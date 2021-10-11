package com.example.statistics.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticList {
    private List<Statistic> averageCheck;
    private List<Statistic> averageOrders;
}
