package leetcode.editor.cn.q_144;

import leetcode.editor.cn.def.*;

import java.util.*;

//给你二叉树的根节点 root ，返回它节点值的 前序 遍历。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,null,2,3]
//输出：[1,2,3]
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
//输入：root = [1]
//输出：[1]
// 
//
// 示例 4： 
//
// 
//输入：root = [1,2]
//输出：[1,2]
// 
//
// 示例 5： 
//
// 
//输入：root = [1,null,2]
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 100] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶：递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 深度优先搜索 二叉树 👍 671 👎 0


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
    // 1. 递归
    private void preorderByRecursive(List<Integer> ans, TreeNode node) {
        if (node == null) {
            return;
        }
        ans.add(node.val);
        preorderByRecursive(ans, node.left);
        preorderByRecursive(ans, node.right);
    }

    // 2. 模拟递归栈
    private List<Integer> preorderByStack(TreeNode node) {
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                ans.add(node.val);
                // 递归开始，压栈
                stack.push(node);
                node = node.left;
            } else {
                // node 为空，出栈
                node = stack.pop();
                node = node.right;
            }
        }
        return ans;
    }

    // 3. 莫里斯遍历
    private List<Integer> preorderByMorris(TreeNode node) {
        List<Integer> ans = new ArrayList<>();
        TreeNode pre;
        while (node != null) {
            if (node.left != null) {
                // 左子树的最右子数 的 右子树 连到根节点
                pre = node.left;
                while (pre.right != null && pre.right != node) {
                    pre = pre.right;
                }
                if (pre.right == null) {
                    // 没有连上，则先连上
                    pre.right = node;
                    ans.add(node.val);
                    node = node.left;
                } else {
                    // 已经连上，走完了一圈，解开
                    pre.right = null;
                    node = node.right;
                }
            } else {
                ans.add(node.val);
                node = node.right;
            }
        }
        return ans;
    }

    public List<Integer> preorderTraversal(TreeNode root) {

//        List<Integer> ans = new ArrayList<>();
//        preorderByRecursive(ans, root);
//        return ans;

//        return preorderByStack(root);
        return preorderByMorris(root);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
