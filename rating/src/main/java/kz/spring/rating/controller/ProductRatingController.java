package kz.spring.rating.controller;

import kz.spring.rating.service.ProductRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("/product/rating")
public class ProductRatingController {

    @Autowired
    private ProductRatingService productRatingService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductRatingById(@PathVariable Long id) {
        return ResponseEntity.ok(productRatingService.getProductRatingById(id));
    }
}