package Algo.BSTree;

public class BinarySearch {
    public static int binarySearch(Comparable[] arr, Comparable target) {
        int n = arr.length;
        return binarySearch(arr, 0, n - 1, target);
    }

    // 二分查找法, 在有序数组arr中, 查找target
    // 如果找到target, 返回第一个target相应的索引index
    // 如果没有找到target, 返回比target小的最大值相应的索引, 如果这个最大值有多个, 返回最大索引
    // 如果这个target比整个数组的最小元素值还要小, 则不存在这个target的floor值, 返回-1
    public static int floor(Comparable[] arr, Comparable target) {
        // 寻找比target小的最大索引
        int l = -1, r = arr.length - 1; //闭区间[0,n-1]
        while (l < r) {
            int mid = l + (r - l + 1) / 2; // 使用向上取整避免死循环,以免当r == l +1时, arr[mid]<target,mid = l+(r-l)/2 --> mid = l;
            if (arr[mid].compareTo(target) >= 0)
                r = mid - 1;
            else l = mid;
        }
        assert l == r;
        // 如果该索引+1就是target本身, 该索引+1即为返回值
        if (l + 1 < arr.length && arr[l + 1] == target)
            return l + 1;
        // 否则, 该索引即为返回值
        return l;
    }

    // 二分查找法, 在有序数组arr中, 查找target
    // 如果找到target, 返回最后一个target相应的索引index
    // 如果没有找到target, 返回比target大的最小值相应的索引, 如果这个最小值有多个, 返回最小的索引
    // 如果这个target比整个数组的最大元素值还要大, 则不存在这个target的ceil值, 返回整个数组元素个数n
    public static int ceil(Comparable[] arr, Comparable target) {
        // 寻找比target大的最小索引值
        int l = 0, r = arr.length - 1;
        while (l < r) {
            // 使用普通的向下取整即可避免死循环
            int mid = l + (r - l) / 2;
            if (arr[mid].compareTo(target) <= 0)
                l = mid + 1;
            else r = mid;
        }
        assert l == r;
        if (r - 1 >= 0 && arr[r - 1] == target)
            return r - 1;
        return r;
    }

    // 二分查找法, 实现lower_bound
    // 即在一个有序数组arr中, 寻找  大于等于  target的元素的第一个索引
    // 如果存在, 则返回相应的索引index
    // 否则, 返回arr的元素个数 n
    public static int lower_bound(Comparable[] arr, Comparable target) {
        if (arr == null)
            throw new IllegalArgumentException("arr can not be null.");
        int l = 0, r = arr.length;// 可能大于所有的数，返回r(arr.length).
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (target.compareTo(arr[mid]) > 0)
                l = mid + 1;
            else r = mid; // arr[mid] >= target
        }
        return l;
    }

    // 二分查找法, 实现upper_bound
    // 即在一个有序数组arr中, 寻找  大于  target的元素的第一个索引
    // 如果存在, 则返回相应的索引index
    // 否则, 返回arr的元素个数 n
    public static int upper_bound(Comparable[] arr, Comparable target) {
        if (arr == null)
            throw new IllegalArgumentException("arr can not be null.");
        int l = 0, r = arr.length;
        while (l != r) {
            int mid = l + (r - l) / 2;
            if (target.compareTo(arr[mid]) >= 0)
                l = mid + 1;
            else r = mid; // arr[mid] > target
        }
        return l;
    }

    // 非递归的二分查找算法
    // 如果找到target,返回相应的索引index
    // 如果没有找到target,返回-index-1(index为第一个大于target的元素位置）
    private static int binarySearch(Comparable[] arr, int l, int r, Comparable target) {
        assert l <= r;
        while (l <= r) {
            //int mid = l + (r - l) / 2;
            int mid = (l + r) >>> 1;
            if (target.compareTo(arr[mid]) == 0)
                return mid;
            else if (target.compareTo(arr[mid]) < 0)
                r = mid - 1;
            else l = mid + 1;
        }
        return -l - 1;
    }

    // 递归的二分查找算法
    // 如果找到target,返回相应的索引index
    // 如果没有找到target,返回-index-1(index为第一个大于target的元素位置）
    private static int binarySearchR(Comparable[] arr, int l, int r, Comparable target) {
        if (l > r) {
            return -l - 1;
        }
        int mid = l + (r - l) / 2;
        if (target.compareTo(arr[mid]) == 0)
            return mid;
        else if (target.compareTo(arr[mid]) < 0)
            return binarySearch(arr, l, mid - 1, target);
        else return binarySearch(arr, mid + 1, r, target);
    }

    public static void main(String[] args) {
        //Integer[] arr = SortHelper.generateOrderedArray(10);
        //System.out.println(binarySearch(arr, -2));
        Integer arr[] = new Integer[]{1, 1, 1, 2, 2, 2, 2, 2, 4, 4, 5, 5, 5, 6, 6, 6};
        for (int i = 0; i <= 8; i++) {

            int floorIndex = floor(arr, i);
            System.out.println("the floor index of " + i + " is " + floorIndex + ".");
            if (floorIndex >= 0 && floorIndex < arr.length)
                System.out.println("The value is " + arr[floorIndex] + ".");
            System.out.println();

            int ceilIndex = ceil(arr, i);
            System.out.println("the ceil index of " + i + " is " + ceilIndex + ".");
            if (ceilIndex >= 0 && ceilIndex < arr.length)
                System.out.println("The value is " + arr[ceilIndex] + ".");
            System.out.println();

            System.out.println();
        }
    }
}
