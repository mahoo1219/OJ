package HUAWEI;

import java.util.Scanner;

public class Main_9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int[] array = new int[10];
        int digit = 0;
        int result = 0;
        while (number != 0) {
            digit = number % 10;
            number /= 10;

            if (array[digit] == 0) {
                array[digit] = 1;
                //System.out.print(digit); //这里是有问题的， Input:10 ; Output: 01.
                result = result * 10 + digit;
            }
        }
        System.out.println(result);
    }
}
