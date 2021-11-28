package kz.spring.support.Controller;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class RabbitController {

    @RabbitListener(queues = "Support")
    public void processQueue1(String message) {
        System.out.println("Received from ShQ: " + message);
    }
}