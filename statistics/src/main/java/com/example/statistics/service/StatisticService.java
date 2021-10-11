package com.example.statistics.service;

import com.example.statistics.model.Statistic;
import com.example.statistics.repository.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticService {
    @Autowired
    private StatisticRepository statisticRepository;
    @Autowired
    private RestTemplate restTemplate;

    String authHost = "http://auth-service/";

    public List<Statistic> getStatisticByShopId(Long shopId) {
        return statisticRepository.getStatisticByShopId(shopId);
    }

    public List<Statistic> getStatisticByShopName(String shopName) {
        return this.statisticRepository.getStatisticByShopName(shopName);
    }

    public List<Statistic> getStatisticById(Long statisticId){
        return statisticRepository.getStatisticById(statisticId);
    }

    public Statistic createStatistic(Statistic statistic, String authToken) {
        String url = authHost + "user/auth/user";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authToken);

        Map<String, Object> map = new HashMap<>();
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<Void> response = restTemplate.postForEntity(url, entity, Void.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            this.statisticRepository.save(statistic);
            return statistic;
        } else {
            return null;
        }
    }

    public Statistic updateStatistic(Statistic statistic, String authToken) {
        String url = authHost + "user/auth/admin";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authToken);

        Map<String, Object> map = new HashMap<>();
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<Void> response = restTemplate.postForEntity(url, entity, Void.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            this.statisticRepository.save(statistic);
            return statistic;
        } else {
            return null;
        }
    }

    public void deleteStatistic(Long statisticId, String authToken) {

        String url = authHost + "user/auth/admin";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authToken);

        Map<String, Object> map = new HashMap<>();
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<Void> response = restTemplate.postForEntity(url, entity, Void.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            this.statisticRepository.deleteById(statisticId);
        }
        return;
    }

    public Double averageCheck(Long shopId){
        double count = 0;
        List<Double> average = new ArrayList<>();
        int i = 0;
        for(Statistic statistic : statisticRepository.getStatisticByShopId(shopId)){
            average.add(statistic.getAverageCheck());
            count += average.get(i);
            i++;
        }
        return count;
    }

    public int averageOrder(Long shopId){
        int count = 0;
        List<Integer> average = new ArrayList<>();
        int i = 0;
        for(Statistic statistic : statisticRepository.getStatisticByShopId(shopId)){
            average.add(statistic.getOrders());
            count += average.get(i);
            i++;
        }
        return count;
    }

    public List<Statistic> getAll() {
        return this.statisticRepository.findAll();
    }
}
