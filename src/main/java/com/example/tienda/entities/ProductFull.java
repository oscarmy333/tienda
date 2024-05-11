package com.example.tienda.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductFull {
    private Long id;
    private String name;
    private String description;
    private Double stock;
    private Double price;

    private String status;
    private Date createAt;
    private Category category;
}
