package Algo.Sort;

public class MaxHeap<Item extends Comparable> {
    protected Item[] data;
    protected int count;
    protected int capacity;

    public MaxHeap() {
        this(15);
    }

    public MaxHeap(int capacity) {
        data = (Item[]) new Comparable[capacity + 1];
        this.count = 0;
        this.capacity = capacity;
    }

    // 构造函数, 通过一个给定数组创建一个最大堆
    // 该构造堆的过程, 时间复杂度为O(n)
    public MaxHeap(Item[] arr) {
        this(arr.length);
        for (int i = 0; i < arr.length; i++) {
            data[i + 1] = arr[i];
        }
        count = arr.length;
        // heapify
        for (int i = count / 2; i >= 1; i--) {
            shiftDown(i);
        }
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void insert(Item item) {
        if (count + 1 >= capacity) {
            Item[] temp = (Item[]) new Comparable[2 * capacity + 1];
            capacity *= 2;
            System.arraycopy(data, 0, temp, 0, data.length - 1);
            data = temp;
        }
        data[++count] = item;
        shiftUp(count);
    }

    public Item extract() {
        assert count > 0;
        Item ret = data[1];

        swap(1, count--);
        shiftDown(1);
        return ret;
    }

    // 获取最大堆中的堆顶元素
    public Item getMax() {
        assert (count > 0);
        return data[1];
    }

    private void shiftUp(int k) {
        Item e = data[k];
        while (k > 1 && e.compareTo(data[k / 2]) > 0) {
            data[k] = data[k / 2];
            k /= 2;
        }
        data[k] = e;
    }

    private void shiftDown(int k) {
        Item e = data[k];
        int j;
        while (2 * k <= count) {
            j = 2 * k;
            // 在此轮循环中,data[k]和data[j]交换位置
            if (j + 1 <= count && data[j + 1].compareTo(data[j]) > 0) {
                j++; // data[j] 是 data[2*k]和data[2*k+1]中的最大值
            }
            if (e.compareTo(data[j]) >= 0) break;
            data[k] = data[j];
            k = j;
        }
        data[k] = e;
    }

    // 交换堆中索引为i和j的两个元素
    private void swap(int i, int j) {
        Item t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    // 测试 MaxHeap
    public static void main(String[] args) {
        Integer[] arr = SortHelper.generateRandomArray(10, 0, 20);
        PrintableMaxHeap maxHeap = new PrintableMaxHeap(arr);
        maxHeap.treePrint();

    }
}

