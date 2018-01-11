package LeetCode;

/**
 * 25. Reverse Nodes in k-Group
 */
public class ReveseKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1)
            return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        int i = 1;
        while (head != null) {
            if (i % k == 0) {
                prev = reverseK(prev, head.next);
                head = prev.next;
            } else {
                head = head.next;
            }
            i++;
        }
        return dummy.next;
    }

    public ListNode reverseK(ListNode prev, ListNode tail) {
        ListNode last = prev.next; //保持 last 的不变性
        ListNode cur = last.next;
        while (cur != tail) {
            last.next = cur.next;
            cur.next = prev.next;
            prev.next = cur;
            cur = last.next;
        }
        return last;
    }
}
