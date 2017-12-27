package LeetCode;

/**
 * 26.Given a sorted array, remove the duplicates in-place such that each element appear only once and return the new length.
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 */
public class RemoveDup {

    public int removeDuplicates2(int[] nums) {
        int i = 0;
        for (int n : nums)
            if (i < 1 || n > nums[i - 1])
                nums[i++] = n;
        return i;
    }

    public int removeDuplicates(int[] nums) {
        if (nums == null)
            return 0;
        int n = nums.length;
        if (n < 2) return n;
        int j = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[++j] = nums[i];
            }
        }
        return ++j;
    }
}
