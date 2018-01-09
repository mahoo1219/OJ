package LeetCode;

/**
 * 206. Reverse Linked List
 */
public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if (head == null)
            return head;
        ListNode pre = null, cur = head, next = head.next;
        while (cur != null) {
            cur.next = pre;
            pre = cur;
            cur = next;
            if (next != null)
                next = next.next;
        }
        head = pre;
        return head;
    }

    // 更好
    public ListNode reverseList2(ListNode head) {
        ListNode cur = head;
        ListNode prev = null;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    // Recursive
    public static ListNode reverseList3(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseList3(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = null;
        reverseList3(a);

        int[] nums = {1, 2, 3, 4, 5};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode head2 = reverseList3(head);
        System.out.println(head2);
    }
}