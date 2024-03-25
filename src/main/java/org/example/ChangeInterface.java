package org.example;

import java.util.Map;

public interface ChangeInterface {
    public void run(Map<Integer, Integer> cycle, int keyCycle0, int keyCycle1);
    public void undo(Map<Integer, Integer> cycle, int keyCycle0, int keyCycle1);
}