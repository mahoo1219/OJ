package Algo.Sort;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SortHelper {
    private static int N = 100000000;

    private SortHelper() {
    }

    //生成有n 个元素的随机数组，每个元素的范围在[0, N]
    public static Integer[] generateRandomArray(int n) {
        return generateRandomArray(n, 0, N);
    }

    //生成有n 个元素的随机数组，每个元素的范围在[min, max]
    public static Integer[] generateRandomArray(int n, int min, int max) {
        assert min <= max;
        Integer[] array = new Integer[n];
        for (int i = 0; i < n; i++) {
            array[i] = (int) (Math.random() * (max - min + 1) + min);
        }
        return array;
    }

    //生成一个近乎有序的数组
    //首先生成一个含有[0...n-1]的完全有序数组，之后随机交换swapTimes对数据
    // swapTimes 定义了数组的无序程度
    // swapTimes == 0 ，数组完全有序
    // swapTimes 越大，数组越趋于无序
    public static Integer[] generateNearlyOrderedArray(int n, int swapTimes) {
        Integer[] array = new Integer[n];
        for (int i = 0; i < n; i++) {
            array[i] = i;
        }

        for (int i = 0; i < swapTimes; i++) {
            int a = (int) (Math.random() * n);
            int b = (int) (Math.random() * n);
            int t = array[a];
            array[a] = array[b];
            array[b] = t;
        }
        return array;
    }

    // 生成一个完全有序的数组
    public static Integer[] generateOrderedArray(int n) {

        return generateNearlyOrderedArray(n, 0);
    }

    // 生成一个完全逆序的数组
    public static Integer[] generateInversedArray(int n) {

        Integer[] arr = generateOrderedArray(n);
        for (int i = n / 2 - 1; i >= 0; i--) {
            Integer t = arr[i];
            arr[i] = arr[n - i - 1];
            arr[n - i - 1] = t;
        }
        return arr;
    }

    // 将数组arr随机化
    public static void shuffleArray(Object[] arr) {

        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int j = (int) (Math.random() * (n - i)) + i;

            Object t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
        }
    }

    // 打印array 数组的所有内容
    public static void printArray(Object[] arr) {
        printArray(arr, 10);
    }

    // 打印array 数组的所有内容，每次打印n 个就换一行
    public static void printArray(Object[] array, int n) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
            if ((i + 1) % n == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }

    // 判断arr 数组是否有序，默认是升序
    public static boolean isSorted(Comparable[] arr) {
        return isSorted(arr, true);
    }

    // 判断arr 数组是否有序
    // up == true, 升序
    // up == false, 降序
    public static boolean isSorted(Comparable[] arr, boolean up) {
        if (up) {
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i].compareTo(arr[i + 1]) > 0)
                    return false;
            }
            return true;
        } else {
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i].compareTo(arr[i + 1]) < 0)
                    return false;
            }
            return true;
        }
    }

    // 测试sortClassName所对应的排序算法排序arr数组所得到结果的正确性和算法运行时间
    public static void testSort(String sortClassName, String methodName, Comparable[] arr) {
        // 通过Java的反射机制，通过排序的类名，运行排序函数
        try {
            // 通过sortClassName获得排序函数的Class对象
            Class cls = Class.forName(sortClassName);
            // 通过排序函数的Class对象获得排序方法
            Method method = cls.getDeclaredMethod(methodName, new Class[]{Comparable[].class});
            // 排序参数只有一个，是可比较数组arr
            Object[] params = new Object[]{arr};

            long startTime = System.currentTimeMillis();
            // 调用排序函数
            method.invoke(null, params);
            long endTime = System.currentTimeMillis();

            assert isSorted(arr);

            System.out.println(cls.getSimpleName() + " : " + method.getName() + " : " + (endTime - startTime) + "ms");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
