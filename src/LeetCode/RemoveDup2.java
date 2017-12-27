package LeetCode;

/**
 * 80.Follow up for "Remove Duplicates":
 * What if duplicates are allowed at most twice?
 */
public class RemoveDup2 {
    public int removeDuplicates(int[] nums) {
        if (nums == null)
            return 0;
        int n = nums.length;
        if (n <= 2) return n;
        int end = 0, count = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[i - 1]) {
                count = 1;
                nums[++end] = nums[i];
            } else {
                if (count < 2) {
                    nums[++end] = nums[i];
                    count++;
                }
            }
        }
        return ++end;
    }

    public int removeDuplicates2(int[] nums) {
        int i = 0;
        for (int n : nums)
            if (i < 2 || n > nums[i - 2])
                nums[i++] = n;
        return i;
    }


    //直接与第前 k 个进行比较
    public int removeDuplicates3(int[] nums) {
        if (nums == null || nums.length <= 2) {
            return nums.length;
        }

        int slow = 2;
        for (int fast = 2; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow - 2]) {
                nums[slow++] = nums[fast];
            }
        }
        return slow;
    }

    // n:A.length
    // k: 最多允许重复多少次。
    int removeDuplicates(int A[], int n, int k) {

        if (n <= k) return n;

        int i = 1, j = 1;
        int cnt = 1;
        while (j < n) {
            if (A[j] != A[j - 1]) {
                cnt = 1;
                A[i++] = A[j];
            } else {
                if (cnt < k) {
                    A[i++] = A[j];
                    cnt++;
                }
            }
            ++j;
        }
        return i;
    }
}