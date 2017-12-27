package Wannafly_4;

import java.util.Arrays;
import java.util.Scanner;

public class Main_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int x = scanner.nextInt();

        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        Arrays.sort(array);
        deal(array, x);
    }

    public static void deal(int[] array, int x) {
        int a;
        for (int i = 0; i < array.length; i++) {
            a = array[i];
            if ((Arrays.binarySearch(array, -2 * a * x) >= 0) && (Arrays.binarySearch(array, a * x * (2 - a)) >= 0)) {
                System.out.println("YES");
                return;
            }
        }
        System.out.println("NO");
    }
}
