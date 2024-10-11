package com.manhnguyen21.media.controller;

import com.manhnguyen21.media.constants.Constant;
import com.manhnguyen21.media.dto.request.MediaPostVm;
import com.manhnguyen21.media.dto.response.MediaDto;
import com.manhnguyen21.media.dto.response.MediaRes;
import com.manhnguyen21.media.dto.response.NoFileMediaVm;
import com.manhnguyen21.media.model.Media;
import com.manhnguyen21.media.service.MediaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class MediaController {
    private final MediaService mediaService;

    @PostMapping(path = "/medias", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> create(@ModelAttribute @Valid MediaPostVm mediaPostVm) {
        Media media = mediaService.saveMedia(mediaPostVm);
        NoFileMediaVm noFileMediaVm =
                new NoFileMediaVm(media.getId(), media.getCaption(), media.getFileName(), media.getMediaType());
        return ResponseEntity.ok().body(noFileMediaVm);
    }

    @GetMapping("/medias/{id}")
    public ResponseEntity<MediaRes> get(@PathVariable Long id) {
        MediaRes media = mediaService.getMediaById(id);
        if (media == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(media);
    }

    @GetMapping("/medias/{id}/file/{fileName}")
    public ResponseEntity<InputStreamResource> getFile(@PathVariable Long id, @PathVariable String fileName) {
        MediaDto mediaDto = mediaService.getFile(id, fileName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .contentType(mediaDto.getMediaType())
                .body(new InputStreamResource(mediaDto.getContent()));
    }
}
