package com.example.exam.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sales_detail")
public class Sale {
    @Id

    private String SlNo;
    private String SalesmanID;
    private String ProdID;
    private String SalesmanName;
    private String DOS;
    @OneToMany(mappedBy = "ProdID", cascade = CascadeType.ALL)
    private Set<Product> product;

}
