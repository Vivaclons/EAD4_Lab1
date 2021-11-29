package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.amqp.core.AmqpTemplate;




import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService  {
    @Autowired
    private AuthService authService;
    @Autowired
    private ProductRepository ProductRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    AmqpTemplate amqpTemplate;


    private String authHost = "http://AUTH-SERVICE/";

    public Product getProductById(Long ProductId) {
        return ProductRepository.getProductById(ProductId);
    }

    public List<Product> getAllProduct() {
        amqpTemplate.convertAndSend("SQ", "List of product");

        return ProductRepository.findAll();
    }

    public List<Product> searchProductByCategory(Long categoryId) {
        return ProductRepository.getProductByCategoryID(categoryId);
    }

    public Product createProduct(Product product, String authToken) {

        amqpTemplate.convertAndSend("SQ", "List of product");
        boolean auth = authService.create(authToken);

        if(!auth){
//            this.ProductRepository.save(product);
////            return product;
            return null;
        }
        return product;



//        String url = authHost + "user/auth/admin";
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", authToken);
//
//        Map<String, Object> map = new HashMap<>();
//        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
//
//        ResponseEntity<Void> response = restTemplate.postForEntity(url, entity, Void.class);
//
//        if (response.getStatusCode() == HttpStatus.OK) {
//            ProductRepository.save(product);
//            return product;
//        } else {
//            return null;
//        }

    }

    public void deleteProduct(Long ProductId, String authToken) {

        String url = authHost + "user/auth/admin";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authToken);

        Map<String, Object> map = new HashMap<>();
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<Void> response = restTemplate.postForEntity(url, entity, Void.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            ProductRepository.deleteById(ProductId);
        }

        return;

    }

    public Product updateProduct (Product product, String authToken) {

        String url = authHost + "user/auth/admin";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authToken);

        Map<String, Object> map = new HashMap<>();
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<Void> response = restTemplate.postForEntity(url, entity, Void.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            ProductRepository.save(product);
            return product;
        } else {
            return null;
        }


    }

}
