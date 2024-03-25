package org.example;

import java.util.List;
import java.util.Map;

public interface Algorithm {
    public List<Map<Integer, Integer>> runAlgorithm(int firstVertex, Long[][] distanceMatrix);
}