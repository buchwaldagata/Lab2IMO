package org.example;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class StartingSolution {
    private List<Integer> cycle;
    private List<String> preparedLinesFromFile;

    StartingSolution(String path){
        prepareLinesFromFile(path);
        getListOfVertex();
    }

    public List<Integer> getCycle() {return cycle;};


    private void prepareLinesFromFile(String path) {
        preparedLinesFromFile = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                if (columns.length > 0) {
                    preparedLinesFromFile.add(columns[0].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getListOfVertex() {
        cycle = new ArrayList<>();
        for (String s : preparedLinesFromFile) {
            Integer x = Integer.parseInt(s);
            cycle.add(x);
        }
    }
}
