package com.example.shoes_shop.repository;

import com.example.shoes_shop.entity.Product;
import com.example.shoes_shop.entity.myenum.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Page<Product> findAllByStatusEquals(ProductStatus status, Pageable pageable);

}
