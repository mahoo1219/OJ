package Algo.Sort;

public class QuickSort {
    public static void quickSort(int[] array, int left, int right) {
        if (left > right)
            return;
        int l = left, r = right;
        int temp = array[left];
        while (l != r) {
            while (array[r] > temp && l < r) {
                r--;
            }
            while (array[l] <= temp && l < r) {
                l++;
            }
            if (l < r) {
                int t = array[l];
                array[l] = array[r];
                array[r] = t;
            }
        }
        array[left] = array[l];
        array[l] = temp;
        TestHelper.printArray(array);
        quickSort(array, left, l - 1);
        quickSort(array, l + 1, right);
    }

    public static void main(String[] args) {
        int[] array = new int[]{6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        quickSort(array, 0, array.length - 1);
        TestHelper.printArray(array);
    }

    public static void quickSort(Comparable[] array, int left, int right) {
        //if (left >= right) {
        //    return;
        //}
        if (right - left <= 15) {
            InsertSort.insertSort(array);
            return;
        }
        int p = partition(array, left, right);
        quickSort(array, left, p - 1);
        quickSort(array, p + 1, right);
    }

    private static int partition(Comparable[] array, int left, int right) {
        TestHelper.swap(array, left, (int) (Math.random() * (right - left) + left));

        Comparable v = array[left];
        int j = left;
        for (int i = left + 1; i <= right; i++) {
            if (array[i].compareTo(v) < 0) {
                TestHelper.swap(array, j + 1, i);
                j++;
            }
        }
        TestHelper.swap(array, left, j);
        return j;
    }

    private static int partition2(Comparable[] arrary, int left, int right) {
        TestHelper.swap(arrary, left, (int) (Math.random() * (right - left) + 1));
        Comparable v = arrary[left];
        // arr[l+1...i) <= v; arr(j...r] >= v
        int i = left + 1, j = right;
        while (true) {
            while (i <= right && arrary[i].compareTo(v) < 0) i++;
            while (j >= left + 1 && arrary[j].compareTo(v) > 0) j--;
            if (i > j) {
                break;
            }
            TestHelper.swap(arrary, i, j);
            i++;
            j--;
        }
        TestHelper.swap(arrary, left, j);
        return j;
    }
}
