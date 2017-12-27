package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 3. Longest Substring Without Repeating Characters
 * https://leetcode.com/articles/longest-substring-without-repeating-characters/
 */
public class LongestSubString {
    public static int lengthOfLongestSubstring(String s) {
        int l = 0, r = 0;  //滑动窗口为s[l...r)
        int[] freq = new int[256];

        int res = 0;
        while (l < s.length()) {
            if (r < s.length() && freq[s.charAt(r)] == 0)
                freq[s.charAt(r++)] = 1; // r 指向了下一位
            else
                freq[s.charAt(l++)]--; // l 指向开始位
            res = Math.max(res, r - l);
        }
        return res;
    }

    public int lengthOfLongestSubstring2(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

    /**
     * int[26] for Letters 'a' - 'z' or 'A' - 'Z'
     * int[128] for ASCII
     * int[256] for Extended ASCII
     */
    public int lengthOfLongestSubstring3(String s) {
        int n = s.length(), res = 0;
        int[] freq = new int[256];

        for (int i = 0, j = 0; j < n; j++) {
            i = Math.max(freq[s.charAt(j)], i);
            res = Math.max(res, j - i + 1);
            freq[s.charAt(j)] = j + 1;
        }
        return res;
    }

    public static int lengthOfLongestSubstring4(String s) {
        int n = s.length(), res = 0;
        int[] freq = new int[256];
        int l = 0, r = 0; //[l,r]
        while (r < s.length()) {
            l = Math.max(freq[s.charAt(r)], l);
            freq[s.charAt(r)] = ++r;
            res = Math.max(res, r - l);
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "abcabcabc";
        System.out.println(lengthOfLongestSubstring4(s));
    }
}
