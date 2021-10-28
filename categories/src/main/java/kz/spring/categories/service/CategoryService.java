package kz.spring.categories.service;

import kz.spring.categories.model.Category;
import kz.spring.categories.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService {
    @Autowired
    private AuthService authService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RestTemplate restTemplate;

    private String authHost = "http://auth-service/";

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    public List<Category> getCategoryById(Long categoryId) {
        return categoryRepository.getCategoryById(categoryId);
    }

    public Category createCategory(Category category, String authToken) {

        boolean auth = authService.create(authToken);

        if(!auth){
//            this.ProductRepository.save(product);
////            return category;
            return null;
        }
        return category;

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
//            categoryRepository.save(category);
//            return category;
//        } else {
//            return null;
//        }
    }

    public void deleteCategory(Long categoryId, String authToken) {
        String url = authHost + "user/auth/admin";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authToken);

        Map<String, Object> map = new HashMap<>();
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<Void> response = restTemplate.postForEntity(url, entity, Void.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            categoryRepository.deleteById(categoryId);
        }
        return;
    }

    public Category updateCategory(Category category, String authToken) {
        String url = authHost + "user/auth/admin";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authToken);

        Map<String, Object> map = new HashMap<>();
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<Void> response = restTemplate.postForEntity(url, entity, Void.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            categoryRepository.save(category);
            return category;
        } else {
            return null;
        }
    }
}
