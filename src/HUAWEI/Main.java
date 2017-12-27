package HUAWEI;

import java.util.Scanner;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            TreeSet<Integer> set = new TreeSet<>();
            int n = scanner.nextInt();
            if (n > 0) {
                for (int i = 0; i <n; i++) {
                    set.add(scanner.nextInt());
                }
            }
            for (Integer i : set) {
                System.out.println(i);
            }
        }
    }

}
