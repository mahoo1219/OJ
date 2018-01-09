package LeetCode;

/**
 * 203. Remove Linked List Elements
 * Remove all elements from a linked list of integers that have value val.
 */
public class RemoveListE {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null)
            return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else
                cur = cur.next;
        }
        return dummy.next;
    }

    public ListNode removeElements2(ListNode head, int val) {
        //if (head == null) return null;
        //head.next = removeElements(head.next, val);
        //return head.val == val ? head.next : head;
        if (head == null) return null;
        ListNode next = removeElements(head.next, val);
        if (head.val == val) return next;
        head.next = next;
        return head;
    }
}
