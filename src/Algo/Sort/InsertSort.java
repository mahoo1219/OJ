package Algo.Sort;

public class InsertSort {
    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);
        //int n = scanner.nextInt();
        //int[] array = new int[n];
        //for (int i = 0; i < n; i++) {
        //    array[i] = scanner.nextInt();
        //}
        //int n = 5;
        //int[] array = new int[]{1, 2, 3, 4, 5};
        //insertSort(array);
        //TestHelper.printArray(array);

        int N = 20000;
        Integer[] arr = TestHelper.generateRandomArray(N, 0, 1000000);
        TestHelper.testSort("Algo.Sort.InsertSort", arr);
    }

    // advance
    public static void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i], j;
            for (j = i - 1; j >= 0 && (array[j] > temp); j--) {
                array[j + 1] = array[j];
            }
            array[++j] = temp;
        }
    }

    public static void insertSort(Comparable[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            //for (int j = i; j > 0 && array[j].compareTo(array[j - 1]) < 0; j--) {
            //    TestHelper.swap(array, j, j - 1);
            //}
            Comparable e = array[i];
            int j = i;
            for (; j > 0 && array[j - 1].compareTo(e) > 0; j--)
                array[j] = array[j - 1];
            array[j] = e;
        }
    }

    // 对arr[l...r]的区间使用InsertionSort排序
    public static void insertSort(Comparable[] array, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            Comparable e = array[i];
            int j = i;
            for (; j > left && array[j].compareTo(array[j - 1]) < 0; j--) {
                array[j] = array[j - 1];
            }
            array[j] = e;
        }
    }
}
