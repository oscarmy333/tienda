package com.example.tienda.repositories;

import com.example.tienda.entities.Category;
import com.example.tienda.entities.ProductFull;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private List<Category> categories = new ArrayList<Category>();

    private List<ProductFull> products = new ArrayList<ProductFull>();

    private void init() {
        Category c1 = new Category(1L, "shoes");
        Category c2 = new Category(2L, "books");
        Category c3 = new Category(3L, "electronics");
        categories.add(c1);
        categories.add(c2);
        categories.add(c3);
        ProductFull p1 = new ProductFull(1L, "adidas Cloudfoam Ultimate",
                "Walkin the air in the back", 2.0, 20.0, "CREATED", new Date(), c1);
        ProductFull p2 = new ProductFull(2L, "under armour men", "Micro G assert",
                4.0, 40.0, "CREATED", new Date(), c1);
        ProductFull p3 = new ProductFull(3L, "Spring Boot in Action",
                "Caig walls is a software developer", 6.0, 60.0, "CREATED", new Date(), c2);
        products.add(p1);
        products.add(p2);
        products.add(p3);
    }

    public ProductRepositoryImpl() {
        init();
    }

    @Override
    public List<ProductFull> findAll() {
        return products;
    }

    @Override
    public Optional<ProductFull> findById(Long id) {
        return products.stream().filter(p -> p.getId() == id).findAny();
    }

    @Override
    public <S extends ProductFull> S save(S entity) {
        products.add(entity);
        return entity;
    }

    @Override
    public List<ProductFull> findByCategory(Category category) {
        return products.stream().filter(p -> p.getCategory().getId() == category.getId()).collect(Collectors.toList());
    }

}
