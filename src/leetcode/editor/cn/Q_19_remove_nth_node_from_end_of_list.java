package leetcode.editor.cn.q_19;

import leetcode.editor.cn.def.*;

import java.util.*;

//给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], n = 2
//输出：[1,2,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1], n = 1
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1,2], n = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中结点的数目为 sz 
// 1 <= sz <= 30 
// 0 <= Node.val <= 100 
// 1 <= n <= sz 
// 
//
// 
//
// 进阶：你能尝试使用一趟扫描实现吗？ 
// Related Topics 链表 双指针 👍 1654 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p1 = head;
        // 先往前走k步
        for(int i=0;i<n;i++){
            p1=p1.next;
        }
        // p2和p1同时走n-k步，p1到头时，p2走了n-k，即倒数第k个
        // p2 多余，只需要找到倒数 k+1 个即可
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode pre = dummy;
        // pre为倒数第k+1个
//        ListNode p2 =head;
        while(p1 != null){
            p1=p1.next;
            pre = pre.next;
//            p2=p2.next;
        }
        if(pre.next != null) {
            pre.next = pre.next.next;
        }
        return dummy.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
