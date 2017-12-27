package Algo.Graph;

import java.util.Arrays;

public class IndexMinHeap<Item extends Comparable> {
    private Item[] items; // 最小索引堆中的数据
    private int[] indexes; // 最小索引堆中的索引, indexes[x] = i 表示索引i在x的位置
    private int[] reverses; // 最小索引堆中的反向索引, reverse[i] = x 表示索引i在x的位置
    private int count;
    private int capacity;

    // 构造函数, 构造一个空堆, 可容纳capacity个元素
    public IndexMinHeap(int n) {
        this.capacity = n;
        this.count = 0;
        items = (Item[]) new Comparable[capacity];
        indexes = new int[capacity];
        reverses = new int[capacity];
        Arrays.fill(reverses, -1);
    }

    // 向最小索引堆中插入一个新的元素, 新元素的索引为i, 元素为item
    // 传入的i对用户而言,是从0索引的
    public void insert(int i, Item e) {
        assert count < capacity;
        assert i >= 0 && i < capacity;
        // 再插入一个新元素前,还需要保证索引i所在的位置是没有元素的。
        assert !contain(i);

        indexes[count++] = i;
        reverses[i] = count;
        items[i] = e;
        shiftUp(count - 1);
    }

    public Item extract() {
        Item e = items[0];
        swapIndex(0, --count);
        reverses[indexes[count]] = -1;
        shiftDown(0);
        return e;
    }

    // 从最小索引堆中取出堆顶元素的索引
    public int extractMinIndex() {
        assert count > 0;
        int ret = indexes[0];
        swapIndex(0, count - 1);
        reverses[indexes[count]] = -1;
        count--;
        shiftDown(0);

        return ret;
    }

    // 获取最小索引堆中的堆顶元素
    public Item getMin() {
        assert count > 0;
        return items[indexes[0]];
    }

    // 获取最小索引堆中的堆顶元素的索引
    public int getMinIndex() {
        assert count > 0;
        return indexes[0];
    }

    // 获取最小索引堆中索引为i的元素
    public Item getItem(int i) {
        assert contain(i);
        return items[i];
    }

    // 将最小索引堆中索引为i的元素修改为newItem
    public void change(int i, Item newItem) {
        assert contain(i);
        items[i] = newItem;

        // 有了 reverse 之后,
        // 我们可以非常简单的通过reverse直接定位索引i在indexes中的位置
        shiftUp(reverses[i]);
        shiftDown(reverses[i]);
    }

    // i 表示在indexs[] 索引数组中的位置。
    private void shiftUp(int i) {
        while (i > 0 && items[indexes[i]].compareTo(items[indexes[(i - 1) / 2]]) < 0) {
            swapIndex(i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    private void shiftDown(int i) {
        while (2 * i + 1 < count) {
            int j = 2 * i + 1;
            if (j + 1 < count && items[indexes[j + 1]].compareTo(items[indexes[j]]) < 0)
                j = j + 1;
            if (items[indexes[i]].compareTo(items[indexes[j]]) >= 0)
                break;
            swapIndex(i, j);
            i = j;
        }
    }

    // 交换索引堆中的索引i和j
    // 由于有了反向索引reverse数组，
    // indexes数组发生改变以后， 相应的就需要维护reverse数组
    private void swapIndex(int i, int j) {
        int temp = indexes[i];
        indexes[i] = indexes[j];
        indexes[j] = temp;

        reverses[indexes[i]] = i;
        reverses[indexes[j]] = j;
    }

    // 看索引i所在的位置是否存在元素
    private boolean contain(int i) {
        return reverses[i] != -1;
    }

    // 返回索引堆中的元素个数
    public int size() {
        return count;
    }

    // 返回一个布尔值, 表示索引堆中是否为空
    public boolean isEmpty() {
        return count == 0;
    }

    // 测试 IndexMinHeap
    public static void main(String[] args) {

        int N = 100;
        IndexMinHeap<Integer> indexMinHeap = new IndexMinHeap<Integer>(N);
        for (int i = 0; i < N; i++)
            indexMinHeap.insert(i, (int) (Math.random() * N));

        Integer[] arr = new Integer[N];
        arr[0] = indexMinHeap.extract();
        System.out.print(arr[0] + " ");
        for (int i = 1; i < N; i++) {
            arr[i] = indexMinHeap.extract();
            System.out.print(arr[i] + " ");
            if(arr[i-1] > arr[i])
                System.out.println("Error");
        }
    }
}
