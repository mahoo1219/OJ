package Algo.Sort;

// 不需要额外空间，在原空间进行排序
public class HeapSort {
    public static void heapSort(Comparable[] arr) {
        int n = arr.length;
        // 注意，此时我们的堆是从0开始索引的
        // 从(最后一个元素的索引-1)/2开始
        // 最后一个元素的索引 = n-1
        // left child: (i) = 2* i + 1
        // right child:(i) = 2*i + 2
        for (int i = (n - 1 - 1) / 2; i >= 0; i--) {
            shiftDown(arr, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            MySort.swap(arr, 0, i); // 将当前最大元素放到最后。
            shiftDown(arr, i, 0);
        }
    }

    private static void shiftDown(Comparable[] arr, int n, int k) {
        Comparable e = arr[k];
        while (2 * k + 1 < n) {
            int j = 2 * k + 1;
            if (j + 1 < n && arr[j + 1].compareTo(arr[j]) > 0)
                j++;
            if (e.compareTo(arr[j]) >= 0)
                break;
            arr[k] = arr[j];
            k = j;
        }
        arr[k] = e;
    }

    public static void main(String[] args) {
        int N = 1000000;
        Integer[] arr = SortHelper.generateRandomArray(N, 0, N);
        SortHelper.testSort("Algo.Sort.HeapSort", "heapSort", arr);
    }
}
