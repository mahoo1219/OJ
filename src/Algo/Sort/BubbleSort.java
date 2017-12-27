package Algo.Sort;

import java.util.Scanner;

public class BubbleSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        //int n = 5;
        //int[] array = new int[]{1, 2, 3, 4, 5};
        bubbleSort(array);
        TestHelper.printArray(array);
    }

    public static void bubbleSort(int[] array) {
        int n = array.length;
        boolean flag = false;
        do {
            flag = false;
            for (int i = 0; i < n - 1; i++) {
                if (array[i] < array[i + 1]) {
                    TestHelper.swap(array, i, i + 1);
                    flag = true;
                }
            }
            // 优化, 每一趟Bubble Sort都将最大的元素放在了最后的位置
            // 所以下一次排序, 最后的元素可以不再考虑
            n--;
        } while (flag);
    }

    public static void bubbleSort(Comparable[] array) {
        int n = array.length;
        int newn;// 使用newn进行优化

        do {
            newn = 0;
            for (int i = 1; i < n; i++) {
                if (array[i - 1].compareTo(array[i]) > 0) {
                    TestHelper.swap(array, i - 1, i);
                    // 记录最后一次的交换位置,在此之后的元素在下一轮扫描中均不考虑
                    newn = i;
                }
            }
            n = newn;
        } while (newn > 0);
    }
}
