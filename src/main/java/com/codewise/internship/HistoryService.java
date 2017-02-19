package com.codewise.internship;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

class HistoryService implements Service {

    private Map<ID, List<Double>> history = initMap();

    @Override
    public void put(ID clientId, double money) {
        List<Double> prices = Optional.ofNullable(history.get(clientId)).orElse(new ArrayList<>());
        prices.add(money);
        history.put(clientId, prices);
    }

    @Override
    public double get(ID clientId) {
        List<Double> prices = Optional.ofNullable(history.get(clientId)).orElse(Collections.emptyList());
        return calculateAverage(prices);
    }

    private double calculateAverage(List<Double> prices) {
        return prices.stream()
            .mapToDouble(Double::doubleValue)
            .average()
            .orElse(0.0);
    }

    private Map<ID, List<Double>> initMap() {
        return new ConcurrentHashMap<>();
    }
}
