package mathmatic;

public class GCD {
    public static void main(String[] args) {
        System.out.println(gcd(153, 2));
    }

    public static int gcd(int m, int n) {
        if (m < n) {
            int temp = m;
            m = n;
            n = temp;
        }
        int r = m % n;
        if (r == 0) {
            return n;
        } else {
            return gcd(n, r);
        }
    }
}
