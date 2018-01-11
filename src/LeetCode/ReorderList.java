package LeetCode;

/**
 * 143. Reorder List
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * You must do this in-place without altering the nodes' values.
 */
public class ReorderList {
    public static void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null)
            return;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow, fast = slow = dummy;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        dummy.next = slow.next;
        slow.next = null;
        ListNode last = dummy.next, temp;
        while (last.next != null) {
            temp = last.next;
            last.next = temp.next;
            temp.next = dummy.next;
            dummy.next = temp;
        }

        System.out.println(head);
        System.out.println(dummy);
        slow = head.next;
        fast = dummy.next;
        last = head;
        while (slow != null && fast != null) {
            last.next = fast;
            fast = fast.next;
            last = last.next;
            last.next = slow;
            slow = slow.next;
            last = last.next;
        }
        if (fast != null)
            last.next = fast;
        System.out.println(head);
    }

    public static void reorderList2(ListNode head) {
        if (head == null || head.next == null)
            return;
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode index = slow.next;
        slow.next = null;
        ListNode laker = null;
        ListNode arsenal = index.next;
        while (index != null) {
            arsenal = index.next;
            index.next = laker;
            laker = index;
            index = arsenal;
        }
        ListNode one = head;
        ListNode two = laker;
        //这里比我写的好多了。
        while (one != null && two != null) {
            ListNode oneNext = one.next;
            ListNode twoNext = two.next;
            one.next = two;
            two.next = oneNext;
            one = oneNext;
            two = twoNext;
        }
        System.out.println(head);
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        ListNode p = new ListNode(a);
        reorderList2(p);
    }
}
