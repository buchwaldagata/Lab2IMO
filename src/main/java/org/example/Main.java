package org.example;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        CoordinateList coordinateList = new CoordinateList("src/resources/kroB100.tsp");
        int[][] intCoordinateList = coordinateList.intCoordinateList;
        DistanceMatrix distanceMatrix = new DistanceMatrix(intCoordinateList);
        Long[][] distanceMatrix2 = distanceMatrix.distanceMatrix;

        GreedyCycleAlgorithm greedyCycleAlgorithm = new GreedyCycleAlgorithm();

        List<Map<Integer, Integer>>  cycles = greedyCycleAlgorithm.runAlgorithm(8, distanceMatrix2);
        System.out.println(cycles);
        System.out.println("HEJ");
    }
}