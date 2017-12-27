package HUAWEI;

import java.util.Scanner;

public class Main_6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLong()) {
            long number = scanner.nextLong();
            deal(number);
        }
        scanner.close();
    }

    private static void deal(long number) {
        if (number == 1 || number == 2) {
            System.out.println(number);
        }
        while (number >= 2) {
            for (int i = 2; i <= number; i++) {
                if (number % i == 0) {
                    number /= i;
                    System.out.print(i + " ");
                    break;
                }
            }
        }
    }
}
