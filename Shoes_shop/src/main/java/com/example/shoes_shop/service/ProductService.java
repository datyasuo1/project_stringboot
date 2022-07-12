package com.example.shoes_shop.service;


import com.example.shoes_shop.entity.Product;
import com.example.shoes_shop.entity.myenum.ProductStatus;
import com.example.shoes_shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Iterable<Product> findAll() {
        return productRepository.findAllByStatusEquals(ProductStatus.ACTIVE, PageRequest.of(0, 10));    }

    public Optional<Product> findById(String id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(String id) {
        productRepository.deleteById(id);
    }
}
