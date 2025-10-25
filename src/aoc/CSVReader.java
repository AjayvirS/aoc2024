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

    public TwoDimensionalList<Integer> readCSVAsMatix(String filePath, String delimiter) {

        String line;
        ArrayList<int[]> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                String[] stringValues = line.split(delimiter);
                int[] intValues = new int[stringValues.length];
                for (int i = 0; i < stringValues.length; i++) {
                    intValues[i] = Integer.parseInt(stringValues[i]);
                }
                rows.add(intValues);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new TwoDimensionalList<>(rows);
    }

}
