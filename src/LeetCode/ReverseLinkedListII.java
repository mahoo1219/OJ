package LeetCode;

/**
 * 92. Reverse Linked List II
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.
 * Given m, n satisfy the following condition: 1 ≤ m ≤ n ≤ length of list.
 */
public class ReverseLinkedListII {
    // 这里分开考虑了m 是否为第一个节点，m == 1?
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null)
            return null;
        ListNode pre, front, cur;
        if (m == 1) {
            pre = null;
            front = head;
            cur = head;
        } else {
            pre = head;
            for (int i = 1; i < m - 1; i++) {
                pre = pre.next;
            }
            front = pre;
            cur = pre.next;
        }

        int count = 0;
        while (cur != null && count <= (n - m)) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
            count++;
        }
        if (m == 1) {
            front.next = cur;
            head = pre;
        } else {
            front.next.next = cur;
            front.next = pre;
        }
        return head;
    }

    // 这里添加了一个头结点，。
    public ListNode reverseBetween2(ListNode head, int m, int n) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0); // create a dummy node to mark the head of this list
        dummy.next = head;
        ListNode pre = dummy; // make a pointer pre as a marker for the node before reversing
        for (int i = 0; i < m - 1; i++) pre = pre.next;

        ListNode start = pre.next; // a pointer to the beginning of a sub-list that will be reversed
        ListNode then = start.next; // a pointer to a node that will be reversed

        // 1 - 2 -3 - 4 - 5 ; m=2; n =4 ---> pre = 1, start = 2, then = 3
        // dummy-> 1 -> 2 -> 3 -> 4 -> 5

        for (int i = 0; i < n - m; i++) {
            start.next = then.next;
            then.next = pre.next;
            pre.next = then;
            then = start.next;
        }

        // first reversing : dummy->1 - 3 - 2 - 4 - 5; pre = 1, start = 2, then = 4
        // second reversing: dummy->1 - 4 - 3 - 2 - 5; pre = 1, start = 2, then = 5 (finish)

        return dummy.next;
    }

    public ListNode reverseBetween3(ListNode head, int m, int n) {
        if (head == null)
            return null;

        ListNode first = new ListNode(0);// Head Node
        first.next = head;
        ListNode prev = first;
        for (int i = 1; i < m; i++) {
            prev = prev.next;
        }

        ListNode cur = prev.next, next = cur.next;
        for (int i = m; i < n; i++) {
            cur.next = next.next;  // 保持了cur 的不变性，每次都将剩余的一个元素插入到列表头（m 处）
            next.next = prev.next;
            prev.next = next;
            next = cur.next;
        }
        return first.next;
    }
}
