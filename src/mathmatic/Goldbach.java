package mathmatic;

import java.util.Arrays;

/**
 * 哥德巴赫猜想验证
 * 任一充分大的偶数都可以表示成为两个质数的和
 */
public class Goldbach {

    public static final int INT = 100;
    public static int[] prime = new int[INT + 1];

    public static void main(String[] args) {
        isPrime(INT);
        goldBach(INT);
        isSister(INT);
    }

    // 证明是否为素数
    // number 必须小于 INT
    public static void isPrime(int number) {

        Arrays.fill(prime, 1); // 初始化数组，标志为1 表示对应的数是质数
        prime[0] = prime[1] = 0;

        for (int i = 0; i * i <= number; i++) {
            if (prime[i] == 1) {
                for (int j = i * i; j <= number; j += i) { // 筛去合数，能整除，即是合数
                    if (j % i == 0) {
                        prime[j] = 0;
                    }
                }
            }
        }
    }

    //寻找不符合哥德巴赫猜想的偶数
    // number 必须小于 INT
    public static void goldBach(int number) {
        for (int i = 6; i <= number; i += 2) { // 从6 开始，循环验证各个偶数
            boolean flag = true;
            for (int j = 2; j <= number / 2; j++) { // 判断组成每个数的两个加数
                if (j % 2 == 0 || ((i - j) % 2 == 0)) { // 若一个加数是偶数，则不判断素数
                    continue;
                }
                if (prime[j] == 1 && prime[i - j] == 1) {
                    System.out.println(i + " = " + j + " + " + (i - j)); // 输出
                    flag = false;
                    break;
                }
            }
            if (flag) {
                System.out.println("找到一个不符合要求的偶数 ：" + i);
            }
        }
    }

    // 求姐妹素数（值相差为2 ）
    public static void isSister(int number) {
        int count = 0, twin = 2;
        for (int i = 2; i <= number; i++) {
            if (prime[i] == 1) {
                if (i - 2 == twin) { // 判断两个素数是否差 2
                    System.out.printf("(%-5d, %-5d)  ", i - 2, i);
                    count++;
                    if (count % 5 == 0) {
                        System.out.println();
                    }
                }
                twin = i;
            }
        }
    }

    // 梅森素数:Mp = (2^P - 1) 是素数（指数p 是素数）
    public static void isMeiSen(int number) {
        isPrime((int) Math.pow(2, number) - 1);
        // TODO has error
    }
}
