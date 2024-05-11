package com.example.tienda.services;

import com.example.tienda.entities.Category;
import com.example.tienda.entities.ProductFull;

import java.util.List;

public interface ProductService {

    public List<ProductFull> listAllProduct();
    public ProductFull getProduct(Long id);
    public ProductFull createProduct(ProductFull product);
    public ProductFull updateProduct(ProductFull product);
    public ProductFull deleteProduct(Long id);
    public List<ProductFull> findByCategory(Category category);
    public ProductFull updateStock(Long id, Double quantity);
}
