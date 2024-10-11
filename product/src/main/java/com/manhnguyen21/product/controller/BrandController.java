package com.manhnguyen21.product.controller;

import com.manhnguyen21.product.dto.request.BrandPost;
import com.manhnguyen21.product.dto.response.BrandVm;
import com.manhnguyen21.product.model.Brand;
import com.manhnguyen21.product.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BrandController {
    private final BrandService brandService;

    @PostMapping("/backoffice/brands")
    public ResponseEntity<BrandVm> createBrand(
            @Valid @RequestBody BrandPost brandPost,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        Brand brand = brandService.create(brandPost);
        return ResponseEntity.created(uriComponentsBuilder.replacePath("/brands/{id}")
                        .buildAndExpand(brand.getId()).toUri())
                .body(BrandVm.fromModel(brand));
    }

    @GetMapping("/backoffice/brands/{id}")
    public ResponseEntity<BrandVm> getBrand(
            @PathVariable(value = "id") Long id
    ) {
        BrandVm brand = brandService.getBrand(id);
        return ResponseEntity.ok(brand);
    }
}
