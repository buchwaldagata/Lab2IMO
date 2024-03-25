package org.example;

import java.util.*;

public class RandomAlgorithm implements Algorithm {
    public List<Map<Integer, Integer>> runAlgorithm(int firstVertex, Long[][] distanceMatrix) {
        List<Integer> unassignedVertices = new ArrayList<>();
        for(int i = 0; i < distanceMatrix.length; i++) {
            unassignedVertices.add(i);
        }

        // key - visit number
        // value - vertex index
        Map<Integer, Integer> firstCycle = new HashMap<>();
        Map<Integer, Integer> secondCycle = new HashMap<>();

        Collections.shuffle(unassignedVertices);

        for (int i = 0; i<unassignedVertices.size()/2; i++) {
            firstCycle.put(i, unassignedVertices.get(i));
        }
        firstCycle.put(firstCycle.size(), firstCycle.get(0));

        for (int i = unassignedVertices.size()/2; i<unassignedVertices.size(); i++) {
            secondCycle.put(i-unassignedVertices.size()/2, unassignedVertices.get(i));
        }
        secondCycle.put(secondCycle.size(), secondCycle.get(0));

        List<Map<Integer, Integer>> cycles = new ArrayList<>();
        cycles.add(firstCycle);
        cycles.add(secondCycle);
        return cycles;
    }
}