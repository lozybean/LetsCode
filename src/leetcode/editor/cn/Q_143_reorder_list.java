package leetcode.editor.cn.q_143;

import leetcode.editor.cn.def.*;

import java.util.*;

//给定一个单链表 L 的头节点 head ，单链表 L 表示为： 
//
// L0 → L1 → … → Ln-1 → Ln 
//请将其重新排列后变为： 
//
// L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → … 
//
// 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入: head = [1,2,3,4]
//输出: [1,4,2,3] 
//
// 示例 2: 
//
// 
//
// 
//输入: head = [1,2,3,4,5]
//输出: [1,5,2,4,3] 
//
// 
//
// 提示： 
//
// 
// 链表的长度范围为 [1, 5 * 10⁴] 
// 1 <= node.val <= 1000 
// 
// Related Topics 栈 递归 链表 双指针 👍 691 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    private void reorderWithStack(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }

        Deque<ListNode> stack = new ArrayDeque<>();
        ListNode node = head;

        while (node != null) {
            stack.push(node);
            node = node.next;
        }
        ListNode top = null;
        ListNode bottom;
        while (!stack.isEmpty()) {
            bottom = stack.pollLast();
            if (top != null) {
                top.next = bottom;
            }
            if (stack.isEmpty()) {
                bottom.next = null;
                return;
            }
            top = stack.pollFirst();

            bottom.next = top;
            top.next = null;
        }
    }

    private ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = new ListNode();
        pre.next = head;
        ListNode node = head;
        while (node != null) {
            ListNode nxt = node.next;
            node.next = pre;
            pre = node;
            node = nxt;
        }
        head.next = null;
        return pre;
    }


    private void reorderWithDoublePtr(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
            if (fast != null) {
                fast = fast.next;
            }
        }

        // 从中点开发分成两个链表
        ListNode node = head;
        ListNode reverseNode = reverseList(slow);
        // 一定是第二个链表先走完
        while (reverseNode != null) {
            ListNode nxt = node.next;
            ListNode nxtReverse = reverseNode.next;
            node.next = reverseNode;
            reverseNode.next = nxt;
            node = nxt;
            reverseNode = nxtReverse;
        }
        // 解开末端
        node.next = null;
    }

    public void reorderList(ListNode head) {
//        reorderWithStack(head);
        reorderWithDoublePtr(head);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
