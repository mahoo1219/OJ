package mathmatic;

//概率法计算圆周率PI 的值。
public class PI {
    public static void main(String[] args) {
        int count = 0, n = 1000000;
        double x = 0, y = 0;
        for (int i = 0; i < n; i++) {
            x = Math.random();
            y = Math.random();
            if ((x * x + y * y) <= 1) {
                count++;
            }
        }
        System.out.println(4.0 * count / n);
    }
}
