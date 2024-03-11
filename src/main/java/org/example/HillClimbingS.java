package org.example;

import javafx.util.Pair;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HillClimbingS {
    List<Integer> cycleA;
    List<Integer> cycleB;

    List<List<Integer>> distanceMatrix;
    double cycleALength= 0;
    double cycleBLength= 0;

    private double calcCycleLength(List<List<Integer>> distanceMatrix, List<Integer> solution){
        double length = 0;
        for(int i= 0; i<solution.size()-2; i++){
            length += distanceMatrix.get(solution.get(i)).get(solution.get(i+1));
        }
        length += distanceMatrix.get(solution.get(solution.size() - 1)).get(solution.get(0));
        return length;
    }

    private List<Integer> swapVertexInside(List<Integer> cycle, int indexOne, int indexTwo){
        List<Integer> alternativeCycle = new ArrayList<>(cycle);
        Collections.swap(alternativeCycle, indexOne, indexTwo);
        return alternativeCycle;
    }

    private List<Integer> swapVertexOutside(List<Integer> cycleModified, List<Integer> cycleRef, int indexModify, int indexRef){
        List<Integer> alternativeCycle = new ArrayList<>(cycleModified);
        Integer newVertex = cycleRef.get(indexRef);
        alternativeCycle.set(indexModify, newVertex);
        return alternativeCycle;
    }

    private Pair<Double,List<Integer>> getSolutionsInside(List<Integer> cycle, int indexOne, int indexTwo){
        List<Integer> newCycle = swapVertexInside(cycle, indexOne, indexTwo);
        double distance = calcCycleLength(distanceMatrix, newCycle);
        return new Pair<>(distance, newCycle);
    }

    private Pair<Double,List<Integer>> getSolutionOutside(List<Integer> cycleModified, List<Integer> cycleRef, int indexModify, int indexRef){
        List<Integer> newCycle = swapVertexOutside(cycleModified, cycleRef, indexModify, indexRef);
        double distance = calcCycleLength(distanceMatrix, newCycle);
        return new Pair<>(distance, newCycle);
    }

    private void getAllSolutions() {
        TreeMap<Double, List<Integer>> solutionsInside = new TreeMap<>();
        TreeMap<Double, List<Integer>> solutionsOutside = new TreeMap<>();
        Comparator<Double> byCost = Double::compareTo;
        for (int i = 0; i < cycleA.size() - 1; i++) {
            for (int j = 0; j < cycleA.size() - 1; j++) {
                if (i == j) {
                    continue;
                }
                Pair<Double, List<Integer>> x = getSolutionsInside(cycleA, i, j);
                solutionsInside.put(x.getKey(),x.getValue());
                Pair<Double, List<Integer>> y = getSolutionOutside(cycleA, cycleB, i, j);
                solutionsOutside.put(y.getKey(),y.getValue());
            }
        }
        Map.Entry<Double, List<Integer>> bestInside = solutionsInside.firstEntry();
        Map.Entry<Double, List<Integer>> bestOutside = solutionsOutside.firstEntry();
        if(bestInside.getKey() <= bestOutside.getKey()) {
            if (bestInside.getKey() < cycleALength){
                cycleALength = bestInside.getKey();
            }
        } else {
            if( bestOutside.getKey() < cycleALength ) {
                cycleALength = bestOutside.getKey();
            }
        }
    }
}