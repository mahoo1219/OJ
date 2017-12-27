package Wannafly_4;

import java.util.Arrays;
import java.util.Scanner;

public class Main_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        String[] array = new String[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextLine();
        }

        deal(array, n);
    }

    public static void deal(String[] array, int n) {
        int currentPosition = 0;
        int count = 0;
        int[] position = new int[n];
        int[] lastPosition = new int[n];
        int contains = 0;
        Arrays.fill(position, -1);
        Arrays.fill(lastPosition, -1);

        for (int i = 0; i < n; i++) {
            position[i] = array[i].indexOf("W");
            lastPosition[i] = array[i].lastIndexOf("W");
            if (position[i] != -1 || lastPosition[i] != -1)
                contains++;
        }

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                if (currentPosition < lastPosition[i]) {
                    count += lastPosition[i] - currentPosition;
                    currentPosition = lastPosition[i];
                }
                if ((i != n - 1) && lastPosition[i] < lastPosition[i + 1]) {
                    count += lastPosition[i + 1] - currentPosition;
                    currentPosition = lastPosition[i + 1];
                }

            } else {
                if ((currentPosition > position[i]) && position[i] != -1) {
                    count += currentPosition - position[i];
                    currentPosition = position[i];
                }
                if ((i != n - 1) && position[i + 1] != -1 && position[i + 1] < position[i]) {
                    count += currentPosition - position[i + 1];
                    currentPosition = position[i + 1];
                }
            }
        }
        System.out.println(count + contains - 1);
    }
}

