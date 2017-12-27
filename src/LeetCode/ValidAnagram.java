package LeetCode;

/**
 * 242. Valid Anagram
 * Given two strings s and t, write a function to determine if t is an anagram of s.
 */
public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] alphabet = new int[26];
        for (int i = 0; i < s.length(); i++) {
            alphabet[s.charAt(i) - 'a']++;
            alphabet[t.charAt(i) - 'a']--;
        }
        for (int e : alphabet)
            if (e != 0)
                return false;
        return true;
    }
}
