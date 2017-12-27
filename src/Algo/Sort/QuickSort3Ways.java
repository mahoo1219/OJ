package Algo.Sort;

public class QuickSort3Ways {
    public static void queickSort3Ways(Comparable[] array) {
        int n = array.length;
        queickSort3Ways(array, 0, n - 1);
    }

    // 递归使用快速排序,对arr[l...r]的范围进行排序
    private static void queickSort3Ways(Comparable[] array, int l, int r) {
        if (r - l <= 15) {
            InsertSort.insertSort(array);
            return;
        }

        TestHelper.swap(array, l, (int) (Math.random() * (r - l) + l));
        Comparable v = array[l];

        int lt = l; // arr[l+1...lt] < v
        int gt = r + 1; // arr[gt...r] > v
        int i = l + 1; // arr[lt+1...i) == v
        while (i < gt) {
            if (array[i].compareTo(v) < 0) {
                TestHelper.swap(array, i, lt + 1);
                i++;
                lt++;

            } else if (array[i].compareTo(v) > 0) {
                TestHelper.swap(array, i, gt - 1);
                gt--;

            } else {
                i++;
            }
        }
        TestHelper.swap(array, l, lt);
        queickSort3Ways(array, l, lt - 1);
        queickSort3Ways(array, gt, r);
    }
}
