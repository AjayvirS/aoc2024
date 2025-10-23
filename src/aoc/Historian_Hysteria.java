package aoc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Historian_Hysteria {


    public static void solveA(TwoLists<Integer> data) {
        ArrayList<Integer> sorted_first = new ArrayList<>(data.first());
        Collections.sort(sorted_first);

        ArrayList<Integer> sorted_second = new ArrayList<>(data.second());
        Collections.sort(sorted_second);
        int total_distance = 0;
        int i = 0;
        for(int elem: sorted_first){
            total_distance+=(Math.abs(elem-sorted_second.get(i)));
            i++;

        }

        System.out.println(total_distance);
        
    }

    public static void solveB(TwoLists<Integer> data){
        HashMap<Integer, Integer> dataSecond = new HashMap<>();

        for(int elem : data.second()){
            dataSecond.putIfAbsent(elem, 0);
            dataSecond.put(elem, dataSecond.get(elem)+1);
        }
        int total_distance = 0;
        for(int elem: data.first()){
            total_distance = total_distance +  elem*dataSecond.getOrDefault(elem, 0);
        }

        System.out.println(total_distance);

    }

}