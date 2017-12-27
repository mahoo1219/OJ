package HUAWEI;

import java.util.Scanner;

public class Main_7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double number = scanner.nextDouble();

        System.out.println((int) (number + 0.5));
        System.out.println(Math.round(number));
    }
}
