package com.example.categories.service.impl;

import com.example.categories.model.ProductCategory;
import com.example.categories.repository.ProductCategoryRepository;
import com.example.categories.service.ProductCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductCategoryCategoryServiceImpl implements ProductCategoryService {

    @Autowired
     private RestTemplate restTemplate;


    @Autowired
    private ProductCategoryRepository productCategoryRepository;





    public List<ProductCategory> findAll(){
        return productCategoryRepository.findAll();
    }

    public ProductCategory saveProduct(ProductCategory product){
        return productCategoryRepository.save(product);
    }

    public void deleteById(Long id){
        productCategoryRepository.deleteById(id);
    }

    public void update (ProductCategory product) {
        productCategoryRepository.save(product);
    }



    @Override
    public List<ProductCategory> getAllProducts() {
        List<ProductCategory> productList = new ArrayList<>();

        List<Long> productIDs = new ArrayList<>(Arrays.asList(1L, 2L, 4L));
        for (Long id : productIDs) {
            ProductCategory product = restTemplate.getForObject("http://localhost:8082/category/" + id, ProductCategory.class);
            productList.add(product);
        }

        return productList;

    }






}
