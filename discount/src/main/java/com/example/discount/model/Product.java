package com.example.discount.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Product {
    private Long id;
    private String name;
    private String description;
    private Double amount;
    private Double sellingPrice;
    private Double purchasePrice;
    private Double rating;
    private Long categoryID;
}
