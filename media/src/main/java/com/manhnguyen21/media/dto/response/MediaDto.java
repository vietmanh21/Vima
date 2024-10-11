package com.manhnguyen21.media.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.MediaType;

import java.io.InputStream;
@Builder
@Getter
public class MediaDto {
    private InputStream content;
    private MediaType mediaType;
}
