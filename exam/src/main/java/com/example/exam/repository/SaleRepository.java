package com.example.exam.repository;

import com.example.exam.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, String> {
}
