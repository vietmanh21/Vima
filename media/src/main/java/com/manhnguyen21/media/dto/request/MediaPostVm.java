package com.manhnguyen21.media.dto.request;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record MediaPostVm(String caption,
                          MultipartFile multipartFile,
                          String fileNameOverride) {
}
