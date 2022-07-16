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
//@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int SlNo;
    private String SalesmanID;

    @ManyToOne
    @JoinColumn(name = "ProId", referencedColumnName = "ProId")
    private Product ProdID;
    private String SalesmanName;
    private String DOS;

}
