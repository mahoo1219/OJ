package LeetCode;

/**
 * 24. Swap Nodes in Pairs
 */
public class SwapPairNodes {
    public ListNode swapPairs(ListNode head) {
        if (head == null)
            return null;
        if (head.next == null)
            return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy, first = head, second = head.next;

        while (first != null && second != null) {
            prev.next = second;
            first.next = second.next;
            second.next = first;

            prev = first;
            first = prev.next;
            if (first != null)
                second = first.next;
        }
        return dummy.next;
    }

    public ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode nextNode = head.next;
        head.next = swapPairs2(nextNode.next);
        nextNode.next = head;
        return nextNode;
    }

    public ListNode swapPairs3(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = head;
        ListNode newHead = head.next;
        while (cur != null && cur.next != null) {
            ListNode tmp = cur;
            cur = cur.next;
            tmp.next = cur.next;
            cur.next = tmp;
            cur = tmp.next;
            if (cur != null && cur.next != null) tmp.next = cur.next; // 这里链接到下一个Pair中的后一个元素，否则会丢失
        }
        return newHead;
    }
}
