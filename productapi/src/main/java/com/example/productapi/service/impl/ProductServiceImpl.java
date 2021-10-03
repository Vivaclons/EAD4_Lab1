package com.example.productapi.service.impl;

import com.example.productapi.model.Product;
import com.example.productapi.repository.ProductRepository;
import com.example.productapi.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
     private RestTemplate restTemplate;


    @Autowired
    private ProductRepository productRepository;





    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public void update (Product product) {
        productRepository.save(product);
    }



    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();

        List<Long> productIDs = new ArrayList<>(Arrays.asList(1L, 2L, 4L));
        for (Long id : productIDs) {
            Product product = restTemplate.getForObject("http://localhost:8082/products/" + id, Product.class);
            productList.add(product);
        }

        return productList;

    }






}
