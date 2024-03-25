package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        CoordinateList coordinateList = new CoordinateList("src/resources/kroB100.tsp");
        int[][] intCoordinateList = coordinateList.intCoordinateList;
        DistanceMatrix distanceMatrix = new DistanceMatrix(intCoordinateList);
        Long[][] distanceMatrix2 = distanceMatrix.distanceMatrix;

        Algorithm algorithm = new GreedyCycleAlgorithm();
//        Algorithm algorithm = new RandomAlgorithm();

//        ChangeInterface changeInterface = new ChangeVertex();
        ChangeInterface changeInterface = new ChangeEdge();

        long max = -1L;
        long min = 1000000L;
        long minIndex = -1L;
        long totalLength = 0L;
        long maxTime = -1L;
        long minTime = 1000000L;
        long minIndexTime = -1L;
        long totalLengthTime = 0L;
        for (int i = 0 ; i < 100; i++) {
            List<Map<Integer, Integer>> cycles = algorithm.runAlgorithm(i, distanceMatrix2);
            GreedyLocalSearch greedyLocalSearch = new GreedyLocalSearch();
            long start = System.currentTimeMillis();
            cycles = greedyLocalSearch.run(cycles, distanceMatrix2, changeInterface);
            long finish = System.currentTimeMillis();
            long timeElapsed = finish - start;
            Map<Integer, Integer> firstCycle = cycles.get(0);
            Map<Integer, Integer> secondCycle = cycles.get(1);
            long len = Main.getLengthFromCycle(firstCycle, distanceMatrix2) + Main.getLengthFromCycle(secondCycle, distanceMatrix2);
            if(len > max){
                max = len;
            }
            if (len < min){
                min = len;
                minIndex = i;
            }
            totalLength += len;

            if(timeElapsed > maxTime){
                maxTime = timeElapsed;
            }
            if (timeElapsed < minTime){
                minTime = timeElapsed;
                minIndexTime = i;
            }
            totalLengthTime += timeElapsed;

            saveCycle(intCoordinateList, firstCycle, "firstCycle"+i);
            saveCycle(intCoordinateList, secondCycle, "secondCycle"+i);
        }
        long avg = totalLength/100;
        long avgTime = totalLengthTime/100;
        System.out.println("Maximum " + max);
        System.out.println("Minimum " + min + " for " + minIndex);
        System.out.println("Average " + avg);
        System.out.println();
        System.out.println("Maximum time " + maxTime);
        System.out.println("Minimum time " + minTime + " for " + minIndexTime);
        System.out.println("Average time " + avgTime);
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

    static void saveCycle(int[][] coordinateList, Map<Integer, Integer> cycle, String filename) {
        cycle.put(cycle.size(), cycle.get(0));
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        Main.saveToFile(bufferedWriter, "lp", "x", "y");
        for(int i =0; i<cycle.size(); i++) {
            int vertex = cycle.get(i);
            for (int[] coordinateRow: coordinateList) {
                if(vertex==coordinateRow[0]) {
                    Main.saveToFile(bufferedWriter, String.valueOf(coordinateRow[0]), String.valueOf(coordinateRow[1]), String.valueOf(coordinateRow[2]));
                }
            }
        }
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void saveToFile(BufferedWriter bufferedWriter, String firstVertex, String x, String y){
        try {
            writePointToCsv(bufferedWriter, firstVertex, x, y);
        } catch (IOException e) {
            System.err.println("Wystąpił błąd podczas zapisywania do pliku: " + e.getMessage());
        }
    }

    private static void writePointToCsv(BufferedWriter bufferedWriter, String vertex, String x, String y) throws IOException {
        bufferedWriter.write(vertex + "," + x + "," + y + "\n");
    }
}