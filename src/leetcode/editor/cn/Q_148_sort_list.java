package leetcode.editor.cn.q_148;

import leetcode.editor.cn.def.*;

import java.util.*;

//给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。 
//
// 进阶： 
//
// 
// 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？ 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [4,2,1,3]
//输出：[1,2,3,4]
// 
//
// 示例 2： 
//
// 
//输入：head = [-1,5,3,4,0]
//输出：[-1,0,3,4,5]
// 
//
// 示例 3： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 5 * 10⁴] 内 
// -10⁵ <= Node.val <= 10⁵ 
// 
// Related Topics 链表 双指针 分治 排序 归并排序 👍 1343 👎 0


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
    // Q21 merge two sorted list
    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode pre = new ListNode();
        ListNode node = pre;
        while (head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                node.next = head1;
                head1 = head1.next;
            } else {
                node.next = head2;
                head2 = head2.next;
            }
            node = node.next;
        }
        if (head1 == null) {
            node.next = head2;
        }
        if (head2 == null) {
            node.next = head1;
        }
        return pre.next;
    }

    // 分治排序
    private ListNode sortByDivideAndConquer(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 先计算长度
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }


        // 1. n = 1
        // 2. 每份n个，分成若干份
        // 3. 每两份合并
        // 4. 令n = n * 2 ， 重复2，直到 n = length

        // 标记前驱节点
        ListNode hairNode = new ListNode();
        hairNode.next = head;

        for (int n = 1; n < length; n <<= 1) {
            ListNode subHead = hairNode;
            node = hairNode.next;

            while (node != null) {
                // 每两份的第一份
                ListNode sub1 = node;
                // 找到这份的尾巴
                for (int i = 1; i < n; i++) {
                    if (node.next == null) {
                        // 处理最后一份提前终止
                        break;
                    }
                    node = node.next;
                }
                // 从尾处断开，并得到第二份的头
                ListNode sub2 = node.next;
                node.next = null;
                node = sub2;
                for (int i = 1; i < n; i++) {
                    if (node == null || node.next == null) {
                        // 第一份已经终止，或者第二份提前终止
                        break;
                    }
                    node = node.next;
                }
                if (node != null) {
                    ListNode nxt = node.next;
                    node.next = null;
                    node = nxt;
                }
                // 合并两份，并将新的头和前驱节点连接
                subHead.next = merge(sub1, sub2);
                while (subHead.next != null) {
                    subHead = subHead.next;
                }
            }

        }
        return hairNode.next;
    }

    public ListNode sortList(ListNode head) {
        return sortByDivideAndConquer(head);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
