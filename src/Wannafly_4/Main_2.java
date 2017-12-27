package Wannafly_4;

import java.util.Arrays;
import java.util.Scanner;

public class Main_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int left = scanner.nextInt();
        int right = scanner.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        deal(array, left, right);
    }

    public static void deal(int[] array, int left, int right) {
        int count = (right - left) / 2;
        int mod = (int)Math.pow(10, 9) + 7;
        int[][] result = new int[right - left + 1][right - left + 1];
        Arrays.fill(result, -1);
        for (int i = 0; i < right - 1; i++) {
            result[i][i + 1] = array[left + i] ^ array[left + i + 1];
        }
        for (int j = left; j < count; j += 2) {
            for (int i = left; i < right - 1; i++) {
                result[i][i + 2 * j] = result[i][i + j] ^ result[i + j + 1][i + 2 * j] % mod;
            }
        }
        System.out.println(result[left][right]);
    }
}
