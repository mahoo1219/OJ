package LeetCode;

/**
 * 328. Odd Even Linked List
 * Given a singly linked list, group all odd nodes together followed by the even nodes.
 * The program should run in O(1) space complexity and O(nodes) time complexity.
 * Note:The relative order inside both the even and odd groups should remain as it was in the input.
 * The first node is considered odd, the second node even and so on ...
 */
public class OddEvenList {
    public static ListNode oddEvenList(ListNode head) {
        ListNode oddHead = new ListNode(1);
        ListNode evenHead = new ListNode(0);
        ListNode odd = oddHead, even = evenHead;

        boolean flag = true;
        while (head != null) {
            if (flag) {
                odd.next = head;
                odd = odd.next;
                flag = false;
            } else {
                even.next = head;
                even = even.next;
                flag = true;
            }
            head = head.next;
        }
        even.next = null;
        odd.next = evenHead.next;
        return oddHead.next;
    }

    // 选择原来的 head 作为一个链表头结点。
    public ListNode oddEvenList2(ListNode head) {
        if (head == null) return null;
        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }

        odd.next = evenHead;
        return head;
    }


    public static void main(String[] args) {
        int[] a = {2, 1, 4, 3, 6, 5, 7, 8};
        ListNode p = new ListNode(a);
        System.out.println(p);

        System.out.println(oddEvenList(p));
    }
}
