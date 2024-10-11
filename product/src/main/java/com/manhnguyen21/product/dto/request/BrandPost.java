package com.manhnguyen21.product.dto.request;

import com.manhnguyen21.product.model.Brand;
import jakarta.validation.constraints.NotNull;

public record BrandPost(@NotNull String name, @NotNull String slug, Boolean isPublish ) {
    public Brand toModel() {
        Brand brand = new Brand();
        brand.setName(name);
        brand.setSlug(slug);
        brand.setPublished(isPublish);
        return brand;
    }
}
