package LeetCode;

/**
 * 11. Container With Most Water
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 */
public class ContainWater {
    // O(n2)
    public int maxArea(int[] height) {
        int n = height.length;
        int volume = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 1; j < n; j++) {
                volume = Math.max(volume, (j - i) * Math.min(height[j], height[i]));
            }
        }
        return volume;
    }

    /**
     * We could see this problem in a DP view and with some tricks we could do it in a greedy way which is O(n) time complexity.
     * Let's define S(i...j) to be the max volume from index i to j and v(i, j) be the volume of the container with boundary i and j . So we just need to figure out S(0...n) (0 and n are the leftmost and rightmost index of the array).
     * It is easy to derive that S(0..n) = max{v(0, n), S(1...n), S(0...n-1)}, and the time complexity is O(n^2). But we can optimize it. WLOG(without loss of generality), we assume height(0) > height(n), then we can simplify S(0..n) as S(0...n) = max{v(0, n), S(0...n-1)} and the complexity is O(n). Below is the proof :
     * 1, if v(0, n) is the largest volume, sicne we have figured out the result, the simplifier is all right.
     * 2, if not, we could find out that n can't be the boundry of the container. If n is the right boundry, becaues of height(0) > height(n) , the max volumn must be height(n) * (n - 0) = v(0, n), which arrives at a contradiction. So S(1...n) can be simplified as S(1...n-1) which is included in S(0...n-1).
     * s(i,j) = max{v(i,j), s(i,j-1)}(height[i] >= height[j]) /  max{v(i,j), s(i+1,j)}(height[i] < height[j])
     */
    public int maxArea2(int[] height) {
        int l = 0, r = height.length - 1;
        int volume = 0;
        while (l < r) {
            volume = Math.max(volume, (r - l) * Math.min(height[l], height[r]));
            if (height[l] > height[r]) r--;
            else l++;

        }
        return volume;
    }
    //https://discuss.leetcode.com/topic/3462/yet-another-way-to-see-what-happens-in-the-o-n-algorithm
    // 对于v(1,6), 如果height[l] < height[r], 那么v(1,5)..v(1,2)一定小于v(1,6)
    // 对于v(2,6), 如果height[l] > height[r], 那么v(2,6)..v(5,6)一定小于v(5,6)
}
