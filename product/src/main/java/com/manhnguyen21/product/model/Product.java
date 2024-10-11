package com.manhnguyen21.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.manhnguyen21.product.model.attribute.ProductAttributeValue;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product extends AbstractAuditEntity {
    @OneToMany(mappedBy = "product")
    @Builder.Default
    List<ProductRelated> relatedProducts = new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String shortDescription;
    private String description;
    private String specification;
    private String sku;
    private String gtin;
    private String slug;
    private Double price;
    private boolean hasOptions;
    private boolean isAllowedToOrder;
    private boolean isPublished;
    private boolean isFeatured;
    private boolean isVisibleIndividually;
    private boolean stockTrackingEnabled;
    private Long stockQuantity;
    private Long taxClassId;
    private String metaTitle;
    private String metaKeyword;
    private String metaDescription;
    private Long thumbnailMediaId;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
    @OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST})
    @Builder.Default
    private List<ProductCategory> productCategories = new ArrayList<>();
    @OneToMany(mappedBy = "product")
    @JsonIgnore
    @Builder.Default
    private List<ProductAttributeValue> attributeValues = new ArrayList<>();
    @OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST})
    @Builder.Default
    private List<ProductImage> productImages = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Product parent;
    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
    @JsonIgnore
    @Builder.Default
    private List<Product> products = new ArrayList<>();
    private boolean taxIncluded;


}
