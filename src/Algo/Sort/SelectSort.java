package Algo.Sort;

public class SelectSort {
    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);
        //int n = scanner.nextInt();
        //int[] array = new int[n];
        //for (int i = 0; i < n; i++) {
        //    array[i] = scanner.nextInt();
        //}
        //int n = 500;
        //Integer[] array = TestHelper.generateRandomArray(n, -1000, 1000);
        //selectSort(array);
        //TestHelper.printArray(array);

        int N = 20000;
        Integer[] array = TestHelper.generateRandomArray(N, -100000, 100000);
        TestHelper.testSort("Algo.Sort.SelectSort", array);
    }

    public static void selectSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            //寻找最小值。
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            if (i != minIndex)
                TestHelper.swap(array, i, minIndex);
        }
    }

    public static void selectSort(Comparable[] array) {
        int n = array.length;
        for (int i = 0; i < n; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j].compareTo(array[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            if (i != minIndex)
                TestHelper.swap(array, i, minIndex);
        }
    }
}
