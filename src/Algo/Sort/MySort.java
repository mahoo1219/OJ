package Algo.Sort;

import java.util.Arrays;

public class MySort {
    public static void main(String[] args) {
        int N = 100000;
        Integer[] arr = SortHelper.generateRandomArray(N, 0, 1000);
        Integer[] arr2 = SortHelper.generateNearlyOrderedArray(N, 2);
        //SortHelper.printArray(arr, 10);

        SortHelper.testSort("Algo.Sort.MySort", "quickSort", arr);
        //SortHelper.testSort("Algo.Sort.MySort", "insertSort", arr2);

        //SortHelper.printArray(arr, 10);
    }

    public static void bubbleSort(Comparable[] arr) {
        assert arr != null;
        int n = arr.length;
        int lastSwapIndex;
        do { // 要进行n-1次比较。
            lastSwapIndex = 0;
            for (int j = 0; j < n - 1; j++) { // 每次比较前 n-i 个元素
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    swap(arr, j, j + 1);
                    lastSwapIndex = j + 1;
                }
            }
            n = lastSwapIndex;
        } while (lastSwapIndex > 0);
    }

    public static void selectSort(Comparable[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) { // n - 1 趟选择
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            if (i != minIndex)
                swap(arr, i, minIndex);
        }
    }

    public static void insertSort(Comparable[] arr) {
        int n = arr.length;
        //for (int i = 1; i < n; i++) { // 对于下标 1..后面的 n - 1 个元素
        //    Comparable temp = arr[i];
        //    int j = i;
        //    for (; j > 0 && arr[j - 1].compareTo(temp) > 0; j--) {
        //        arr[j] = arr[j - 1];
        //    }
        //    arr[j] = temp;
        //}
        insertSort(arr, 0, n - 1);
    }

    public static void insertSort(Comparable[] arr, int l, int r) {
        int n = r - l + 1;
        for (int i = l + 1; i < n; i++) {
            Comparable temp = arr[i];
            int j = i;
            while (j > l && arr[j - 1].compareTo(temp) > 0) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    public static void mergeSort(Comparable[] arr) {
        int n = arr.length;
        //mergeSortR(arr, 0, n - 1);
        mergeSortBU(arr, n);
    }

    private static void mergeSortR(Comparable[] arr, int l, int r) {
        assert l <= r;
        if (l - r <= 15) {
            insertSort(arr, l, r);
            return;
        }

        int mid = (l + r) / 2;
        mergeSortR(arr, l, mid);
        mergeSortR(arr, mid + 1, r);
        if (arr[mid].compareTo(arr[mid + 1]) > 0)
            merge(arr, l, mid, r);
    }

    private static void mergeSortBU(Comparable[] arr, int n) {
        // Merge Algo.Sort Bottom Up 优化
        // 对于小数组, 使用插入排序优化
        for (int i = 0; i < n; i += 16) {
            insertSort(arr, i, Math.min(i + 15, n - 1));
        }
        for (int sz = 16; sz < n; sz += sz) { //每次归并个数：1,2,4,8...n
            // 保证边界，i+sz < n
            for (int i = 0; i < n - sz; i += sz + sz) { // 归并元素位置
                //arr[i,i+sz-1] , arr[i+sz, i+2*sz -1]
                //必须保证i+2*sz-1 < n-1
                if (arr[i + sz - 1].compareTo(arr[i + sz]) > 0)
                    merge(arr, i, i + sz - 1, Math.min(i + sz + sz - 1, n - 1));
            }
        }
    }

    // 这里aux 可以一次分配，作为参数传入函数中
    private static void merge(Comparable[] arr, int l, int mid, int r) {
        Comparable[] aux = Arrays.copyOfRange(arr, l, r + 1);
        int i = 0, j = mid + 1 - l, k = l; // i: 左下标 j: 右下标 k: arr下标
        while (k <= r) {
            if (i > mid) {
                arr[k++] = aux[j++];
            } else if (j > r - l) { // 注意这里不是 j > r
                arr[k++] = aux[i++];
            } else if (aux[i].compareTo(aux[j]) <= 0) {
                arr[k++] = aux[i++];
            } else if (aux[i].compareTo(aux[j]) > 0) {
                arr[k++] = aux[j++];
            }
        }
    }

    public static void shellSort(Comparable[] arr) {
        int n = arr.length;
        int h = 1;
        // 计算 increment sequence: 1,4,13,40,121,364,1093....
        while (h <= n / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                Comparable e = arr[i];
                int j = i;
                // 对arr[i], arr[i-h], arr[i-2*h],arr[i-3*h]... 使用插入排序
                for (; j >= h && e.compareTo(arr[j - h]) < 0; j -= h) {
                    arr[j] = arr[j - h];
                }
                arr[j] = e;
            }
            h = h / 3;
        }
    }

    public static void quickSort(Comparable[] arr) {
        int n = arr.length;
        //quickSort1(arr, 0, n - 1);
        quickSort3Ways(arr, 0, n - 1);
    }

    // 递归使用快速排序,对arr[l...r]的范围进行排序
    private static void quickSort1(Comparable[] arr, int l, int r) {
        if (r - l <= 15) {
            insertSort(arr, l, r);
            return;
        }
        int p = partition2(arr, l, r);
        quickSort1(arr, l, p - 1);
        quickSort1(arr, p + 1, r);
    }

    // 对arr[l...r]部分进行partition操作
    // 返回p, 使得arr[l...p-1] < arr[p] ; arr[p+1...r] > arr[p]
    private static int partition(Comparable[] arr, int l, int r) {
        // 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
        swap(arr, l, (int) Math.random() * (r - l + 1) + l);
        Comparable v = arr[l];
        int j = l;  // arr[l+1,j]<v  arr[j+1,i)>v
        for (int i = l + 1; i <= r; i++) {
            if (v.compareTo(arr[i]) > 0) {
                j++;
                swap(arr, j, i);
            }
        }
        swap(arr, l, j);
        return j;
    }

    //对arr[l...r]部分进行partition操作
    private static int partition2(Comparable[] arr, int l, int r) {
        swap(arr, l, (int) Math.random() * (r - l + 1) + l);
        Comparable v = arr[l];
        int i = l + 1, j = r;  // arr[l+1,i]<=v  arr(j,r]>=v
        while (i <= j) {
            while (v.compareTo(arr[i]) > 0) {
                i++;
            }
            while (v.compareTo(arr[j]) < 0) {
                j--;
            }
            swap(arr, i++, j--);
        }
        swap(arr, l, j);
        return j;
    }

    public static void quickSort3Ways(Comparable[] arr, int l, int r) {
        if (r - l <= 15) {
            insertSort(arr, l, r);
            return;
        }

        swap(arr, l, (int) Math.random() * (r - l + 1) + l);
        Comparable v = arr[l];
        // arr[l+1,lt]<v  arr[lt+1,i)==v   arr[gt,r]>v
        int lt = l, gt = r + 1, i = l + 1;
        while (i < gt) {
            if (v.compareTo(arr[i]) > 0) {
                swap(arr, ++lt, i++);
            } else if (v.compareTo(arr[i]) < 0) {
                swap(arr, i, --gt);
            } else i++;
        }
        swap(arr, l, lt);
        quickSort3Ways(arr, l, lt - 1);
        quickSort3Ways(arr, gt, r);
    }

    public static void swap(Comparable[] arr, int l, int r) {
        Comparable temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }
}
