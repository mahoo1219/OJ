package Algo.Sort;

public class Selection {
    public static Comparable solve(Comparable[] arr, int k) {
        int n = arr.length;
        assert k >= 0 && k < n;
        return solve(arr, 0, n - 1, k);
    }

    private static Comparable solve(Comparable[] arr, int l, int r, int k) {
        if (l == r) {
            return arr[l];
        }
        int p = partition(arr, l, r);
        if (k == p) {
            return arr[p];
        } else if (k < p) {
            return solve(arr, l, p - 1, k);
        } else {
            return solve(arr, p + 1, r, k);
        }
    }


    private static int partition(Comparable[] arr, int l, int r) {
        swap(arr, l, (int) Math.random() * (r - l + 1) + l);
        Comparable e = arr[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (e.compareTo(arr[i]) > 0) {
                swap(arr, ++j, i);
            }
        }
        swap(arr, l, j);
        return j;
    }

    public static void swap(Comparable[] arr, int l, int r) {
        Comparable temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }

    // 测试 Selection
    public static void main(String[] args) {

        // 生成一个大小为n, 包含0...n-1这n个元素的随机数组arr
        int N = 10000;
        Integer[] arr = SortHelper.generateOrderedArray(N);
        SortHelper.shuffleArray(arr);

        // 验证selection算法, 对arr数组求第i小元素, 应该为i
        for (int i = 0; i < N; i++) {
            assert solve(arr, i).equals(i);
        }
        System.out.println("test complete.");
    }
}
