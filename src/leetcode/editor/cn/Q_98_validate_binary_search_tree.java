package leetcode.editor.cn.q_98;

import leetcode.editor.cn.def.*;

import java.util.ArrayDeque;
import java.util.Deque;

//给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。 
//
// 有效 二叉搜索树定义如下： 
//
// 
// 节点的左子树只包含 小于 当前节点的数。 
// 节点的右子树只包含 大于 当前节点的数。 
// 所有左子树和右子树自身必须也是二叉搜索树。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [2,1,3]
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：root = [5,1,4,null,null,3,6]
//输出：false
//解释：根节点的值是 5 ，但是右子节点的值是 4 。
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目范围在[1, 10⁴] 内 
// -2³¹ <= Node.val <= 2³¹ - 1 
// 
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 1254 👎 0


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

    private long pre = Long.MIN_VALUE;

    // 中序遍历
    // 0ms 100.00% beat
    // 38.2MB 13.10% beat
    private boolean dfs(TreeNode node) {
        if (node == null) {
            return true;
        }
        if (!dfs(node.left)) {
            return false;
        }
        if (node.val <= pre) {
            return false;
        }
        pre = node.val;
        return dfs(node.right);
    }

    // 另一种递归方法
    private boolean dfs(TreeNode node, Integer min, Integer max) {
        if (node == null) {
            return true;
        }
        if (max != null && node.val >= max) return false;
        if (min != null && node.val <= min) return false;
        return dfs(node.left, min, node.val) && dfs(node.right, node.val, max);
    }

    // dfs 模拟栈空间，没有全局状态，更安全
    // 2ms 19.67% beat
    // 37.9MB 79.12% beat
    private boolean dfsManualStack(TreeNode node) {
        long pre = Long.MIN_VALUE;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            if (node.val <= pre) {
                return false;
            }
            pre = node.val;
            node = node.right;
        }
        return true;
    }

    public boolean isValidBST(TreeNode root) {
//        return dfs(root);
        return dfs(root, null, null);
//        return dfsManualStack(root);

    }
}
//leetcode submit region end(Prohibit modification and deletion)
