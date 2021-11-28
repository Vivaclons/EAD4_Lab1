package com.example.demo.Controller;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class RabbitController {

    @RabbitListener(queues = "MQ")
    public void processQueue1(String message) {
        System.out.println("Received from mq: " + message);
    }
}