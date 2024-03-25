package org.example;

import java.util.*;

class MovesAlteringSetsOfVerticesFormingTwoCycles {

    public static void run(List<Map<Integer, Integer>> cycles, int keyFirstCycle, int keySecondCycle) {
        changeVerticesInCycles(cycles, keyFirstCycle, keySecondCycle);
    }

    public static void undo(List<Map<Integer, Integer>> cycles, int keyFirstCycle, int keySecondCycle) {
        changeVerticesInCycles(cycles, keyFirstCycle, keySecondCycle);
    }

    private static void changeVerticesInCycles(List<Map<Integer, Integer>> cycles, int keyFirstCycle, int keySecondCycle) {
        Map<Integer, Integer> firstCycle = cycles.get(0);
        Map<Integer, Integer> secondCycle = cycles.get(1);
        Integer valueOldFirstCycle = firstCycle.get(keyFirstCycle);
        Integer valueOldSecondCycle = secondCycle.get(keySecondCycle);

        firstCycle.replace(keyFirstCycle, valueOldSecondCycle);
        secondCycle.replace(keySecondCycle, valueOldFirstCycle);
    }
}