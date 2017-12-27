package LeetCode;

import java.util.HashSet;

/**
 * 202. Happy Number
 * A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
 */
public class HappyNumber {
    public static boolean isHappy(int n) {
        int sum;
        HashSet<Integer> res = new HashSet<>();
        do {
            sum = 0;
            while (n != 0) {
                sum += (n % 10) * (n % 10);
                n /= 10;
            }
            if (!res.add(sum))
                return false;
            n = sum;
        } while (sum != 1);
        return true;
    }


    /**
     * Unhappy numbers follow cycle ->4,16,37,58,89,145,42,20,4...
     */
    public boolean isHappy2(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n = n / 10;
        }

        if (sum == 1) {
            return true;
        }
        if (sum == 4) {
            return false;
        }
        return isHappy2(sum);
    }

    //Floyd Cycle detection algorithm
    boolean isHappy3(int n) {
        int slow, fast;
        slow = fast = n;
        do {
            slow = digitSquareSum(slow);
            fast = digitSquareSum(fast);
            if (fast == 1) break;
            fast = digitSquareSum(fast);
        } while (slow != fast);
        if (fast == 1 || slow == 1) return true;
        else return false;
    }

    int digitSquareSum(int n) {
        int sum = 0, tmp;
        while (n != 0) {
            tmp = n % 10;
            sum += tmp * tmp;
            n /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        int n = 7;
        System.out.println(isHappy(n));
    }
}
