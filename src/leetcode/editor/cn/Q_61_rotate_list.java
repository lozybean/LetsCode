package leetcode.editor.cn.q_61;

import leetcode.editor.cn.def.*;

import java.util.*;

//给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], k = 2
//输出：[4,5,1,2,3]
// 
//
// 示例 2： 
//
// 
//输入：head = [0,1,2], k = 4
//输出：[2,0,1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 500] 内 
// -100 <= Node.val <= 100 
// 0 <= k <= 2 * 10⁹ 
// 
// Related Topics 链表 双指针 👍 644 👎 0


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
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        // 1. 先将链表成环
        int n = 1;
        ListNode node = head;
        while (node.next != null) {
            node = node.next;
            n++;
        }
        node.next = head;
        // 2. 对于环状链表，计算起始位置
        // 整体向右移动 k ，相当于 => 从原始链表的尾部，移动 k%n 个元素到头部
        // [1,2], k=1, 相当于将 2%1= 1 个元素移动到头部 => [2,1]
        // [1,2,3,4,5], k=2, 相当于移动 2%5 = 2 个元素到头部 => [4,5,1,2,3]
        // 新头部的位置：n - k % n (初始0坐标)
        // 需要找的是新的尾部: n - k % n - 1, 并从尾部断开环形链表

        int m = n - k % n - 1;
        ListNode newTail = head;
        while (m > 0) {
            newTail = newTail.next;
            m--;
        }
        ListNode newHead = newTail.next;
        newTail.next = null;
        return newHead;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
