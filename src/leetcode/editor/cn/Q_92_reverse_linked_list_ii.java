package leetcode.editor.cn.q_92;

import leetcode.editor.cn.def.*;

import java.util.*;

//给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链
//表节点，返回 反转后的链表 。
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], left = 2, right = 4
//输出：[1,4,3,2,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [5], left = 1, right = 1
//输出：[5]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目为 n 
// 1 <= n <= 500 
// -500 <= Node.val <= 500 
// 1 <= left <= right <= n 
// 
//
// 
//
// 进阶： 你可以使用一趟扫描完成反转吗？ 
// Related Topics 链表 👍 1055 👎 0


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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || head.next == null || left == right) {
            return head;
        }
        // 需要翻转的节点个数
        int k = right - left + 1;
        // 前驱节点，要翻转的前一个节点
        ListNode pre = new ListNode();
        pre.next = head;
        int i = left;
        while (i > 1) {
            pre = pre.next;
            i--;
        }
        // left节点
        ListNode leftNode = pre.next;
        ListNode cur = pre.next;
        // prev 是要翻转的循环中，当前节点的上一个节点
        ListNode prev = null;
        ListNode next;
        while (k > 0) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
            k--;
        }
        leftNode.next = cur;
        pre.next = prev;
        if (left == 1) {
            return pre.next;
        } else {
            return head;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
