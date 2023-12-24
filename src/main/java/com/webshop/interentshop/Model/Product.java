package com.webshop.interentshop.Model;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private double price;
    private Integer stockQuantity;
}
