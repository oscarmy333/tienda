package com.example.tienda.services;

import com.example.tienda.entities.Category;
import com.example.tienda.entities.ProductFull;
import com.example.tienda.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductFull> listAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public ProductFull getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public ProductFull createProduct(ProductFull product) {
        product.setStatus("CREATED");
        product.setCreateAt(new Date());
        return productRepository.save(product);
    }

    @Override
    public ProductFull updateProduct(ProductFull product) {
        ProductFull productDB = getProduct(product.getId());
        if (productDB == null) {
            return null;
        }
        productDB.setName(product.getName());
        productDB.setDescription(product.getDescription());
        productDB.setCategory(product.getCategory());
        productDB.setPrice(product.getPrice());
        return productRepository.save(productDB);
    }

    @Override
    public ProductFull deleteProduct(Long id) {
        ProductFull productDB = getProduct(id);
        if (productDB == null) {
            return null;
        }
        productDB.setStatus("DELETED");
        return productRepository.save(productDB);
    }

    @Override
    public List<ProductFull> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public ProductFull updateStock(Long id, Double quantity) {
        ProductFull productDB = getProduct(id);
        if (productDB == null) {
            return null;
        }
        Double stock = productDB.getStock() + quantity;
        productDB.setStock(stock);
        return productRepository.save(productDB);
    }
}
