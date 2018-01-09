package LeetCode;

/**
 * 86. Partition List
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 * You should preserve the original relative order of the nodes in each of the two partitions.
 */
public class PartitionList {
    public static ListNode partition(ListNode head, int x) {
        if (head == null)
            return null;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy, cur = head, before = dummy;
        while (cur != null) {  // 小于x 值的元素就给插入到前面，大于则直接跳过
            if (cur.val < x) {
                prev.next = cur.next;
                cur.next = before.next;
                before.next = cur;
                before = cur;
                prev = cur;
                cur = cur.next;
            } else {
                prev = cur;
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    public static ListNode partition2(ListNode head, int x) {
        ListNode lowerHead = new ListNode(0);
        ListNode greaterHead = new ListNode(0);
        ListNode lower = lowerHead, greater = greaterHead;
        while (head != null) {
            if (head.val < x) {
                lower.next = head;
                lower = head;
            } else {
                greater.next = head;
                greater = head;
            }
            head = head.next;
        }
        lower.next = greaterHead.next;
        greater.next = null; // cut off anything after bigger
        return lowerHead.next;
    }

    public static void main(String[] args) {
        int[] a = {1, 4, 3, 2, 5, 2};
        ListNode p = new ListNode(a);
        System.out.println(p);
        partition(p, 3);
        System.out.println(p);
    }

    // 分为两个链表，小于，大于等于，然后合并
    public ListNode partition3(ListNode head, int x) {
        ListNode smallerHead = new ListNode(0), biggerHead = new ListNode(0);
        ListNode smaller = smallerHead, bigger = biggerHead;
        while (head != null) {
            if (head.val < x) {
                smaller = smaller.next = head;
            } else {
                bigger = bigger.next = head;
            }
            head = head.next;
        }
        // no need for extra check because of fake heads
        smaller.next = biggerHead.next; // join bigger after smaller
        bigger.next = null; // cut off anything after bigger
        return smallerHead.next;
    }

    public ListNode partition4(ListNode head, int x) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p = dummy;
        ListNode tail = dummy;
        while (p != null && p.next != null) {
            if (p.next.val >= x)
                p = p.next;
            else {
                if (p == tail) {  // don't forget the edge cases when p==tail
                    tail = tail.next;
                    p = p.next;
                } else {
                    ListNode tmp = p.next;
                    p.next = tmp.next;
                    tmp.next = tail.next;
                    tail.next = tmp;
                    tail = tmp; // don't forget to move tail.
                }
            }
        }
        return dummy.next;
    }


}
