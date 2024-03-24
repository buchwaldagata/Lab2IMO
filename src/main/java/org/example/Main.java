package org.example;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        CoordinateList coordinateList = new CoordinateList("src/resources/kroA100.tsp");
        int[][] intCoordinateList = coordinateList.intCoordinateList;
        DistanceMatrix distanceMatrix = new DistanceMatrix(intCoordinateList);
        Long[][] distanceMatrix2 = distanceMatrix.distanceMatrix;

        GreedyCycleAlgorithm greedyCycleAlgorithm = new GreedyCycleAlgorithm();

        List<Map<Integer, Integer>>  cycles = greedyCycleAlgorithm.runAlgorithm(92, distanceMatrix2);
        Map<Integer, Integer> firstCycle = cycles.get(0);
        Map<Integer, Integer> secondCycle = cycles.get(1);
        System.out.println(cycles);
        System.out.println(firstCycle);
        System.out.println(getLengthFromCycle(firstCycle, distanceMatrix2));
        System.out.println(getLengthFromCycle(secondCycle,distanceMatrix2));
        System.out.println("HEJ");


        MovesAlteringSetsOfVerticesFormingTwoCycles movesAlteringSetsOfVerticesFormingTwoCycles = new MovesAlteringSetsOfVerticesFormingTwoCycles();
        movesAlteringSetsOfVerticesFormingTwoCycles.run(cycles, distanceMatrix2);


    }


    public static Long getLengthFromCycle(Map<Integer, Integer> cycle, Long[][] distanceMatrix) {
        Long len = 0L;
        for(int i=0; i<cycle.keySet().size(); i++) {
            int fromVertex = cycle.get(i);
            int toVertex;
            if ( i == cycle.keySet().size()-1){
                toVertex = cycle.get(0);
            }
            else{
                toVertex = cycle.get(i+1);
            }
            len += distanceMatrix[fromVertex][toVertex];
        }
        return len;
    }
}