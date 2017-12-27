package LeetCode;

/**
 * 344.Write a function that takes a string as input and returns the string reversed.
 */
public class ReverseString {
    public String reverseString(String s) {
        return new StringBuilder(s).reverse().toString();
    }
}
