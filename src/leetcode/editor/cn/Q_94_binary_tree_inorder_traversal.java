package leetcode.editor.cn.q_94;

import leetcode.editor.cn.def.*;

import java.util.*;

//给定一个二叉树的根节点 root ，返回它的 中序 遍历。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,null,2,3]
//输出：[1,3,2]
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
//输出：[2,1]
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
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 深度优先搜索 二叉树 👍 1144 👎 0


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
    public void inorderByRecursive(List<Integer> ans, TreeNode node) {
        if (node == null) {
            return;
        }
        inorderByRecursive(ans, node.left);
        ans.add(node.val);
        inorderByRecursive(ans, node.right);
    }

    public List<Integer> inorderByStack(TreeNode node) {
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                // 压栈
                stack.push(node);
                node = node.left;
            } else {
                // 出栈
                node = stack.pop();
                ans.add(node.val);
                node = node.right;
            }
        }
        return ans;
    }

    public List<Integer> inorderByMorris(TreeNode node) {
        List<Integer> ans = new ArrayList<>();
        TreeNode pre;
        while (node != null) {
            if (node.left != null) {
                // 将node连接到左子树最右子树的右子树
                pre = node.left;
                while (pre.right != null && pre.right != node) {
                    pre = pre.right;
                }

                if (pre.right == null) {
                    // 未连接上，则连接
                    pre.right = node;
                    node = node.left;
                } else {
                    // 已连接上，则断开，并意味着走完了左子树
                    pre.right = null;
                    ans.add(node.val);
                    node = node.right;
                }
            } else {
                ans.add(node.val);
                node = node.right;
            }
        }
        return ans;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
//        List<Integer> ans = new ArrayList<>();
//        inorderByRecursive(ans, root);
//        return ans;

//        return inorderByStack(root);
        return inorderByMorris(root);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
