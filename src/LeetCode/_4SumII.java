package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 454. 4Sum II
 * Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.
 * To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500.
 * All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.
 */
public class _4SumII {
    // return the amount of tuples
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> sums = new HashMap<>();
        for (int i : A) {
            for (int j : B)
                sums.put(i + j, sums.getOrDefault(i + j, 0) + 1);
        }

        int res = 0;
        for (int i : C) {
            for (int j : D)
                res += sums.getOrDefault(-i - j, 0);
        }
        return res;
    }
}
