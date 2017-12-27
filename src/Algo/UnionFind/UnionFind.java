package Algo.UnionFind;

// 使用 rank 进行优化
public class UnionFind {
    private int[] parent;// parent[i]表示第一个元素所指向的父节点
    // 基于 size 的优化
    //private int[] sz; // size[i]表示以i为根的集合中元素个数

    // 基于 rank 的优化
    private int[] rank;
    private int count;// 数据个数

    public UnionFind(int count) {
        this.count = count;
        parent = new int[count];
        rank = new int[count];
        for (int i = 0; i < count; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    // 查找过程, 查找元素p所对应的集合编号
    // O(h)复杂度, h为树的高度
    private int find(int p) {
        assert p >= 0 && p < count;
        //while (p != parent[p]) {
        //    parent[p] = parent[parent[p]];
        //    p = parent[p];
        //}
        //return p;

        // path compression 2, 递归算法
        if (p != parent[p]) {
            parent[p] = find(parent[p]);
        }
        return parent[p];
    }

    // 查看元素p和元素q是否所属一个集合
    // O(h)复杂度, h为树的高度
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // 合并元素p和元素q所属的集合
    // O(h)复杂度, h为树的高度
    public void unionElements(int p, int q) {

        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot)
            return;
        // 根据两个元素所在树的元素个数不同判断合并方向
        // 将元素个数少的集合合并到元素个数多的集合上
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[qRoot] < rank[pRoot]) {
            parent[qRoot] = pRoot;
        } else { // rank[pRoot] == rank[qRoot]
            parent[qRoot] = pRoot;
            rank[pRoot]++; // 此时, 维护rank的值
        }
    }

    public static void testUF(int n) {

        UnionFind uf = new UnionFind(n);

        long startTime = System.currentTimeMillis();

        // 进行n次操作, 每次随机选择两个元素进行合并操作
        for (int i = 0; i < n; i++) {
            int a = (int) (Math.random() * n);
            int b = (int) (Math.random() * n);
            uf.unionElements(a, b);
        }
        // 再进行n次操作, 每次随机选择两个元素, 查询他们是否同属一个集合
        for (int i = 0; i < n; i++) {
            int a = (int) (Math.random() * n);
            int b = (int) (Math.random() * n);
            uf.isConnected(a, b);
        }
        long endTime = System.currentTimeMillis();

        // 打印输出对这2n个操作的耗时
        System.out.println("UF3, " + 2 * n + " ops, " + (endTime - startTime) + "ms");
    }

    public static void main(String[] args) {
        testUF(1000000);
    }
}
