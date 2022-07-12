package com.example.shoes_shop.seeder;

import com.example.shoes_shop.entity.Category;
import com.example.shoes_shop.repository.CategoryRepository;
import com.example.shoes_shop.util.NumberUtil;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class CategorySeeder {
    public static List<Category> categories;
    public static final int NUMBER_OF_PRODUCT = 10;

    @Autowired
    CategoryRepository categoryRepository;
    public void generate() {
        log.debug("------------Seeding product-------------");
        Faker faker = new Faker();
        categories = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_PRODUCT; i++) {
            categories.add(Category.builder()
                    .id(UUID.randomUUID().toString())
                    .name(faker.name().name())
                    .build());
        }
        categoryRepository.saveAll(categories);
        log.debug("--------------End of seeding product-------------");
    }
}
