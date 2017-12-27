package Algo.BSTree;

import Algo.Sort.SortHelper;

import java.util.LinkedList;

// 二分搜索树
// 由于Key需要能够进行比较，所以需要extends Comparable<Key>
public class BST<Key extends Comparable<Key>, Value> {

    private Node root; // 根节点
    private int count; // 树种的节点个数

    public BST() {
        root = null;
        count = 0;
    }

    // 返回二分搜索树的节点个数
    public int size() {
        return count;
    }

    // 返回二分搜索树是否为空
    public boolean isEmpty() {
        return count == 0;
    }

    // 向二分搜索树中插入一个新的(key, value)数据对
    public void insert(Key key, Value value) {
        root = insertR(root, key, value);
    }

    // 查看二分搜索树中是否存在键key
    public boolean contain(Key key) {
        return containR(root, key);
    }

    // 在二分搜索树中搜索键key所对应的值。如果这个值不存在, 则返回null
    public Value search(Key key) {
        return searchR(root, key);
    }

    // 二分搜索树的前序遍历
    public void preOrder() {
        preOrderR(root);
    }

    // 二分搜索树的中序遍历
    public void inOrder() {
        inOrderR(root);
    }

    // 二分搜索树的后序遍历
    public void postOrder() {
        postOrderR(root);
    }

    // 二分搜索树的层序遍历
    public void levelOrder() {
        LinkedList<Node> q = new LinkedList<>();
        if (root == null)
            return;
        q.add(root);
        while (!q.isEmpty()) {
            Node node = q.remove();
            System.out.print(node.key + ", ");
            if (node.left != null)
                q.add(node.left);
            if (node.right != null)
                q.add(node.right);
        }
        System.out.println();
    }

    // 寻找二分搜索树的最小的键值
    public Key minimum() {
        assert count != 0;
        Node minNode = minimum(root);
        return minNode.key;
    }

    // 寻找二分搜索树的最大的键值
    public Key maximum() {
        assert count != 0;
        Node maxNode = maximum(root);
        return maxNode.key;
    }

    // 从二分搜索树中删除最小值所在节点
    public void removeMin() {
        if (root != null)
            root = removeMin(root);
    }

    // 从二分搜索树中删除最大值所在节点
    public void removeMax() {
        if (root != null)
            root = removeMax(root);
    }

    // 从二分搜索树中删除键值为key的节点
    public void remove(Key key) {
        root = remove(root, key);
    }

    // 寻找key的floor值, 递归算法
    // 如果不存在key的floor值(key比BST中的最小值还小), 返回NULL
    public Key floor(Key key) {
        if (count == 0 || key.compareTo(minimum()) < 0)
            return null;
        Node floorNode = floor(root, key);
        return floorNode.key;
    }

    // 寻找key的ceil值, 递归算法
    // 如果不存在key的ceil值(key比BST中的最大值还大), 返回NULL
    public Key ceil(Key key) {

        if (count == 0 || key.compareTo(maximum()) > 0)
            return null;

        Node ceilNode = ceil(root, key);
        return ceilNode.key;
    }

    // 查找key的前驱
    // 如果不存在key的前驱(key不存在, 或者key是整棵二叉树中的最小值), 则返回NULL
    public Key predecessor(Key key) {
        Node node = search(root, key);
        // 如果key所在的节点不存在, 则key没有前驱, 返回NULL
        if (node == null)
            return null;

        // 如果key所在的节点左子树不为空,则其左子树的最大值为key的前驱
        if (node.left != null)
            return maximum(node.left).key;

        // 否则, key的前驱在从根节点到key的路径上, 在这个路径上寻找到比key小的最大值, 即为key的前驱
        Node preNode = predecessorFromAncestor(root, key);
        return preNode == null ? null : preNode.key;
    }

    // 查找key的后继, 递归算法
    // 如果不存在key的后继(key不存在, 或者key是整棵二叉树中的最大值), 则返回NULL
    public Key successor(Key key) {

        Node node = search(root, key);
        // 如果key所在的节点不存在, 则key没有前驱, 返回NULL
        if (node == null)
            return null;

        // 如果key所在的节点右子树不为空,则其右子树的最小值为key的后继
        if (node.right != null)
            return minimum(node.right).key;

        // 否则, key的后继在从根节点到key的路径上, 在这个路径上寻找到比key大的最小值, 即为key的后继
        Node sucNode = successorFromAncestor(root, key);
        return sucNode == null ? null : sucNode.key;
    }

    //********************
    //* 二分搜索树的辅助函数
    //********************

    // 向以node为根的二分搜索树中, 插入节点(key, value), 使用递归算法
    // 返回插入新节点后的二分搜索树的根
    private Node insertR(Node root, Key key, Value value) {
        if (root == null) {
            count++;
            return new Node(key, value);
        }
        if (key.compareTo(root.key) == 0)
            root.value = value;
        else if (key.compareTo(root.key) < 0)
            root.left = insertR(root.left, key, value);
        else root.right = insertR(root.right, key, value);
        return root;
    }

    // 非递归实现
    private void insert(Node root, Key key, Value value) {
        if (root == null) {
            count++;
            root = new Node(key, value);
            return;
        }
        Node currentNode = root;
        Node parentNode = root;
        boolean isLeftChild = true;
        while (currentNode != null) {
            parentNode = currentNode;
            if (key.compareTo(currentNode.key) == 0) {
                currentNode.value = value;
                return;
            } else if (key.compareTo(currentNode.key) < 0) {
                currentNode = currentNode.left;
                isLeftChild = true;
            } else {
                currentNode = currentNode.right;
                isLeftChild = false;
            }
        }
        Node aNode = new Node(key, value);
        if (isLeftChild) {
            parentNode.left = aNode;
        } else {
            parentNode.right = aNode;
        }
    }


    // 查看以node为根的二分搜索树中是否包含键值为key的节点, 使用递归算法
    private boolean containR(Node root, Key key) {
        if (root == null)
            return false;
        if (key.compareTo(root.key) == 0)
            return true;
        else if (key.compareTo(root.key) < 0)
            return containR(root.left, key);
        else return containR(root.right, key);
    }

    // 在以node为根的二分搜索树中查找key所对应的value, 递归算法
    // 若value不存在, 则返回NULL
    private Value searchR(Node root, Key key) {
        if (root == null)
            return null;
        if (key.compareTo(root.key) == 0)
            return root.value;
        else if (key.compareTo(root.key) < 0)
            return searchR(root.left, key);
        else return searchR(root.right, key);
    }

    private Node search(Node root, Key key) {
        Node currentNode = root;
        while (currentNode != null && key.compareTo(currentNode.key) != 0) {
            if (key.compareTo(currentNode.key) < 0)
                currentNode = currentNode.left;
            else currentNode = currentNode.right;
        }
        if (currentNode == null)
            return null;
        else return currentNode;
    }

    // 对以node为根的二叉搜索树进行前序遍历, 递归算法
    private void preOrderR(Node root) {
        if (root == null)
            return;
        System.out.println(root.key + " : " + root.value);
        preOrderR(root.left);
        preOrderR(root.right);
    }

    // 对以node为根的二叉搜索树进行中序遍历, 递归算法
    private void inOrderR(Node root) {
        if (root != null) {
            inOrderR(root.left);
            System.out.println(root.key + " : " + root.value);
            inOrderR(root.right);
        }
    }

    // 对以node为根的二叉搜索树进行后序遍历, 递归算法
    private void postOrderR(Node root) {
        if (root != null) {
            postOrderR(root.left);
            postOrderR(root.right);
            System.out.println(root.key + " : " + root.value);
        }
    }

    // 返回以node为根的二分搜索树的最小键值所在的节点
    private Node minimum(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    // 返回以node为根的二分搜索树的最大键值所在的节点
    private Node maximum(Node node) {
        while (node.right != null)
            node = node.right;
        return node;
    }

    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node root) {
        if (root.left == null) {
            Node rightNode = root.right;
            root.right = null;
            count--;
            return rightNode;
        }
        root.left = removeMin(root.left);
        return root;
    }

    // 删除掉以node为根的二分搜索树中的最大节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            count--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    // 删除掉以node为根的二分搜索树中键值为key的节点, 递归算法
    // 返回删除节点后新的二分搜索树的根
    private Node remove(Node node, Key key) {
        if (node == null)
            return null;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {// key == node->key
            if (node.left == null) { // 待删除节点左子树为空的情况
                Node rightChild = node.right;
                node.right = null;
                count--;
                return rightChild;
            }
            if (node.right == null) {  // 待删除节点右子树为空的情况
                Node leftChild = node.left;
                node.left = null;
                count--;
                return leftChild;
            }
            // 待删除节点左右子树均不为空的情况

            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor = new Node(minimum(node.right));
            count++;

            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            count--;
            return successor;
        }
    }


    private boolean delete(Node node, Key key) {
        Node currentNode = node;
        Node parentNode = node;
        boolean isLeftChild = true;
        while (currentNode != null && key.compareTo(currentNode.key) != 0) {
            parentNode = currentNode;
            if (key.compareTo(currentNode.key) < 0) {
                currentNode = currentNode.left;
                isLeftChild = true;
            } else {
                currentNode = currentNode.right;
                isLeftChild = false;
            }
        }
        if (currentNode == null)
            return false;
        if (currentNode.left == null && currentNode.right == null) {
            count--;
            if (currentNode == root) {
                root = null;
            } else if (isLeftChild) {
                parentNode.left = null;
            } else {
                parentNode.right = null;
            }
        } else if (currentNode.left == null) {//要删除的节点只有右孩子
            count--;
            if (currentNode == root) {
                root = currentNode.right;
            } else if (isLeftChild) {
                parentNode.left = currentNode.right;
            } else {
                parentNode.right = currentNode.right;
            }
        } else if (currentNode.right == null) { //要删除的节点只有左孩子
            count--;
            if (currentNode == root) {
                root = currentNode.left;
            } else if (isLeftChild) {
                parentNode.left = currentNode.left;
            } else {
                parentNode.right = currentNode.left;
            }
        } else { // 既有左孩子，也有右孩子
            Node predecessor = maximum(currentNode.left);
            delete(currentNode.left, predecessor.key);
            currentNode.key = predecessor.key;
            currentNode.value = predecessor.value;
        }
        return true;
    }

    // 在以node为根的二叉搜索树中, 寻找key的floor值所处的节点, 递归算法
    private Node floor(Node node, Key key) {
        if (node == null)
            return null;

        // 如果node的key值和要寻找的key值相等
        // 则node本身就是key的floor节点
        if (key.compareTo(node.key) == 0)
            return node;

        // 如果node的key值比要寻找的key值大
        // 则要寻找的key的floor节点一定在node的左子树中
        if (key.compareTo(node.key) < 0)
            return floor(node.left, key);

        // 如果node->key < key
        // 则node有可能是key的floor节点, 也有可能不是(存在比node->key大但是小于key的其余节点)
        // 需要尝试向node的右子树寻找一下
        Node tempNode = floor(node.right, key);
        if (tempNode != null)
            return tempNode;
        return node;
    }

    // 在以node为根的二叉搜索树中, 寻找key的ceil值所处的节点, 递归算法
    private Node ceil(Node node, Key key) {

        if (node == null)
            return null;

        // 如果node的key值和要寻找的key值相等
        // 则node本身就是key的ceil节点
        if (node.key.compareTo(key) == 0)
            return node;

        // 如果node的key值比要寻找的key值小
        // 则要寻找的key的ceil节点一定在node的右子树中
        if (node.key.compareTo(key) < 0)
            return ceil(node.right, key);

        // 如果node->key > key
        // 则node有可能是key的ceil节点, 也有可能不是(存在比node->key小但是大于key的其余节点)
        // 需要尝试向node的左子树寻找一下
        Node tempNode = ceil(node.left, key);
        if (tempNode != null)
            return tempNode;

        return node;
    }

    // 在以node为根的二叉搜索树中, 寻找key的祖先中,比key小的最大值所在节点, 递归算法
    // 算法调用前已保证key存在在以node为根的二叉树中
    private Node predecessorFromAncestor(Node node, Key key) {
        if (key.compareTo(node.key) == 0)
            return null;
        // 如果当前节点大于key, 则当前节点不可能是比key小的最大值
        // 向下搜索到的结果直接返回
        if (key.compareTo(node.key) < 0)
            return predecessorFromAncestor(node.left, key);
        else {
            // 如果当前节点小于key, 则当前节点有可能是比key小的最大值
            // 向右继续搜索, 将结果存储到tempNode中
            assert key.compareTo(node.key) > 0;
            Node tempNode = predecessorFromAncestor(node.right, key);
            if (tempNode != null)
                return tempNode;
                // 如果tempNode为空, 则当前节点即为结果
            else return node;
        }
    }

    // 在以node为根的二叉搜索树中, 寻找key的祖先中,比key大的最小值所在节点, 递归算法
    // 算法调用前已保证key存在在以node为根的二叉树中
    Node successorFromAncestor(Node node, Key key) {

        if (node.key.compareTo(key) == 0)
            return null;

        if (key.compareTo(node.key) > 0)
            // 如果当前节点小于key, 则当前节点不可能是比key大的最小值
            // 向下搜索到的结果直接返回
            return successorFromAncestor(node.right, key);
        else {
            assert (key.compareTo(node.key) < 0);
            // 如果当前节点大于key, 则当前节点有可能是比key大的最小值
            // 向左继续搜索, 将结果存储到tempNode中
            Node tempNode = predecessorFromAncestor(node.left, key);
            if (tempNode != null)
                return tempNode;
            else
                // 如果tempNode为空, 则当前节点即为结果
                return node;
        }
    }

    private class Node {
        Key key;
        Value value;
        Node left, right;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.left = this.right = null;
        }

        public Node(Node node) {
            this.key = node.key;
            this.value = node.value;
            this.left = node.left;
            this.right = node.right;
        }

    }

    // 测试二分搜索树
    public static void main(String[] args) {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();

        // 取n个取值范围在[0...n)的随机整数放进二分搜索树中
        int N = 1000;
        for (int i = 0; i < N; i++) {
            Integer key = new Integer((int) (Math.random() * N));
            // 为了后续测试方便,这里value值取和key值一样
            bst.insert(key, key);
        }
        //Integer[] arr = {11, 4, 19, 1, 10, 17, 0, 2, 8, 12, 18, 6, 14};
        //for (int i = 0; i <arr.length; i++) {
        //    //Integer key = new Integer((int) (Math.random() * N));
        //    // 为了后续测试方便,这里value值取和key值一样
        //    bst.insert(arr[i], arr[i]);
        //}
        System.out.println(bst.size());
        // 注意, 由于随机生成的数据有重复, 所以bst中的数据数量大概率是小于n的

        bst.levelOrder();

        // order数组中存放[0...n)的所有元素
        Integer order[] = new Integer[N];
        for (int i = 0; i < N; i++) {
            order[i] = new Integer(i);
        }

        // 打乱order数组的顺序
        SortHelper.shuffleArray(order);

        // 乱序删除[0...n)范围里的所有元素
        for (int i = 0; i < N; i++)
            if (bst.contain(order[i])) {
                //bst.remove(order[i]);
                bst.delete(bst.root, order[i]);
                //System.out.println("After remove " + order[i] + " size = " + bst.size());
            }

        bst.levelOrder();
        // 最终整个二分搜索树应该为空
        System.out.println(bst.size());
    }
}
