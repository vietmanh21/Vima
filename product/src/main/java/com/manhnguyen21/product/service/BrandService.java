package com.manhnguyen21.product.service;

import com.manhnguyen21.common.exception.NotFoundException;
import com.manhnguyen21.product.dto.request.BrandPost;
import com.manhnguyen21.product.dto.response.BrandVm;
import com.manhnguyen21.product.model.Brand;
import com.manhnguyen21.product.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;


    public Brand create(BrandPost brandPost) {
        return brandRepository.save(brandPost.toModel());
    }

    public BrandVm getBrand(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() ->
                new NotFoundException("BRAND_NOT_FOUND", id));
        return BrandVm.fromModel(brand);
    }
}
