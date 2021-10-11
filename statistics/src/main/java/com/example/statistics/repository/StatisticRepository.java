package com.example.statistics.repository;

import com.example.statistics.model.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticRepository extends JpaRepository<Statistic, Long> {
    List<Statistic> getStatisticById(Long categoryId);
    List<Statistic> getStatisticByShopName(String shopName);
    List<Statistic> getStatisticByShopId(Long shopId);
}
