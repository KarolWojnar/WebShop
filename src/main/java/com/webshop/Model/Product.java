package com.webshop.Model;

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
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private double price;
    private Integer stockQuantity;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category categoryId;
    private String image;
    private double rate;
    private int countRate;
}
