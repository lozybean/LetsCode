package leetcode.editor.cn.q_23;

import leetcode.editor.cn.def.*;

import java.util.*;

//给你一个链表数组，每个链表都已经按升序排列。 
//
// 请你将所有链表合并到一个升序链表中，返回合并后的链表。 
//
// 
//
// 示例 1： 
//
// 输入：lists = [[1,4,5],[1,3,4],[2,6]]
//输出：[1,1,2,3,4,4,5,6]
//解释：链表数组如下：
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//将它们合并到一个有序链表中得到。
//1->1->2->3->4->4->5->6
// 
//
// 示例 2： 
//
// 输入：lists = []
//输出：[]
// 
//
// 示例 3： 
//
// 输入：lists = [[]]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// k == lists.length 
// 0 <= k <= 10^4 
// 0 <= lists[i].length <= 500 
// -10^4 <= lists[i][j] <= 10^4 
// lists[i] 按 升序 排列 
// lists[i].length 的总和不超过 10^4 
// 
// Related Topics 链表 分治 堆（优先队列） 归并排序 
// 👍 1364 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

class Solution {
    // O(n^2)
    private ListNode simpleMerge(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        ListNode res;
        int min = 0;
        for (int i = 1; i < lists.length; i++) {
            if (lists[min] == null || lists[i] != null && lists[min].val > lists[i].val) {
                min = i;
            }
        }
        res = lists[min];
        if (res != null) {
            lists[min] = lists[min].next;
            res.next = simpleMerge(lists);
        }
        return res;
    }

    public ListNode mergeTwoList(ListNode n1, ListNode n2) {
        ListNode res;
        if (n1 == null && n2 == null) {
            return null;
        } else if (n1 == null) {
            res = n2;
            res.next = mergeTwoList(null, n2.next);
        } else if (n2 == null) {
            res = n1;
            res.next = mergeTwoList(n1.next, null);
        } else if (n1.val > n2.val) {
            res = n2;
            res.next = mergeTwoList(n1, n2.next);
        } else {
            res = n1;
            res.next = mergeTwoList(n1.next, n2);
        }
        return res;
    }

    public ListNode binaryMerge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        int mid = (l + r) >> 1;
        return mergeTwoList(binaryMerge(lists, l, mid), binaryMerge(lists, mid + 1, r));
    }

    public ListNode mergeKLists(ListNode[] lists) {
        return binaryMerge(lists, 0, lists.length - 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
