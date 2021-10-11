package com.example.statistics.controller;

import com.example.statistics.model.Statistic;
import com.example.statistics.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statistic")
public class StatisticController {
    @Autowired
    private StatisticService statisticService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllStatistic() {
        return ResponseEntity.ok(statisticService.getAll());
    }

    @GetMapping("/{StatisticId}")
    public ResponseEntity<?> getStatisticById(@PathVariable("StatisticId") Long statisticId) {
        return ResponseEntity.ok(statisticService.getStatisticById(statisticId));
    }

    @GetMapping("/average/check/{shopId}")
    public ResponseEntity<?> averageCheck(@PathVariable("shopId") Long shopId) {
        return ResponseEntity.ok(statisticService.averageCheck(shopId));
    }

    @GetMapping("/average/order/{shopId}")
    public ResponseEntity<?> averageOrder(@PathVariable("shopId") Long shopId) {
        return ResponseEntity.ok(statisticService.averageOrder(shopId));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody Statistic statistic, @RequestHeader("Authorization") String auth) {
        return ResponseEntity.ok(statisticService.createStatistic(statistic, auth));
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody Statistic statistic, @RequestHeader("Authorization") String auth) {
        return ResponseEntity.ok(statisticService.updateStatistic(statistic, auth));
    }

    @DeleteMapping("/delete/{StatisticId}")
    public void deleteProduct(@PathVariable("StatisticId") Long statisticId, @RequestHeader("Authorization") String auth) {
        statisticService.deleteStatistic(statisticId, auth);
    }
}
