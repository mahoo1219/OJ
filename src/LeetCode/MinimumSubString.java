package LeetCode;

import java.util.HashMap;

/**
 * 76. Minimum Window Substring
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * If there is no such window in S that covers all characters in T, return the empty string "".
 */
public class MinimumSubString {
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int count = t.length(), len = s.length() + 1;
        int begin = 0, end = 0, head = 0;
        while (end < s.length()) {
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
                if (map.get(c) >= 0) count--;
            }
            end++;

            while (count == 0) {
                char temp = s.charAt(begin);
                if (map.containsKey(temp)) {
                    map.put(temp, map.get(temp) + 1);
                    if (map.get(temp) > 0)
                        count++;
                }
                if (end - begin < len) {
                    len = end - begin;
                    head = begin;
                }
                begin++;

            }
        }
        return (len == s.length() + 1) ? "" : s.substring(head, head + len);
    }

    public String minWindow2(String s, String t) {
        int[] hash = new int[256];
        char[] S = s.toCharArray();
        char[] T = t.toCharArray();
        for (int i = 0; i < T.length; i++) {
            hash[T[i]]++;
        }
        int count = T.length;
        int begin = 0;
        int end = 0;
        int head = 0;
        int min = Integer.MAX_VALUE;
        while (end < S.length) {
            if (hash[S[end++]]-- > 0) {
                count--;
            }
            while (count == 0) {
                if (min > end - begin) {
                    min = end - begin;
                    head = begin;
                }
                if (hash[S[begin++]]++ == 0) {
                    count++;
                }
            }
        }
        return min == Integer.MAX_VALUE ? "" : s.substring(head, head + min);
    }
}
