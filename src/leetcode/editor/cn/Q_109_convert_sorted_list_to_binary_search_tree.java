package leetcode.editor.cn.q_109;

import leetcode.editor.cn.def.*;

import java.util.*;

//<p>给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。</p>
//
//<p>本题中，一个高度平衡二叉树是指一个二叉树<em>每个节点&nbsp;</em>的左右两个子树的高度差的绝对值不超过 1。</p>
//
//<p><strong>示例:</strong></p>
//
//<pre>给定的有序链表： [-10, -3, 0, 5, 9],
//
//一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
//
//      0
//     / \
//   -3   9
//   /   /
// -10  5
//</pre>
//<div><div>Related Topics</div><div><li>树</li><li>二叉搜索树</li><li>链表</li><li>分治</li><li>二叉树</li></div></div><br><div><li>👍 614</li><li>👎 0</li></div>

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

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    private int getLength(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

    // 由于递增序列就是中序遍历的特性
    // 只要递增遍历就可以中序构建
    ListNode globalHead;

    private TreeNode build(int lower, int upper) {
        if (lower > upper) {
            return null;
        }
        int mid = (lower + upper) / 2;
        TreeNode root = new TreeNode();
        root.left = build(lower, mid - 1);
        root.val = globalHead.val;
        globalHead = globalHead.next;
        root.right = build(mid + 1, upper);
        return root;
    }

    public TreeNode sortedListToBST(ListNode head) {
//        List<Integer> nums = convertToArrayList(head);
//        return dfs(nums, 0, nums.size() - 1);
        globalHead = head;
        int len = getLength(head);
        return build(0, len - 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
