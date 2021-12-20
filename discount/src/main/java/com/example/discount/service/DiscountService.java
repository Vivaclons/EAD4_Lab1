package com.example.discount.service;

import com.example.discount.model.Discount;
import com.example.discount.repository.DiscountRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountService {
    @Autowired
    private DiscountRepository discountRepository;
    @Autowired
    private AuthService authService;
    @Autowired
    AmqpTemplate amqpTemplate;

    private String authHost = "http://auth-service/";

    public List<Discount> getAllDiscount() {
        amqpTemplate.convertAndSend("ShQ", "description added");
        return this.discountRepository.findAll();
    }

    public Discount createDiscount(Discount discount, String authToken) {
        amqpTemplate.convertAndSend("ShQ", "description added");
        boolean auth = authService.create(authToken);

        if(!auth){
            return null;
        }
        return discount;
    }

    public Discount updateDiscount(Discount discount, String authToken) {
        amqpTemplate.convertAndSend("ShQ", "description added");
        boolean auth = authService.create(authToken);

        if(!auth){
            return null;
        }
        return discount;
    }

    public void deleteDiscount(Long discountId, String authToken) {
        amqpTemplate.convertAndSend("ShQ", "description added");
        String url = authHost + "user/auth/admin";

        boolean auth = authService.create(authToken);

        if (!auth) {
            this.discountRepository.deleteById(discountId);
        }
        return;
    }

    public Double calculateDiscount(int price){
        double amount= (100*price)/100;
        return amount;
    }
}
