package com.example.demo.service;

import com.example.demo.model.Orders;
import com.example.demo.model.OrderProduct;
import com.example.demo.model.Product;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    @Autowired
    private OrderRepository OrderRepository;
    @Autowired
    private RestTemplate restTemplate;

    private String authHost    = "http://auth-service/";
    private String productHost = "http://product-api/";

    public Orders getOrderById(Long OrderId) {
        return OrderRepository.getById(OrderId);
    }

    public List<Orders> getAllOrder() {
        return OrderRepository.findAll();
    }
    
    public Orders createOrder(Orders orders) {
        OrderRepository.save(orders);
        return orders;
    }

    public void deleteOrder(Long OrderId) {
        OrderRepository.deleteById(OrderId);
    }

    public Orders updateOrder (Orders orders) {
        OrderRepository.save(orders);
        return orders;
    }

    public Orders DoOrder(Long orderId, String token) {

        String url = authHost + "user/auth/admin";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        headers.setContentType(MediaType.APPLICATION_JSON);


        Map<String, Object> map = new HashMap<>();
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<Void> response = restTemplate.postForEntity(url, entity, Void.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            return null;
        }

        Orders orders = getOrderById(orderId);

        for (OrderProduct productId : orders.getOrderProducts()) {

            url = productHost + "/product/" + productId.getProductID();

            Product product =  restTemplate.getForObject(url, Product.class);

            if (product == null) {
                System.out.println("Put error");
                return null;
            }

            product.setAmount(product.getAmount() - productId.getAmount());

            url = productHost + "/product/update";

            HttpEntity<Product> request = new HttpEntity<>(product, headers);
            HttpEntity<Product> PutProductResponse =  restTemplate.exchange(url, HttpMethod.PUT, request, Product.class);

            product = PutProductResponse.getBody();
            if (product == null) {
                System.out.println("Put error");
                return null;
            }
        }
        orders.setIsDone(true);
        updateOrder(orders);

        return orders;

    }

}
