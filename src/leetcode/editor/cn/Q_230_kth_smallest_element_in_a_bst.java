package leetcode.editor.cn.q_230;

import leetcode.editor.cn.def.*;

import java.util.*;

//给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,1,4,null,2], k = 1
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：root = [5,3,6,2,4,null,null,1], k = 3
//输出：3
// 
//
// 
//
// 
//
// 提示： 
//
// 
// 树中的节点数为 n 。 
// 1 <= k <= n <= 10⁴ 
// 0 <= Node.val <= 10⁴ 
// 
//
// 
//
// 进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？ 
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 519 👎 0


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
    // 中序遍历
    int order;
    int res;

    // 0ms 100% beat
    // 递归dfs是有状态的，不太好
    private void dfs(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        dfs(root.left, k);
        order++;
        if (order == k) {
            res = root.val;
            return;
        }
        dfs(root.right, k);
    }

    // 0ms 100% beat
    // 手动模拟栈，函数无状态
    private int dfsWithStack(TreeNode root, int k) {
        int n = 0;
        Deque<TreeNode> stack = new LinkedList<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                // 压栈
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                if (++n == k) {
                    return root.val;
                }
                root = root.right;
            }
        }
        // 由于 n>=k ， root不会为null，不会NPE
        return root.val;
    }

    // 有额外的一次统计过程，单次查找效率低，但是频繁查找效率更高
    // 一般不会频繁对一颗不会更改的树频繁查找，若树会改变，则需要进行自平衡
    public static class Bst {
        TreeNode root;
        Map<TreeNode, Integer> nodeSize = new HashMap<>();

        public Bst(TreeNode root) {
            this.root = root;
            countSize(this.root);
        }

        // 二分法查找
        private int kthSmallest(int k) {
            TreeNode node = this.root;
            while (node != null) {
                int leftChildSize = this.nodeSize.getOrDefault(node.left, 0);
                if (leftChildSize == k - 1) {
                    return node.val;
                } else if (leftChildSize < k - 1) {
                    node = node.right;
                    k = k - 1 - leftChildSize;
                } else {
                    node = node.left;
                }
            }
            // 由于 n>=k, 走不到这个地方
            return -1;
        }

        private int countSize(TreeNode node) {
            if (node == null) {
                return 0;
            }
            this.nodeSize.put(node, 1 + countSize(node.left) + countSize(node.right));
            return this.nodeSize.get(node);
        }
    }

    public int kthSmallest(TreeNode root, int k) {
//        dfs(root, k);
//        return res;
//        return dfsWithStack(root, k);
        Bst bst = new Bst(root);
        return bst.kthSmallest(k);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
