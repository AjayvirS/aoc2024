package aoc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class CSVReader {

    public TwoLists<Integer> readCSV(String filePath, String delimiter) {

        String line;
        TwoLists<Integer> values = new TwoLists<>(new ArrayList<>(), new ArrayList<>());

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                values.first().add(Integer.valueOf(line.split(delimiter)[0]));
                values.second().add(Integer.valueOf(line.split(delimiter)[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return values;
    }

}
