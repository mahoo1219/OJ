package HUAWEI;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main_8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int count = scanner.nextInt();
        int index = 0, number = 0;
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        while (scanner.hasNext()) {
            index = scanner.nextInt();
            number = scanner.nextInt();
            treeMap.put(index, treeMap.containsKey(index) ? treeMap.get(index) + number : number);
        }
        treeMap.forEach((key, value) -> System.out.println(key + " " + value));
        System.out.println();
        for (int key : treeMap.keySet()) {
            System.out.println(key + " " + treeMap.get(key));
        }
        System.out.println();
        for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
