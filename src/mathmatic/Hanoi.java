package mathmatic;

public class Hanoi {
    private static int count = 0; // 2^n - 1;

    public static void main(String[] args) {
        int number = 3;
        hanoi(number, 'A', 'B', 'C'); // Move form A to B, the C is the temporary.
    }

    public static void hanoi(int num, char from, char target, char temp) {
        if (num < 1) {
            System.out.println("Error");
            throw new RuntimeException();
        }
        if (num == 1) {
            System.out.println("No." + ++count + " from " + from + " to " + target);
        } else {
            hanoi(num - 1, from, temp, target);  //将 num - 1 个圆盘移动到临时柱 temp 上。
            System.out.println("No." + ++count + " from " + from + " to " + target); // 将最后一个圆盘移到目标柱 target 上。
            hanoi(num - 1, temp, target, from);  //将 num - 1 个圆盘从临时柱temp 移动到目标柱 target 上， from 作为临时柱。
        }
    }
}
