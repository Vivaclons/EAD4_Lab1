package com.example.demo.controller;


import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @GetMapping("/{ProductId}")
    public ResponseEntity<?> getProductById(@PathVariable("ProductId") Long ProductId) {
        return ResponseEntity.ok(productService.getProductById(ProductId));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody Product product, @RequestHeader("Authorization") String auth) {
        return ResponseEntity.ok(productService.createProduct(product, auth));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody Product product, @RequestHeader("Authorization") String auth) {
        return ResponseEntity.ok(productService.updateProduct(product, auth));
    }

    @DeleteMapping("/delete/{ProductId}")
    public void deleteProduct(@PathVariable("ProductId") Long ProductId, @RequestHeader("Authorization") String auth) {

        productService.deleteProduct(ProductId, auth);
    }


}
