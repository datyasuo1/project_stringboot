package com.example.shoes_shop.api;


import com.example.shoes_shop.entity.Category;
import com.example.shoes_shop.entity.Product;
import com.example.shoes_shop.service.CategoryService;
import com.example.shoes_shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/products")
public class ProductApi {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    public ProductApi(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Product>> getList() {
        return ResponseEntity.ok(productService.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Product> crete(@RequestBody Product product) {
        return ResponseEntity.ok(productService.save(product));
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        Optional<Product> optionalProduct = productService.findById(id);
        if (optionalProduct.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalProduct.get());
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public ResponseEntity<Product> delete(@PathVariable String id) {
        if (!productService.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<Product> update(@PathVariable String id, @RequestBody Product products,@PathVariable String cateId) {
        Optional<Product> optionalProduct = productService.findById(id);
        Optional<Category> optionalCategory = categoryService.findById(cateId);
        if (!optionalProduct.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        if (!optionalCategory.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        Product existProduct = optionalProduct.get();
        existProduct.setId(products.getId());
        existProduct.setName(products.getName());
        existProduct.setPrice(products.getPrice());
        existProduct.setThumbnail(products.getThumbnail());
        existProduct.setDetail(products.getDetail());
        existProduct.setStatus(products.getStatus());
        existProduct.setCategory(optionalCategory.get());
        return ResponseEntity.ok(productService.save(existProduct));
    }
}
