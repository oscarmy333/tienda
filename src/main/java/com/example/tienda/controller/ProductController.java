package com.example.tienda.controller;

import com.example.tienda.dto.ProductDto;
import com.example.tienda.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {

    private List<Product> products = new ArrayList<>(
            Arrays.asList(
                    new Product(10,"Abarrote", 100),
                    new Product(12, "Bebidas", 200),
                    new Product(30, "Vegetales", 300),
                    new Product(41, "Dulces", 400)
            )
    );

    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        return ResponseEntity.ok(products);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") int id){
        Product product = findById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    private ResponseEntity<Product> create(@RequestBody ProductDto dto){
        int index = products.isEmpty()?1:getLastId()+1;
        Product product=Product.builder().id(index).name(dto.getName()).price(dto.getPrice()).build();
        products.add(product);
        return ResponseEntity.ok(product);
    }

    @PutMapping("{id}")
    private ResponseEntity<Product> update(@PathVariable("id") int id, @RequestBody ProductDto dto){
        Product product = findById(id);
        if (product==null)
            return ResponseEntity.ok(null);
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        products.get(getIndex(id)).setName(dto.getName());
        products.get(getIndex(id)).setPrice(dto.getPrice());
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Product> delete (@PathVariable("id") int id){
        Product product = findById(id);
        products.remove(product);
        return ResponseEntity.ok(product);
    }

    private int getIndex(int id) {
        AtomicInteger counter = new AtomicInteger(-1);
        return products.stream().filter(product -> {counter.getAndIncrement(); return id==product.getId();})
                .mapToInt(p -> counter.get()).findFirst().orElse(-1);
    }


    private int getLastId() {
        return products.stream().max(Comparator.comparing(Product::getId)).get().getId();
    }


    private Product findById(int id) {
        return products.stream().filter(product -> product.getId()==id).findAny().orElse(null);
    }


}
