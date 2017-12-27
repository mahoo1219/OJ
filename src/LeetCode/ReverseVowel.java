package LeetCode;

import java.util.Collections;
import java.util.HashSet;

/**
 * 345.Reverse Vowels of a String
 * Write a function that takes a string as input and reverse only the vowels of a string.
 */
public class ReverseVowel {
    public static String reverseVowels(String s) {
        if (s == null || s.length() == 0)
            return s;

        HashSet<Character> vowels = new HashSet<>();
        Collections.addAll(vowels, 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
        //Set<Character> vowels2 = new HashSet<Character>(Arrays.asList(new Character[]{'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'}));

        char[] chars = s.toCharArray();
        int i = 0, j = s.length() - 1;
        while (i < j) {
            while (i < j && !vowels.contains(chars[i])) {
                i++;
            }
            while (i < j && !vowels.contains(chars[j])) {
                j--;
            }
            if (i < j) {
                char c = chars[i];
                chars[i] = chars[j];
                chars[j] = c;
                ++i;
                --j;
            }
        }
        return String.valueOf(chars);
    }

    public String reverseVowels2(String s) {
        char[] word = s.toCharArray();
        int l = 0;
        int r = s.length() - 1;
        String VOWEL = "aeiouAEIOU";
        while (l < r) {
            while (l < r && VOWEL.indexOf(word[l]) < 0) {
                l++;
            }
            while (l < r && VOWEL.indexOf(word[r]) < 0) {
                r--;
            }
            char tmp = word[l];
            word[l] = word[r];
            word[r] = tmp;
            l++;
            r--;
        }
        return new String(word);
    }

    public static void main(String[] args) {
        String s = "hello";
        System.out.println(reverseVowels(s));
    }
}
