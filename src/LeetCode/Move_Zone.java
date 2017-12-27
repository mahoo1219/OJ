package LeetCode;

/**
 * leetcode 283, move zone.
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
 */
public class Move_Zone {
    public void moveZeroes(int[] nums) {
        int n = nums.length;
        int j = 0; // 在 nums[0,j) 中所有元素均为非 0 元素
        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }
        for (; j < n; j++) {
            nums[j] = 0;
        }
    }

    public void moveZeroes2(int[] nums) {
        if (nums.length < 2)
            return;
        // 在 nums[0,j) 中所有元素均为非 0 元素
        for (int i = 0, j = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i != j) {
                    nums[j++] = nums[i];
                    nums[i] = 0;
                } else j++;
            }
        }
    }
}
