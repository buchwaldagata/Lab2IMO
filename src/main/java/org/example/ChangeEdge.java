package org.example;

import java.util.Map;

public class ChangeEdge implements ChangeInterface{
    public void run(Map<Integer, Integer> cycle, int keyCycle0, int keyCycle1) {
        replaceEdges(cycle, keyCycle0, keyCycle1);
    }

    public void undo(Map<Integer, Integer> cycle, int keyCycle0, int keyCycle1) {
        replaceEdges(cycle, keyCycle0, keyCycle1);
    }

    private void replaceEdges(Map<Integer, Integer> cycle, int keyCycle0, int keyCycle1) {
        for(int i=0; i<(keyCycle1-keyCycle0)/2; i++) {
            int key0 = keyCycle0+i+1;
            int key1 = keyCycle1-i;
            int oldValue0 = cycle.get(key0);
            int oldValue1 = cycle.get(key1);
            cycle.replace(key0, oldValue1);
            cycle.replace(key1, oldValue0);
        }
    }
}