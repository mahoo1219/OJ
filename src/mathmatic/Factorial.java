package mathmatic;

import java.util.ArrayList;
import java.util.Scanner;

public class Factorial {
    private static int currentNumber = 1;

    private static ArrayList<Integer> result = new ArrayList<>(10);

    static {
        result.add(1); //0! = 1
    }

    public static void main(String[] args) {
        int num = 0;
        int col = 1;
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            num = sc.nextInt();
            deal(num);
            for (int i = result.size() - 1; i >= 0; i--, col++) {
                System.out.print(result.get(i));
                if (col % 3 == 0)
                    System.out.print(" ");
                if (col == 39) {
                    System.out.println();
                    col = 0;
                }
            }
            System.out.println("\n" + result.size());
        }
    }

    public static void deal(int number) {
        while (currentNumber <= number) {
            for (int i = 0; i < result.size(); i++) {
                result.set(i, result.get(i) * currentNumber);
            }
            currentNumber++;
            int carray = 0, digit = 0, remainder = 0;
            for (int i = 0; i < result.size(); i++) {
                digit = result.get(i) + carray;
                carray = digit / 10;
                result.set(i, digit % 10);
            }
            while (carray != 0) {
                result.add(carray % 10);
                carray /= 10;
            }
        }
    }
}
