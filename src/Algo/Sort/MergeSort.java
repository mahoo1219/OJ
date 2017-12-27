package Algo.Sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int n = 100000;
        Integer[] array = TestHelper.generateNearlyOrderedArray(n, 0);
        Integer[] array2 = Arrays.copyOf(array, n);
        //mergeSort(array);
        TestHelper.testSort("Algo.Sort.MergeSort", array);
        TestHelper.testSort("Algo.Sort.InsertSort", array2);
    }

    public static void mergeSort(Comparable[] array) {
        int n = array.length;
        mergeSort(array, 0, n - 1);
        //mergeSortBU(array);
    }


    // 不需要随机选择元素，可以对链表进行操作。
    public static void mergeSortBU(Comparable[] array) {
        int n = array.length;
        // Merge Algo.Sort Bottom Up 无优化版本
        //for (int sz = 1; sz < n; sz += sz) { // 每次归并的元素个数
        //    // 确保有两个部分进行合并，所以需要 i < n -sz.
        //    for (int i = 0; i < n - sz; i += sz + sz) { //起始元素位置
        //        // 对 arr[i...i+sz-1] 和 arr[i+sz...i+2*sz-1] 进行归并
        //        merge(array, i, i + sz - 1, Math.min(i + sz + sz - 1, n - 1));
        //    }
        //}

        // Merge Algo.Sort Bottom Up 优化
        // 对于小数组, 使用插入排序优化
        for (int i = 0; i < n; i += 16) {
            InsertSort.insertSort(array, i, Math.min(i + 15, n - 1));
        }
        for (int sz = 16; sz < n; sz += sz) {
            for (int i = 0; i < n - sz; i += sz + sz) {
                // 对于arr[mid] <= arr[mid+1]的情况,不进行merge
                if (array[i + sz - 1].compareTo(array[i + sz]) > 0)
                    merge(array, i, i + sz - 1, Math.min(i + sz + sz - 1, n - 1));
            }
        }
    }

    // 递归使用归并排序,对arr[l...r]的范围进行排序. 前闭后闭
    private static void mergeSort(Comparable[] array, int left, int right) {
        // 优化2：对于小规模数组，使用插入排序
        //if (right - left <= 15) {
        //    InsertSort.insertSort(array,left,right);
        //    return;
        //}
        if (left >= right) //只有一个元素
            return;
        int mid = (left + right) / 2;
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);
        // 优化1： 对于array[mid] <= array[mid+1]的情况，不进行merge
        // 对于近乎有序的数组非常有效，但对于一般情况，有一定的性能损失。
        if (array[mid].compareTo(array[mid + 1]) > 0)
            merge(array, left, mid, right);
    }

    // 将arr[l...mid]和arr[mid+1...r]两部分进行归并
    private static void merge(Comparable[] array, int left, int mid, int right) {
        Comparable[] temp = Arrays.copyOfRange(array, left, right + 1); // 注意偏移量

        // 初始化，i 指向左半部分的起始索引位置 left; j 指向右半部分起始位置 mid + 1;
        int i = left, j = mid + 1;
        // k: 临时数组的存放索引
        for (int k = left; k <= right; k++) {
            //先判断索引的合法性
            if (i > mid) { // 如果左半部分元素已经全部处理完毕
                array[k] = temp[j - left];
                j++;

            } else if (j > right) { // 如果右半部分元素已经全部处理完毕
                array[k] = temp[i - left];
                i++;

            } else if (temp[i - left].compareTo(temp[j - left]) < 0) { // 左半部分所指元素 < 右半部分所指元素
                array[k] = temp[i - left];
                i++;

            } else { // 左半部分所指元素 >= 右半部分所指元素
                array[k] = temp[j - left];
                j++;
            }
        }
    }
}
