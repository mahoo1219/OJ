package HUAWEI;

import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

public class Main_14 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = Integer.parseInt(scanner.nextLine());
        String[] strings = new String[number];
        for (int i = 0; i < number; i++) {
            strings[i] = scanner.nextLine();
        }
        Arrays.sort(strings);
        for (String s : strings)
            System.out.println(s);
    }

    public static void sort(String[] strings) {
        for (int i = 0; i < strings.length; i++) {
            for (int j = i + 1; j < strings.length; j++) {
                if (strings[i].compareTo(strings[j]) > 0) {
                    String temp = strings[i];
                    strings[i] = strings[j];
                    strings[j] = temp;
                }
            }
        }
    }
}
