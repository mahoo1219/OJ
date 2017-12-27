package DynamicProgramming;

/**
 * ZigZag – 2003 TCCC Semifinals 3
 */
public class ZigZag {
    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 5,8,9,10};
        deal(array);
    }

    public static void deal(int[] array) {
        int n = array.length;
        int[] f0 = new int[n]; // 与前一个数的差值为正数。
        int[] f1 = new int[n]; // 与前一个数的差值为负数。

        f0[0] = f1[0] = 1;

        // 0 <= j < i
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (array[i] > array[j]) {
                    f0[i] = Math.max(f0[j], f1[j] + 1);
                } else if (array[i] < array[j]) {
                    f1[i] = Math.max(f1[j], f0[j] + 1);
                } else continue;
            }
        }
        System.out.println(Math.max(f0[n - 1], f1[n - 1]));
    }

}
