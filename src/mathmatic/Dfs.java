package mathmatic;

public class Dfs {
    private static int[][] a = new int[51][51];
    private static int[][] book = new int[51][51];
    static int n = 3;

    static int[][] next = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int tx, ty;
    static int p = 3, q = 2;
    static int min = Integer.MAX_VALUE;

    public static void dfs(int x, int y, int step) {
        // 判断是否到达目标位置
        if (x == p && y == q) {
            // 更新最小值
            if (step < min)
                min = step;
            return; // 是目标位置，返回。
        }

        // 枚举四种方法
        for (int i = 0; i < 4; i++) {
            // 计算下一个点的坐标
            tx = x + next[i][0];
            ty = y + next[i][1];
            // 判断是否越界
            if (tx < 1 || tx > n || ty < 1 || ty > n) {
                continue;
            }
            // 判断该店是否为障碍物或者已经在路径上
            if (a[tx][ty] == 0 && book[tx][ty] == 0) {
                book[tx][ty] = 1; // 标记这个点已经走过
                dfs(tx, ty, step + 1); // 开始尝试下一个点
                book[tx][ty] = 0; // 尝试结束，取消这个点的标记
                // 感觉这里不需要删除标记。 但因为是求最小路径，所以需要删除。
                //回溯后是否要将节点标记重置应该看路径来源是否会对节点能否到达出口产生影响，如果任意路径到达此节点都不会影响它能否到达终点，则不去除标记，因为即使下一次搜索到它，也知道它无法到达终点，从而跳过它。
            }
        }
        return;
    }

    public static void bfs(int startx, int starty, int step) {
        class node {
            int x;
            int y;
            int f; //father
            int s; //step
        }

        node[] que = new node[2501];
        int head = 1, tail = 1;
        que[tail].x = startx;
        que[tail].y = starty;
        que[tail].f = 0;
        que[tail].s = 0;
        tail++;
        book[startx][starty] = 1;
        int flag = 0; //是否到达
        while (head < tail) {
            for (int i = 0; i < 4; i++) {
                tx = que[head].x + next[i][0];
                ty = que[head].y + next[i][1];
                if (tx < 1 || tx > n || ty < 1 || ty > n) {
                    continue;
                }
                if (a[tx][ty] == 0 && book[tx][ty] == 0) {
                    book[tx][ty] = 1;
                    que[tail].x = tx;
                    que[tail].y = ty;
                    que[tail].f = head;
                    que[tail].s = que[head].s + 1;
                    tail++;
                }

                if (tx == p && ty == q) {
                    flag = 1; // 到达目标位置，跳出
                    break;
                }
            }
            if (flag == 1)
                break;
            head++; // 当一个点扩展结束，head++，遍历下一个结点
        }

    }

    public static void main(String[] args) {
        dfs(0, 0, 0);
    }
}
