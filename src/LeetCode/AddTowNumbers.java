package LeetCode;

/**
 * 2. Add Two Numbers
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order
 * and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 */
public class AddTowNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        else if (l2 == null)
            return l1;

        ListNode head = new ListNode(0);
        boolean flag = false;
        ListNode cur = head;
        int val;
        while (l1 != null || l2 != null) {
            if (l1 != null && l2 != null)
                val = l1.val + l2.val + (flag ? 1 : 0);
            else if (l1 != null)
                val = l1.val + (flag ? 1 : 0);
            else
                val = l2.val + (flag ? 1 : 0);
            if (val > 9) {
                val %= 10;
                flag = true;
            } else
                flag = false;
            ListNode node = new ListNode(val);
            cur.next = node;
            cur = cur.next;
            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }
        if (flag) {
            ListNode node = new ListNode(1);
            cur.next = node;
        }
        return head.next;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        int sum = 0;
        ListNode cur = dummy;
        while (l1 != null || l2 != null) {
            sum = sum / 10;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
        }
        if (sum >= 10)
            cur.next = new ListNode(1);
        return dummy.next;
    }

    // 尽量不要修改传递进入参数的值
    public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
}
