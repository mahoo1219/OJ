package LeetCode;

/**
 * 215.Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order,
 * not the kth distinct element.For example,Given [3,2,1,5,6,4] and k = 2, return 5.
 */
public class KthLE {
    public int findKthLargest(int[] nums, int k) {
        int l = 0, r = nums.length - 1;
        int ret;
        k = nums.length - k;
        while (true) {
            ret = partition(nums, l, r);
            if (ret == k)
                return nums[ret];
            else if (ret < k) {
                l = ret + 1;
            } else {
                r = ret - 1;
            }
        }
    }

    public int partition(int[] nums, int l, int r) {
        int temp = nums[l];
        int i = l + 1, j = r;  // nums[1,i) <= temp,   nums(j,nums.length-1] > temp
        while (i <= j) {
            while (i <= j && nums[i] <= temp)
                i++;
            while (i <= j && nums[j] >= temp)
                j--;
            if (i > j)
                break;
            swapInt(nums, i++, j--);
        }
        swapInt(nums, l, j); // j 即是目前找到的 temp 应该所处的位置
        return j;
    }

    public void swapInt(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        KthLE e = new KthLE();
        System.out.println(e.findKthLargest(nums, 2));
    }
}
