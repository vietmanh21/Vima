package com.manhnguyen21.product.dto.response;


import com.manhnguyen21.product.model.Brand;

public record BrandVm(Long id, String name, String slug, Boolean isPublish) {
    public static BrandVm fromModel(Brand brand) {
        return new BrandVm(brand.getId(), brand.getName(), brand.getSlug(), brand.isPublished());
    }
}
