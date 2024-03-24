package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.util.Pair;
public class RandomStart {

    private List<Integer> cycleA;
    private List<Integer> cycleB;
    RandomStart(){
        cycleA=new ArrayList<>();
        cycleB=new ArrayList<>();
        getRandomSolution();
    }

    public Pair<List<Integer>,List<Integer>> getCycles(){
        return new Pair<>(cycleA, cycleB);
    }
    private void getRandomSolution(){
        List<Integer> vertices = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            vertices.add(i);
        }
        for(int i=vertices.size()-1; i>=0 ; i-=2 ){
            int indexA = getRandomNumber(i);
            cycleA.add(vertices.get(indexA));
            vertices.remove(indexA);
            int indexB = getRandomNumber(i-1);
            cycleB.add(vertices.get(indexB));
            vertices.remove(indexB);
        }
    }

    private int getRandomNumber(int max){
        Random random = new Random();
        return random.nextInt(max + 1);
    }

}
