package com.example.demo.controller;


import com.example.demo.model.Orders;
import com.example.demo.service.OrderService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;



    @GetMapping("/all")
    public ResponseEntity<?> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrder());
    }

    @GetMapping("/{OrderId}")
    public ResponseEntity<?> getOrderById(@PathVariable("OrderId") Long OrderId) {
        return ResponseEntity.ok(orderService.getOrderById(OrderId));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody Orders orders) {
        return ResponseEntity.ok(orderService.createOrder(orders));
    }

    @GetMapping("/do/{OrderId}")
    public ResponseEntity<?> doOrder(@PathVariable("OrderId") Long orderId, @RequestHeader("Authorization") String auth) {
        return ResponseEntity.ok(orderService.DoOrder(orderId, auth));
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateOrder(@RequestBody Orders orders, @RequestHeader("Authorization") String auth) {
        return ResponseEntity.ok(orderService.createOrder(orders));
    }

    @DeleteMapping("/delete/{OrderId}")
    public void deleteOrder(@PathVariable("OrderId") Long OrderId, @RequestHeader("Authorization") String auth) {
        orderService.deleteOrder(OrderId);
    }


}
