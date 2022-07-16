package com.example.exam.api;

import com.example.exam.entity.Product;
import com.example.exam.entity.Sale;
import com.example.exam.service.ProductService;
import com.example.exam.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/sales")
public class SaleApi {
    @Autowired
    SaleService saleService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Sale>> getList(){
        return ResponseEntity.ok(saleService.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Sale sale){
        return ResponseEntity.ok(saleService.save(sale));
    }

}
