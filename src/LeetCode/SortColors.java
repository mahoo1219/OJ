package LeetCode;

/**
 * 75.Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent,
 * with the colors in the order red, white and blue. use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 */
public class SortColors {
    public void sortColors(int[] nums) {
        int num0 = 0, num1 = 0, num2 = 0;

        for (int n : nums) {
            if (n == 0) num0++;
            else if (n == 1) num1++;
            else num2++;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i < num0)
                nums[i] = 0;
            else if (i < num0 + num1)
                nums[i] = 1;
            else nums[i] = 2;
        }
    }

    public void sortColors2(int[] nums) {
        // nums[0,l]=0, nums[l+1,i)=1, nums[i]待定, nums[r, n-1]=2; 三路快速排序
        int l = -1, r = nums.length;
        for (int i = 0; i < r; ) {
            if (nums[i] == 0) {
                swapInt(nums, ++l, i++);
            } else if (nums[i] == 2)
                swapInt(nums, --r, i);
            else
                i++;
        }
    }

    public void swapInt(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {1,2, 0};
        SortColors s = new SortColors();
        s.sortColors2(arr);
        for (int a : arr) {
            System.out.print(a + " ");
        }
    }
}
