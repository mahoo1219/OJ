package Algo.Sort;

public class InversionCount {

    public static int solve(Comparable[] arr) {
        int n = arr.length;
        Comparable[] aux = new Comparable[n];
        return inversionCount(arr, aux, 0, n - 1);
    }

    private static int inversionCount(Comparable[] arr, Comparable[] aux, int l, int r) {
        assert l <= r;
        if (l >= r)
            return 0;
        int mid = (l + r) / 2;
        // 求出 arr[l...mid] 范围的逆序数
        int ret1 = inversionCount(arr, aux, l, mid);
        // 求出 arr[mid+1...r] 范围的逆序数
        int ret2 = inversionCount(arr, aux, mid + 1, r);
        return ret1 + ret2 + merge(arr, aux, l, mid, r);
    }

    private static int merge(Comparable[] arr, Comparable[] aux, int l, int mid, int r) {
        System.arraycopy(arr, l, aux, l, r - l + 1);
        int count = 0;
        int i = l, j = mid + 1, k = l;
        while (k <= r) {
            if (i > mid) {
                arr[k++] = aux[j++];
            } else if (j > r) {
                arr[k++] = aux[i++];
            } else if (aux[i].compareTo(aux[j]) <= 0) { // 左半部分所指元素 <= 右半部分所指元素
                arr[k++] = aux[i++];
            } else if (aux[i].compareTo(aux[j]) > 0) { // 右半部分所指元素 < 左半部分所指元素
                arr[k++] = aux[j++];
                // 此时, 因为右半部分k所指的元素小
                // 这个元素和左半部分的所有未处理的元素都构成了逆序数对
                // 左半部分此时未处理的元素个数为 mid - j + 1
                count += mid - i + 1;
            }
        }
        return count;
    }

    // 测试 InversionCount
    public static void main(String[] args) {

        int N = 1000000;

        // 测试1: 测试随机数组
        Integer[] arr = SortHelper.generateRandomArray(N, 0, 100000);
        System.out.println("Test Inversion Count for Random Array, n = " + N + " :" + solve(arr));

        // 测试2: 测试完全有序的数组
        // 结果应该为0
        arr = SortHelper.generateOrderedArray(N);
        System.out.println("Test Inversion Count for Ordered Array, n = " + N + " :" + solve(arr));

        // 测试3: 测试完全逆序的数组
        // 结果应改为 N*(N-1)/2
        arr = SortHelper.generateInversedArray(N);
        System.out.println("Test Inversion Count for Inversed Array, n = " + N + " :" + solve(arr));
        return;
    }
}
