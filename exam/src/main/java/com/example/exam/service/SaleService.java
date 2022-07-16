package com.example.exam.service;

import com.example.exam.entity.Product;
import com.example.exam.entity.Sale;
import com.example.exam.repository.ProductRepository;
import com.example.exam.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;
    public Iterable<Sale> findAll(){
        return saleRepository.findAll();
    }

    public Sale save(Sale sale){
        return saleRepository.save(sale);
    }
}
