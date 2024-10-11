package com.manhnguyen21.media.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "shop")
public record ShopConfig(String publicUrl) {
}
