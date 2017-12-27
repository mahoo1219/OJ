package mathmatic;

public class Factorial_recursive {
    public static void main(String[] args) {
        System.out.println(factorial(4));
    }

    public static int factorial(int num) {
        if (num < 0) {
            System.out.println("Error");
            throw new RuntimeException();
        }
        if (num == 0) {
            return 1;
        }
        return num * factorial(num - 1);
    }
}
