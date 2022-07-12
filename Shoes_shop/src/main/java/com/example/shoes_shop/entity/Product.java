package com.example.shoes_shop.entity;

import com.example.shoes_shop.entity.base.BaseEntity;
import com.example.shoes_shop.entity.myenum.ProductStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    @Id
    private String id;
    private String name;
    private String thumbnail;
    private String description;
    private String detail;
    private BigDecimal price;
    @Enumerated(EnumType.ORDINAL)
    private ProductStatus status;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

}
