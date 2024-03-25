package org.example;

import java.util.*;

public class GreedyLocalSearch {
    public List<Map<Integer, Integer>> run(List<Map<Integer, Integer>>  cycles, Long[][] distanceMatrix, ChangeInterface changeInterface) {
        Map<Integer, Integer> firstCycle = cycles.get(0);
        Map<Integer, Integer> secondCycle = cycles.get(1);
        firstCycle.remove(firstCycle.keySet().size() - 1);
        secondCycle.remove(secondCycle.keySet().size() - 1);

        int firstCycleSize = firstCycle.size();
        int secondCycleSize = secondCycle.size();

        List<Integer> keysFirstCycleOperation0 = new ArrayList<>(firstCycle.keySet());
        List<Integer> keysSecondCycleOperation0 = new ArrayList<>(secondCycle.keySet());
        List<Integer> keysFirstCycleOperation1 = new ArrayList<>(firstCycle.keySet());
        List<Integer> keysSecondCycleOperation2 = new ArrayList<>(secondCycle.keySet());

        long sum = Main.getLengthFromCycle(firstCycle, distanceMatrix) + Main.getLengthFromCycle(secondCycle, distanceMatrix);
        long oldSum;
        do {
            List<Integer> numberOfOperations = getNumberOfOperations(firstCycleSize, secondCycleSize);
            Collections.shuffle(keysFirstCycleOperation0);
            Collections.shuffle(keysSecondCycleOperation0);
            Collections.shuffle(keysFirstCycleOperation1);
            Collections.shuffle(keysSecondCycleOperation2);
            int keysFirstCycleOperation0Pointer = 0;
            int keysSecondCycleOperation0Pointer = 0;
            int keysFirstCycleOperation1Pointer0 = 0;
            int keysFirstCycleOperation1Pointer1 = 1;
            int keysSecondCycleOperation2Pointer0 = 0;
            int keysSecondCycleOperation2Pointer1 = 1;
            oldSum = sum;
            outerLoop:
            for (Integer numberOfOperation : numberOfOperations) {
                if (numberOfOperation == 0) {
                    int keyFirstCycle = keysFirstCycleOperation0.get(keysFirstCycleOperation0Pointer);
                    int keySecondCycle = keysSecondCycleOperation0.get(keysSecondCycleOperation0Pointer);
                    MovesAlteringSetsOfVerticesFormingTwoCycles.run(cycles, keyFirstCycle, keySecondCycle);
                    long newSum = Main.getLengthFromCycle(firstCycle, distanceMatrix) + Main.getLengthFromCycle(secondCycle, distanceMatrix); //todo: change
                    if (newSum >= sum) {
                        MovesAlteringSetsOfVerticesFormingTwoCycles.undo(cycles, keyFirstCycle, keySecondCycle);
                    } else {
                        sum = newSum;
                        break outerLoop;
                    }
                    keysFirstCycleOperation0Pointer++;
                    if (keysFirstCycleOperation0Pointer == firstCycleSize) {
                        keysSecondCycleOperation0Pointer++;
                        keysFirstCycleOperation0Pointer = 0;
                    }
                } else if (numberOfOperation == 1) {
                    int keyFirstCycle0 = keysFirstCycleOperation1.get(keysFirstCycleOperation1Pointer0);
                    int keyFirstCycle1 = keysFirstCycleOperation1.get(keysFirstCycleOperation1Pointer1);
                    changeInterface.run(firstCycle, keyFirstCycle0, keyFirstCycle1);
                    long newSum = Main.getLengthFromCycle(firstCycle, distanceMatrix) + Main.getLengthFromCycle(secondCycle, distanceMatrix); //todo: change
                    if (newSum >= sum) {
                        changeInterface.undo(firstCycle, keyFirstCycle0, keyFirstCycle1);
                    } else {
                        sum = newSum;
                        break outerLoop;
                    }
                    keysFirstCycleOperation1Pointer1++;
                    if (keysFirstCycleOperation1Pointer1 == firstCycleSize) {
                        keysFirstCycleOperation1Pointer0++;
                        keysFirstCycleOperation1Pointer1 = keysFirstCycleOperation1Pointer0 + 1;
                    }
                } else {
                    int keySecondCycle0 = keysSecondCycleOperation2.get(keysSecondCycleOperation2Pointer0);
                    int keySecondCycle1 = keysSecondCycleOperation2.get(keysSecondCycleOperation2Pointer1);
                    changeInterface.run(secondCycle, keySecondCycle0, keySecondCycle1);
                    long newSum = Main.getLengthFromCycle(secondCycle, distanceMatrix) + Main.getLengthFromCycle(secondCycle, distanceMatrix); //todo: change
                    if (newSum >= sum) {
                        changeInterface.undo(secondCycle, keySecondCycle0, keySecondCycle1);
                    } else {
                        sum = newSum;
                        break outerLoop;
                    }
                    keysSecondCycleOperation2Pointer1++;
                    if (keysSecondCycleOperation2Pointer1 == secondCycleSize) {
                        keysSecondCycleOperation2Pointer0++;
                        keysSecondCycleOperation2Pointer1 = keysSecondCycleOperation2Pointer0 + 1;
                    }
                }
            }
        } while (oldSum != sum);

        List<Map<Integer, Integer>> newCycles = new ArrayList<>();
        newCycles.add(firstCycle);
        newCycles.add(secondCycle);
        return newCycles;
    }

    private static List<Integer> getNumberOfOperations(int firstCycleSize, int secondCycleSize) {
        int twoCyclesOperationsSize = firstCycleSize * secondCycleSize;
        int firstCycleOperationsSize = firstCycleSize * (firstCycleSize -1) / 2;
        int secondCycleOperationsSize = secondCycleSize * (secondCycleSize -1) / 2;
        List<Integer> numberOfOperations = new ArrayList<>();
        for (int i=0; i<twoCyclesOperationsSize; i++) {
            numberOfOperations.add(0);
        }
        for (int i=0; i<firstCycleOperationsSize; i++) {
            numberOfOperations.add(1);
        }
        for (int i=0; i<secondCycleOperationsSize; i++) {
            numberOfOperations.add(2);
        }
        Collections.shuffle(numberOfOperations);
        return numberOfOperations;
    }
}
