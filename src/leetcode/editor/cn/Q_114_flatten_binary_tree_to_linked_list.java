package leetcode.editor.cn.q_114;

import leetcode.editor.cn.def.*;

import java.util.*;

//给你二叉树的根结点 root ，请你将它展开为一个单链表： 
//
// 
// 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。 
// 展开后的单链表应该与二叉树 先序遍历 顺序相同。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,2,5,3,4,null,6]
//输出：[1,null,2,null,3,null,4,null,5,null,6]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [0]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 树中结点数在范围 [0, 2000] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？ 
// Related Topics 栈 树 深度优先搜索 链表 二叉树 👍 962 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

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
    // 消耗栈，空间复杂度O(n)
    private void flattenByRecursive(TreeNode root) {
        if (root == null) {
            return;
        }

        flattenByRecursive(root.left);
        flattenByRecursive(root.right);

        TreeNode rightChild = root.right;
        root.right = root.left;
        root.left = null;

        TreeNode node = root;
        while (node.right != null) {
            node = node.right;
        }
        node.right = rightChild;
    }

    // 类似莫里斯遍历，但是左子树最右节点不需要连root成环，而是直接连右子树
    private void flattenByMorris(TreeNode root) {
        TreeNode pre;
        while (root != null) {
            if (root.left != null) {
                pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = root.right;
                // 重用pre，节省一点空间
                pre = root.left;
                root.left = null;
                root.right = pre;
            }
            root = root.right;
        }
    }

    public void flatten(TreeNode root) {
//        flattenByRecursive(root);
        flattenByMorris(root);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
