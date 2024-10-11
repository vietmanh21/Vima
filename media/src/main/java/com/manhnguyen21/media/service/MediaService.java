package com.manhnguyen21.media.service;

import com.manhnguyen21.common.exception.NotFoundException;
import com.manhnguyen21.media.config.FileSystemConfig;
import com.manhnguyen21.media.config.ShopConfig;
import com.manhnguyen21.media.dto.request.MediaPostVm;
import com.manhnguyen21.media.dto.response.MediaDto;
import com.manhnguyen21.media.dto.response.MediaRes;
import com.manhnguyen21.media.dto.response.NoFileMediaVm;
import com.manhnguyen21.media.model.Media;
import com.manhnguyen21.media.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MediaService {
    private final MediaRepository mediaRepository;
    private final FileSystemConfig fileSystemConfig;
    private final ShopConfig shopConfig;

    @SneakyThrows
    public Media saveMedia(MediaPostVm mediaPost) {
        MediaType mediaType = MediaType.valueOf(Objects.requireNonNull(mediaPost.multipartFile().getContentType()));
        if (!(MediaType.IMAGE_JPEG.equals(mediaType) || MediaType.IMAGE_PNG.equals(mediaType)
                || MediaType.IMAGE_GIF.equals(mediaType))) {
        }
        Media media = new Media();
        media.setCaption(mediaPost.caption());
        if (StringUtils.hasText(mediaPost.fileNameOverride())) {
            media.setFileName(mediaPost.fileNameOverride().trim());
        } else {
            media.setFileName(mediaPost.multipartFile().getOriginalFilename());
        }

        Path filePath = Paths.get(fileSystemConfig.getDirectory() + media.getFileName());
        Files.write(filePath, mediaPost.multipartFile().getBytes());
        media.setFilePath(filePath.toString());
        media.setMediaType(mediaPost.multipartFile().getContentType());

        return mediaRepository.save(media);
    }

    @SneakyThrows
    public MediaDto getFile(Long id, String fileName) {
        Media media = mediaRepository.findById(id).orElse(null);

        if (media == null || !fileName.equalsIgnoreCase(media.getFileName())) {
            return MediaDto.builder().build();
        }
        Path path = Paths.get(media.getFilePath());
        InputStream content = Files.newInputStream(path);
        return MediaDto.builder()
                .content(content)
                .mediaType(MediaType.valueOf(media.getMediaType())).build();
    }

    public MediaRes getMediaById(Long id) {
        NoFileMediaVm noFileMediaVm = mediaRepository.findByIdWithoutFileInReturn(id);
        if (noFileMediaVm == null) {
            return null;
        }
        String url = UriComponentsBuilder.fromUriString(shopConfig.publicUrl())
                .path(String.format("/medias/%1$s/file/%2$s", noFileMediaVm.id(), noFileMediaVm.fileName())).build().toUriString();

        return new MediaRes(noFileMediaVm.id(),
                noFileMediaVm.caption(), noFileMediaVm.fileName(),
                noFileMediaVm.mediaType(), url);
    }
}
