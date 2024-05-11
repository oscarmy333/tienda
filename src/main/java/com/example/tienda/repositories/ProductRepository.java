package com.example.tienda.repositories;

import com.example.tienda.entities.Category;
import com.example.tienda.entities.ProductFull;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    public List<ProductFull> findAll();
    public Optional<ProductFull> findById(Long id) ;
    public <S extends ProductFull> S save(S entity);
    public List<ProductFull> findByCategory(Category category);
}
