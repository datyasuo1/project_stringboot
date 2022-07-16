package com.example.exam.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_details")
public class Product {
    @Id

    private String ProId;
    private String ProdName;
    private String Description;
    private Date DateOfManf;
    private BigDecimal Price;
}
