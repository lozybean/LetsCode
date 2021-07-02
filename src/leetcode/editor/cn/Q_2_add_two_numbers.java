package leetcode.editor.cn.q_2;

import leetcode.editor.cn.def.*;

import java.util.*;

//给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。 
//
// 请你将两个数相加，并以相同形式返回一个表示和的链表。 
//
// 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 
//
// 示例 1： 
//
// 
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[7,0,8]
//解释：342 + 465 = 807.
// 
//
// 示例 2： 
//
// 
//输入：l1 = [0], l2 = [0]
//输出：[0]
// 
//
// 示例 3： 
//
// 
//输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//输出：[8,9,9,9,0,0,0,1]
// 
//
// 
//
// 提示： 
//
// 
// 每个链表中的节点数在范围 [1, 100] 内 
// 0 <= Node.val <= 9 
// 题目数据保证列表表示的数字不含前导零 
// 
// Related Topics 递归 链表 数学 
// 👍 6397 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private ListNode add(ListNode n1, ListNode n2, byte carry) {
        ListNode res = new ListNode();
        if (n1 == null && n2 == null) {
            if(carry == 0) {
                return null;
            } else {
                res.val = 1;
                return res;
            }
        } else if (n1 == null) {
            int sum = n2.val + carry;
            byte c = 0;
            if (sum > 9) {
                c = 1;
                res.val = sum - 10;
            } else {
                res.val = sum;
            }
            res.next = add(null, n2.next, c);
        } else if (n2 == null) {
            int sum = n1.val + carry;
            byte c = 0;
            if (sum > 9) {
                c = 1;
                res.val = sum - 10;
            } else {
                res.val = sum;
            }
            res.next = add(n1.next, null, c);
        } else {
            int sum = n1.val + n2.val + carry;
            byte c = 0;
            if (sum > 9) {
                c = 1;
                res.val = sum - 10;
            } else {
                res.val = sum;
            }
            res.next = add(n1.next, n2.next, c);
        }
        return res;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return add(l1, l2, (byte) 0);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
