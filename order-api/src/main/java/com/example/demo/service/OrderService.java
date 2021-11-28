package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.OrderRepository;
import org.springframework.amqp.core.AmqpTemplate;
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
    ProductService productService;

    @Autowired
    AuthService authService;

    @Autowired
    AmqpTemplate amqpTemplate;

    private String productHost = "http://product-api/";

    public Orders getOrderById(Long OrderId) {
        return OrderRepository.getById(OrderId);
    }

    public List<Orders> getAllOrder() {
        return OrderRepository.findAll();
    }
    
    public Orders createOrder(Orders orders) {

        amqpTemplate.convertAndSend("MQ", "Order created");
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

        boolean authorized = authService.authAdmin(token);
        if (!authorized) {
            return null;
        }

        Orders orders = getOrderById(orderId);

        for (OrderProduct productId : orders.getOrderProducts()) {
            boolean isUpdated = productService.updateProductCount(productId, token);
            if (!isUpdated) {
                return null;
            }
        }
        orders.setIsDone(true);
        updateOrder(orders);

        return orders;

    }

}
