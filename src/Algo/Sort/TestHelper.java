package Algo.Sort;

import java.lang.reflect.Method;

public class TestHelper {
    // 生成有n个元素的随机数组,每个元素的随机范围为[rangeL, rangeR]
    public static Integer[] generateRandomArray(int n, int rangeL, int rangeR) {
        assert (rangeL <= rangeR);
        Integer[] array = new Integer[n];
        for (int i = 0; i < n; i++) {
            array[i] = new Integer((int) (Math.random() * (rangeR - rangeL + 1) + rangeL));
        }
        return array;
    }

    // 生成一个近乎有序的数组
    // 首先生成一个含有[0...n-1]的完全有序数组, 之后随机交换swapTimes对数据
    // swapTimes定义了数组的无序程度:
    // swapTimes == 0 时, 数组完全有序
    // swapTimes 越大, 数组越趋向于无序
    public static Integer[] generateNearlyOrderedArray(int n, int swapTimes) {
        Integer[] array = new Integer[n];
        for (int i = 0; i < n; i++) {
            array[i] = new Integer(i);
        }

        for (int i = 0; i < swapTimes; i++) {
            int a = (int) Math.random() * n;
            int b = (int) Math.random() * n;
            swap(array, a, b);
        }
        return array;
    }

    public static void printArray(int[] array) {
        int count = 1;
        for (int i = 0; i < array.length; i++, count++) {
            System.out.print(array[i] + " ");
            if (count % 10 == 0)
                System.out.println();
        }
        System.out.println();
    }

    public static void printArray(Object[] array) {
        int count = 1;
        for (int i = 0; i < array.length; i++, count++) {
            System.out.print(array[i] + " ");
            if (count % 10 == 0)
                System.out.println();
        }
    }

    public static void printArray(Object[] array, int n) {
        int count = 1;
        for (int i = 0; i < array.length; i++, count++) {
            System.out.print(array[i] + " ");
            if (count % n == 0)
                System.out.println();
        }
    }

    public static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public static void testSort(String sortName, Comparable[] arr) {
        try {
            Class sortClass = Class.forName(sortName);
            Method sortMethod = sortClass.getMethod(sortClass.getSimpleName().substring(0, 1).toLowerCase() + sortClass.getSimpleName().substring(1), new Class[]{Comparable[].class});
            Object[] params = new Object[]{arr};

            long startTime = System.currentTimeMillis();
            sortMethod.invoke(null, params);
            long endTime = System.currentTimeMillis();

            assert isSorted(arr);

            System.out.println(sortClass.getSimpleName() + " : " + (endTime - startTime) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isSorted(Comparable[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i].compareTo(array[i + 1]) > 0)
                return false;
        }
        return true;
    }
}
