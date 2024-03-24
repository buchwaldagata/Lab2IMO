package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
//        Instance kroA100 = new Instance("src/main/resources/kroA100.tsp");
        Instance kroB100 = new Instance("src/main/resources/kroB100.tsp");
        StartingSolution startingCycleA = new StartingSolution("src/main/resources/kroB100_cycleA_greedy.tsp");
        StartingSolution startingCycleB = new StartingSolution("src/main/resources/kroB100_cycleB_greedy.tsp");


        List<HillClimbingS> solutions = new ArrayList<HillClimbingS>();
//        solutions.add(new HillClimbingS(kroB100, startingCycleA, startingCycleB));
        for (int i = 0; i < 100; i++) {
            RandomStart startingCycles = new RandomStart();
            solutions.add(new HillClimbingS(kroB100, startingCycles));
        }

        int best = 0;
        double cost = 999999999;
        for (int i = 0; i < solutions.size(); i++) {
            if (solutions.get(i).bestCyclesLength < cost) {
                best = i;
                cost = solutions.get(i).bestCyclesLength;
            }
        }

        List<Double> criteriumValues=new ArrayList<>();
        for (HillClimbingS solution: solutions) {
            criteriumValues.add(solution.getSolutionValue());
        }
        System.out.println(criteriumValues.stream().max(Double::compareTo));
        System.out.println(criteriumValues.stream().min(Double::compareTo));
        if(criteriumValues.stream().reduce(Double::sum).isPresent()){
            System.out.println(criteriumValues.stream().reduce(Double::sum).get()/Double.parseDouble(String.valueOf(criteriumValues.size())));
        }

        solutions.get(best).solutionToCsv("best.csv",kroB100);
    }
}
