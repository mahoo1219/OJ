package Algo.Graph;

import java.util.Arrays;

public class MinHeap<Item extends Comparable> {
    private Item[] items; // 从 0 开始存储, (i-1)/2, i, 2*i +1, 2* i +2;
    private int count; // 只有count>1才能输出。
    private int capacity;

    // 构造函数, 构造一个空堆, 可容纳capacity个元素
    public MinHeap(int capacity) {
        this.capacity = capacity;
        items = (Item[]) new Comparable[capacity];
        count = 0;
    }

    // 构造函数, 通过一个给定数组创建一个最小堆
    // 该构造堆的过程, 时间复杂度为O(n)
    public MinHeap(Item[] arr) {
        this.count = this.capacity = arr.length;
        items = Arrays.copyOf(arr, arr.length);

        for (int i = 0; i < (count - 2) / 2; i++) {
            shiftDown(i);
        }
    }

    // 返回堆中的元素个数
    public int size() {
        return count;
    }

    // 返回一个布尔值, 表示堆中是否为空
    public boolean isEmpty() {
        return count == 0;
    }

    // 向最小堆中插入一个新的元素 item
    public void insert(Item e) {
        assert count < capacity;
        items[count++] = e;
        shiftUp(count - 1);
    }

    // 从最小堆中取出堆顶元素, 即堆中所存储的最小数据
    public Item extract() {
        assert count > 0;
        Item ret = items[0];
        items[0] = items[--count];
        shiftDown(0);
        return ret;
    }

    // 获取最小堆中的堆顶元素
    public Item getMin() {
        assert count > 0;
        return items[0];
    }

    //********************
    //* 最小堆核心辅助函数
    //********************
    private void shiftUp(int i) {
        Item e = items[i];
        int j = (i - 1) / 2;
        while (i > 0 && e.compareTo(items[j]) < 0) {
            items[i] = items[j];
            i = j;
            j = (j - 1) / 2;
        }
        items[i] = e;
    }

    private void shiftDown(int index) {
        int j = 2 * index + 1;
        Item e = items[index];
        while (j < count) {
            if (j + 1 < count && items[j + 1].compareTo(items[j]) < 0)
                j = j + 1;
            if (items[j].compareTo(e) >= 0) break;
            items[index] = items[j];
            index = j;
            j = 2 * index + 1;
        }
        items[index] = e;
    }

    public static void main(String[] args) {
        MinHeap<Integer> minHeap = new MinHeap<Integer>(1000);
        int N = 1000;
        for (int i = 0; i < N; i++) {
            minHeap.insert(new Integer((int) (Math.random() * N)));
        }

        Integer[] arr = new Integer[N];
        for (int i = 0; i < N; i++) {
            arr[i] = minHeap.extract();
            System.out.print(arr[i] + " ");
        }
    }
}
