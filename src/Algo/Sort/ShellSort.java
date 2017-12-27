package Algo.Sort;

public class ShellSort {
    public static void main(String[] args) {
        Integer[] array = TestHelper.generateRandomArray(20, 0, 100);
        TestHelper.printArray(array,20);
        shellSort(array);
        TestHelper.printArray(array,20);
    }

    public static void shellSort(Comparable[] array) {

        int n = array.length;
        int h = 1;
        // 计算 increment sequence: 1,4,13,40,121,364,1093....
        while (h <= n / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                Comparable e = array[i];
                int j = i;
                // 对arr[i], arr[i-h], arr[i-2*h],arr[i-3*h]... 使用插入排序
                for (; j >= h && e.compareTo(array[j - h]) < 0; j -= h) {
                    array[j] = array[j - h];
                }
                array[j] = e;
                //TestHelper.printArray(array);
            }
            h /= 3;
        }
    }
}
