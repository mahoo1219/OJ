package LeetCode;

/**
 * 147. Insertion Sort List
 */
public class InsertionSortList {
    public static ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode index = dummy, cur = head.next, prev = head;
        while (cur != null) {
            //Before insert, the prev is at the last node of the sorted list.
            //Only the last node's value is larger than the current inserting node should we move the temp back to the head
            if (prev.val > cur.val)
                index = dummy;
            while (index.next != null && index.next.val < cur.val) {
                index = index.next;
            }
            //这里保持了一条链表，没有分开，所以要判断
            if (index.next != cur) { //这里必须判断是否是自身
                prev.next = cur.next;
                cur.next = index.next; // 否则这里元素指向了自身
                index.next = cur;
                cur = prev.next;
            } else {
                prev = cur;
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    //这种方法更加简洁
    public ListNode insertionSortList2(ListNode head) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;

        ListNode cur = dummy.next;
        ListNode prev = dummy;
        while (cur != null) {
            ListNode next = cur.next;
            if (cur.val < prev.val) {
                ListNode insert = dummy;
                while (insert.next.val < cur.val)
                    insert = insert.next;

                cur.next = insert.next;
                insert.next = cur;
                prev.next = next;
            } else {
                prev = cur;
            }

            cur = next;
        }
        return dummy.next;
    }

    public static ListNode insertionSortList3(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode helper = new ListNode(0); //new starter of the sorted list
        ListNode cur = head; //the node will be inserted
        ListNode pre = helper; //insert node between pre and pre.next
        ListNode next = null; //the next node will be inserted
        //not the end of input list
        //这里把分成了两个链表
        while (cur != null) {
            next = cur.next;
            //find the right place to insert
            while (pre.next != null && pre.next.val < cur.val) {
                pre = pre.next;
            }
            //insert between pre and pre.next
            cur.next = pre.next;
            pre.next = cur;
            pre = helper;
            cur = next;
        }

        return helper.next;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3};
        ListNode p = new ListNode(a);
        System.out.println(p);
        System.out.println(insertionSortList(p));
    }
}
