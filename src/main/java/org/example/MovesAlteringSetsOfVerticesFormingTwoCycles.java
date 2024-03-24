package org.example;

import java.util.*;

class MovesAlteringSetsOfVerticesFormingTwoCycles {

    public List<Map<Integer, Integer>> run(List<Map<Integer, Integer>>  cycles, Long[][] distanceMatrix) {
        Map<Integer, Integer> firstCycle = cycles.get(0);
        Map<Integer, Integer> secondCycle = cycles.get(1);
        firstCycle.remove(firstCycle.keySet().size() - 1);
        secondCycle.remove(secondCycle.keySet().size() - 1);
        Map<Integer, Integer> newFirstCycle = new HashMap<>();
        Map<Integer, Integer> newSecondCycle = new HashMap<>();

        List<Integer> keysOldFirstCycle = new ArrayList<>(firstCycle.keySet());
        List<Integer> keysOldSecondCycle = new ArrayList<>(secondCycle.keySet());
        //todo dodac randomizacje
        Collections.shuffle(keysOldFirstCycle);
        Collections.shuffle(keysOldSecondCycle);
        Long sum = Main.getLengthFromCycle(firstCycle, distanceMatrix) + Main.getLengthFromCycle(secondCycle, distanceMatrix);

        Long secondSum;
        do {
            secondSum = sum;
            outerLoop:
            for (int i = 0; i < keysOldFirstCycle.size(); i++) {// todo czy dobrze z tym -1
                for (int j = 0; j < keysOldSecondCycle.size(); j++) {
                    System.out.println("Stara suma " + sum);
                    Integer keyOldFirst = keysOldFirstCycle.get(i);
                    Integer valueOldFirst = firstCycle.get(keyOldFirst);
                    Integer keyOldSecond = keysOldSecondCycle.get(j);
                    Integer valueOldSecond = secondCycle.get(keyOldSecond);

                    Integer tmpkeyOldFirst = new Integer(keyOldFirst);
                    Integer tmpvalueOldFirst = new Integer(valueOldFirst);
                    Integer tmpkeyOldSecond = new Integer(keyOldSecond);
                    Integer tmpvalueOldSecond = new Integer(valueOldSecond);


                    firstCycle.remove(keyOldFirst);
                    secondCycle.remove(keyOldSecond);

                    firstCycle.put(tmpkeyOldFirst, tmpvalueOldSecond);
                    secondCycle.put(tmpkeyOldSecond, tmpvalueOldFirst);

                    Long newSum =
                            Main.getLengthFromCycle(firstCycle, distanceMatrix) + Main.getLengthFromCycle(secondCycle,
                                    distanceMatrix);

                    if (newSum >= sum) {
                        firstCycle.remove(tmpkeyOldFirst);
                        secondCycle.remove(tmpkeyOldSecond);
                        firstCycle.put(keyOldFirst, valueOldFirst);
                        secondCycle.put(keyOldSecond, valueOldSecond);
                    } else {
                        System.out.println("Nowa suma " + newSum);
                        sum = newSum;
                        break outerLoop;
                    }
                }
            }


        }
        while (secondSum != sum);


        List<Map<Integer, Integer>> newCycles = new ArrayList<>();
        newCycles.add(newFirstCycle);
        newCycles.add(newSecondCycle);
        return newCycles;

    }

}
