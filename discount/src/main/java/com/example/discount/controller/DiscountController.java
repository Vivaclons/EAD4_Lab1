package com.example.discount.controller;

import com.example.discount.model.Discount;
import com.example.discount.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/discount")
public class DiscountController {
    @Autowired
    private DiscountService discountService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllDiscounts() {
        return ResponseEntity.ok(discountService.getAllDiscount());
    }

    @GetMapping("/calculate")
    public ResponseEntity calculateDiscount() {
        return ResponseEntity.ok(discountService.calculateDiscount(1000));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createDiscount(@RequestBody Discount discount, @RequestHeader("Authorization") String auth) {
        return ResponseEntity.ok(discountService.createDiscount(discount, auth));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateDiscount(@RequestBody Discount discount, @RequestHeader("Authorization") String auth) {
        return ResponseEntity.ok(discountService.updateDiscount(discount, auth));
    }

    @DeleteMapping("/delete/{ProductId}")
    public void deleteDiscount(@PathVariable("discountId") Long ProductId, @RequestHeader("Authorization") String auth) {
        discountService.deleteDiscount(ProductId, auth);
    }
}
