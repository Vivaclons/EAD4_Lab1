package com.example.demo.service;

import com.example.demo.model.*;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class ProductService {

    @Autowired
    private RestTemplate restTemplate;
    private String productHost = "http://product-api/";


    @HystrixCommand(fallbackMethod = "updateProductCountFallback")
    public boolean updateProductCount(OrderProduct productId, String token) {

        String url = productHost + "/product/" + productId.getProductID();

        Product product =  restTemplate.getForObject(url, Product.class);

        if (product == null) {
            System.out.println("Put error");
            return false;
        }

        product.setAmount(product.getAmount() - productId.getAmount());

        url = productHost + "/product/update";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Product> request = new HttpEntity<>(product, headers);
        HttpEntity<Product> PutProductResponse =  restTemplate.exchange(url, HttpMethod.PUT, request, Product.class);

        product = PutProductResponse.getBody();
        if (product == null) {
            System.out.println("Put error");
            return false;
        }

        return true;
    }

    public Boolean updateProductCountFallback(String username) {
        return false;
    }
}