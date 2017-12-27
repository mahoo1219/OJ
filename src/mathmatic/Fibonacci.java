package mathmatic;

public class Fibonacci {
    public static void main(String[] args) {
        int number = 1;
        for (int i = 0; i < 10; i++) {
            System.out.println(fibo(i));
        }
    }

    public static int fibo(int n) {
        if (n < 0) {
            System.out.println("Error");
            throw new RuntimeException();
        }
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        return fibo(n - 1) + fibo(n - 2);
    }
}
