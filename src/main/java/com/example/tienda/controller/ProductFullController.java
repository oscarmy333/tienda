package com.example.tienda.controller;

import com.example.tienda.entities.Category;
import com.example.tienda.entities.ProductFull;
import com.example.tienda.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductFullController {
    @Autowired
    private ProductService productService;

    //GET /products?categoryId=1
    @GetMapping
    public ResponseEntity<List<ProductFull>>
    listProduct(@RequestParam(name = "categoryId", required = false) Long categoryId) {
        List<ProductFull> products = null;
        if (categoryId == null) {
            products = productService.listAllProduct();
            if (products.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
        } else {
            products =
                    productService.findByCategory(Category.builder().id(categoryId).build());
            if (products.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductFull> getProduct(@PathVariable("id") Long id) {
        ProductFull product = productService.getProduct(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<ProductFull> createProduct(@RequestBody ProductFull product) {
        ProductFull productCreate = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCreate);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductFull> updateProduct(@PathVariable("id") Long id,
                                                     @RequestBody ProductFull product) {
        product.setId(id);
        ProductFull productDB = productService.updateProduct(product);
        if (productDB == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDB);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ProductFull> deleteProduct(@PathVariable("id") Long id) {
        ProductFull productDelete = productService.deleteProduct(id);
        if (productDelete == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDelete);
    }

    @GetMapping(value = "/{id}/stock")
    public ResponseEntity<ProductFull> updateStockProduct(@PathVariable("id") Long id,
                                                          @RequestParam(name = "quantity", required = true) Double quantity) {
        ProductFull product = productService.updateStock(id, quantity);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

}

