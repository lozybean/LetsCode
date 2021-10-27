package leetcode.editor.cn.q_21;

import leetcode.editor.cn.def.*;

import java.util.*;

//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
//
// 
//
// 示例 1： 
//
// 
//输入：l1 = [1,2,4], l2 = [1,3,4]
//输出：[1,1,2,3,4,4]
// 
//
// 示例 2： 
//
// 
//输入：l1 = [], l2 = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：l1 = [], l2 = [0]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 两个链表的节点数目范围是 [0, 50] 
// -100 <= Node.val <= 100 
// l1 和 l2 均按 非递减顺序 排列 
// 
// Related Topics 递归 链表 
// 👍 1772 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public ListNode merge(ListNode n1, ListNode n2) {
        ListNode res;
        if (n1 == null && n2 == null) {
            return null;
        } else if (n1 == null) {
            res = n2;
        } else if (n2 == null) {
            res = n1;
        } else if (n1.val > n2.val) {
            res = n2;
            res.next = merge(n1, n2.next);
        } else {
            res = n1;
            res.next = merge(n1.next, n2);
        }
        return res;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        return merge(l1, l2);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
