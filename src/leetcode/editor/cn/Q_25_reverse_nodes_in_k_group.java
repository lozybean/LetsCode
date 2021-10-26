package leetcode.editor.cn.q_25;

import leetcode.editor.cn.def.*;

//给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。 
//
// k 是一个正整数，它的值小于或等于链表的长度。 
//
// 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。 
//
// 进阶： 
//
// 
// 你可以设计一个只使用常数额外空间的算法来解决此问题吗？ 
// 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], k = 2
//输出：[2,1,4,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1,2,3,4,5], k = 3
//输出：[3,2,1,4,5]
// 
//
// 示例 3： 
//
// 
//输入：head = [1,2,3,4,5], k = 1
//输出：[1,2,3,4,5]
// 
//
// 示例 4： 
//
// 
//输入：head = [1], k = 1
//输出：[1]
// 
//
// 
// 
//
// 提示： 
//
// 
// 列表中节点的数量在范围 sz 内 
// 1 <= sz <= 5000 
// 0 <= Node.val <= 1000 
// 1 <= k <= sz 
// 
// Related Topics 递归 链表 👍 1334 👎 0


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
    // 翻转一个子链表，并返回新的头和尾
    // 对于新的头，应当使用前驱节点重新连接
    private void reverse(ListNode head, ListNode tail) {
        // 原尾部是新头部的next
        ListNode prev = tail.next;
        ListNode node = head;
        while (tail != prev) {
            ListNode nxt = node.next;
            node.next = prev;
            prev = node;
            node = nxt;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode newHead = null;
        // 前驱虚拟节点，连着子链表的头节点
        ListNode pre = new ListNode();
        pre.next = head;
        // 子链表尾
        ListNode tail = pre;
        // 上一个子链表的尾部
        ListNode preTail = tail;
        while (pre != null) {
            for (int i = 0; i < k; i++) {
                tail = tail.next;
                if (tail == null) {
                    if (newHead == null) {
                        return head;
                    } else {
                        return newHead;
                    }
                }
            }
            head = pre.next;
            // 交换头尾，交换后，tail为头，head为尾
            reverse(head, tail);
            // 上一个子链表尾部连接到当前头部
            preTail.next = tail;
            if (newHead == null) {
                // 只需要返回第一次翻转的头部
                newHead = tail;
            }
            // 从新的尾部继续往前走
            pre = head;
            tail = pre;
            preTail = tail;
        }
        return newHead;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
