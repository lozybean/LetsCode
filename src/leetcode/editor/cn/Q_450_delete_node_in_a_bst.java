package leetcode.editor.cn.q_450;

import leetcode.editor.cn.def.*;

import java.util.*;

//给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的
//根节点的引用。 
//
// 一般来说，删除节点可分为两个步骤： 
//
// 
// 首先找到需要删除的节点； 
// 如果找到了，删除它。 
// 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入：root = [5,3,6,2,4,null,7], key = 3
//输出：[5,4,6,2,null,null,7]
//解释：给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
//一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
//另一个正确答案是 [5,2,6,null,4,null,7]。
//
//
// 
//
// 示例 2: 
//
// 
//输入: root = [5,3,6,2,4,null,7], key = 0
//输出: [5,3,6,2,4,null,7]
//解释: 二叉树不包含值为 0 的节点
// 
//
// 示例 3: 
//
// 
//输入: root = [], key = 0
//输出: [] 
//
// 
//
// 提示: 
//
// 
// 节点数的范围 [0, 10⁴]. 
// -10⁵ <= Node.val <= 10⁵ 
// 节点值唯一 
// root 是合法的二叉搜索树 
// -10⁵ <= key <= 10⁵ 
// 
//
// 
//
// 进阶： 要求算法时间复杂度为 O(h)，h 为树的高度。 
// Related Topics 树 二叉搜索树 二叉树 👍 558 👎 0


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
    // 替换Node，当node中包含其他属性时，使用替换更加可靠
    private TreeNode changeNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (root.val == key) {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            // leftMax 的 parent
            TreeNode node = root;
            TreeNode rightMin = node.right;
            boolean rightChild = true;
            while (rightMin.left != null) {
                node = rightMin;
                rightMin = rightMin.left;
                rightChild = false;
            }
            TreeNode minRightChild = rightMin.right;
            if (root.left != rightMin) {
                rightMin.left = root.left;
            }
            if (root.right != rightMin) {
                rightMin.right = root.right;
            }
            root = rightMin;
            if (rightChild) {
                node.right = null;
            } else {
                node.left = minRightChild;
            }
        } else if (root.val > key) {
            root.left = changeNode(root.left, key);
        } else {
            root.right = changeNode(root.right, key);
        }
        return root;
    }

    // 只替换node的值，而不实际替换node
    private TreeNode changeVal(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val > key) {
            root.left = changeVal(root.left, key);
        } else if (root.val < key) {
            root.right = changeNode(root.right, key);
        } else if (root.left == null) {
            return root.right;
        } else if (root.right == null) {
            return root.left;
        } else {
            TreeNode rightMin = root.right;
            while (rightMin.left != null) {
                rightMin = rightMin.left;
            }
            root.val = rightMin.val;
            // 递归替换
            root.right = changeVal(root.right, root.val);
        }
        return root;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        return changeVal(root, key);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
