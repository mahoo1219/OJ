package LeetCode;

/**
 * 27.Given an array and a value, remove all instances of that value in-place and return the new length.
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 */
public class Remove_Element {
    public static int removeElement(int[] nums, int val) {
        int l = 0, r = nums.length - 1; // nums[0,l) 元素即为不相等元素
        while (l <= r) {
            while (l <= r && nums[l] != val) l++;
            while (r >= l && nums[r] == val) r--;
            if (l <= r)
                nums[l++] = nums[r--];
        }
        return l;
    }

    public static int removeElement2(int[] nums, int val) {
        int end = -1;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] != val)
                nums[++end] = nums[i];
        }
        return ++end;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 2, 3};
        System.out.println(removeElement(nums, 3));
    }
}
