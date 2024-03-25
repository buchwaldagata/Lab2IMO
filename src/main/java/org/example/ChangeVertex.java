package org.example;

import java.util.Map;

public class ChangeVertex implements ChangeInterface {
    public void run(Map<Integer, Integer> cycle, int keyCycle0, int keyCycle1) {
        changeVertices(cycle, keyCycle0, keyCycle1);
    }

    public void undo(Map<Integer, Integer> cycle, int keyCycle0, int keyCycle1) {
        changeVertices(cycle, keyCycle0, keyCycle1);
    }

    private static void changeVertices(Map<Integer, Integer> cycle, int keyCycle0, int keyCycle1) {
        Integer valueCycle0 = cycle.get(keyCycle0);
        Integer valueCycle1 = cycle.get(keyCycle1);

        cycle.replace(keyCycle0, valueCycle1);
        cycle.replace(keyCycle1, valueCycle0);
    }
}