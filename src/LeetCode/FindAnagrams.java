package LeetCode;
/**
 * https://discuss.leetcode.com/topic/30941/here-is-a-10-line-template-that-can-solve-most-substring-problems
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/
 */

import java.util.*;

/**
 * 438. Find All Anagrams in a String
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
 * The order of output does not matter.
 */
public class FindAnagrams {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0)
            return res;

        int[] freq = new int[27]; // 26个小写英文字母freq[1,26]
        for (char c : p.toCharArray())// 这里涉及到一个问题，s="a", p="aa", 是否包含
            freq[transfer(c)]++;
        int count = p.length();
        for (int l = 0, r = 0; r < s.length(); r++) {
            //move right everytime, if the character exists in p's hash, decrease the count
            //current hash value >= 1 means the character is existing in p
            if (--freq[transfer(s.charAt(r))] >= 0) {
                count--;
            }
            //when the count is down to 0, means we found the right anagram
            //then add window's left to result list
            if (count == 0) {
                res.add(l);
            }
            //if we find the window's size equals to p, then we have to move left (narrow the window) to find the new match window
            //++ to reset the hash because we kicked out the left
            //only increase the count if the character is in p
            //the count >= 0 indicate it was original in the hash, cuz it won't go below 0
            if (r - l + 1 == p.length() && ++freq[transfer(s.charAt(l++))] > 0)
                count++;
            //if (right - left == p.length()) {
            //
            //    if (hash[s.charAt(left)] >= 0) {
            //        count++;
            //    }
            //    hash[s.charAt(left)]++;
            //    left++;
            //}
        }
        return res;
    }

    private int transfer(int c) {
        return c - 'a' + 1;
    }

    // TODO unfinished 滑动窗口模板方法
    public List<Integer> slidingWindowTemplate(String s, String t) {
        //init a collection or int value to save the result according the question.
        List<Integer> result = new LinkedList<>();
        if (t.length() > s.length()) return result;

        //create a hashmap to save the Characters of the target substring.
        //(K, V) = (Character, Frequence of the Characters)
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        //maintain a counter to check whether match the target string.
        int counter = map.size();//must be the map size, NOT the string size because the char may be duplicate.

        //Two Pointers: begin - left pointer of the window; end - right pointer of the window
        int begin = 0, end = 0;

        //the length of the substring which match the target string.
        int len = Integer.MAX_VALUE;

        //loop at the begining of the source string
        while (end < s.length()) {

            char c = s.charAt(end);//get a character

            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);// plus or minus one
                if (map.get(c) == 0) counter--;//modify the counter according the requirement(different condition).
            }
            end++;

            //increase begin pointer to make it invalid/valid again
            while (counter == 0 /* counter condition. different question may have different condition */) {

                char tempc = s.charAt(begin);//***be careful here: choose the char at begin pointer, NOT the end pointer
                if (map.containsKey(tempc)) {
                    map.put(tempc, map.get(tempc) + 1);//plus or minus one
                    if (map.get(tempc) > 0) counter++;//modify the counter according the requirement(different condition).
                }

                /* save / update(min/max) the result if find a target*/
                // result collections or result int value

                begin++;
            }
        }
        return result;
    }
}
