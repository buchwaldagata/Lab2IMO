package org.example;

import java.util.*;

class intraRouteMovements {
    public List<Map<Integer, Integer>> runIntraRouteMovements(List<Map<Integer, Integer>>  cycles, Long[][] distanceMatrix) {
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
    Long sum = Main.getLengthFromCycle(firstCycle, distanceMatrix);
    Long sum2 = Main.getLengthFromCycle(secondCycle, distanceMatrix);
    Long secondSum;
    do {
        secondSum = sum;
        outerLoop:
        for (int i = 0; i < keysOldFirstCycle.size(); i++) {// todo czy dobrze z tym -1
            for (int j = 0; j < keysOldFirstCycle.size(); j++) {
                System.out.println("Stara suma " + sum);
                Integer keyOldFirst = keysOldFirstCycle.get(i);
                Integer valueOldFirst = firstCycle.get(keyOldFirst);
                Integer keyOldSecond = keysOldFirstCycle.get(j);
                Integer valueOldSecond = firstCycle.get(keyOldSecond);

                Integer tmpkeyOldFirst = new Integer(keyOldFirst);
                Integer tmpvalueOldFirst = new Integer(valueOldFirst);
                Integer tmpkeyOldSecond = new Integer(keyOldSecond);
                Integer tmpvalueOldSecond = new Integer(valueOldSecond);


                firstCycle.remove(keyOldFirst);
                firstCycle.remove(keyOldSecond);

                firstCycle.put(tmpkeyOldFirst, tmpvalueOldSecond);
                firstCycle.put(tmpkeyOldSecond, tmpvalueOldFirst);



                Long newSum =
                        Main.getLengthFromCycle(firstCycle, distanceMatrix);

                if (newSum >= sum) {
                    firstCycle.remove(tmpkeyOldFirst);
                    firstCycle.remove(tmpkeyOldSecond);
                    firstCycle.put(keyOldFirst, valueOldFirst);
                    firstCycle.put(keyOldSecond, valueOldSecond);
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
