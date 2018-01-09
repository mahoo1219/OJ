package LeetCode;

import java.util.ArrayList;

/**
 * 445. Add Two Numbers II
 * You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first
 * and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * Follow up: What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 */
public class AddTwoNumbersII {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        ArrayList<Integer> arr1 = new ArrayList<>();
        ListNode cur = l1;
        while (cur != null) {
            arr1.add(cur.val);
            cur = cur.next;
        }
        cur = l2;
        ArrayList<Integer> arr2 = new ArrayList<>();
        while (cur != null) {
            arr2.add(cur.val);
            cur = cur.next;
        }
        ListNode dummy = new ListNode(0);
        int sum = 0;
        for (int i = arr1.size() - 1, j = arr2.size() - 1; i >= 0 || j >= 0; i--, j--) {
            sum /= 10;
            if (i >= 0)
                sum += arr1.get(i);
            if (j >= 0)
                sum += arr2.get(j);
            ListNode node = new ListNode(sum % 10);
            node.next = dummy.next;
            dummy.next = node;
        }
        if (sum >= 10) {
            ListNode node = new ListNode(1);
            node.next = dummy.next;
            dummy.next = node;
        }
        return dummy.next;
    }
}
