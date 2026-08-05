package com.example.demo.controller;
import com.example.demo.dto.response.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts() {
        List<ProductResponse> products = List.of(
                new ProductResponse("P01", "Laptop Dell XPS 15", 1500.0),
                new ProductResponse("P02", "iPhone 15 Pro Max", 1200.0)
        );
        return ResponseEntity.ok(products);
    }
}
