package leetcode.editor.cn.q_101;

import leetcode.editor.cn.def.*;

import java.util.*;

//给定一个二叉树，检查它是否是镜像对称的。 
//
// 
//
// 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。 
//
//     1
//   / \
//  2   2
// / \ / \
//3  4 4  3
// 
//
// 
//
// 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的: 
//
//     1
//   / \
//  2   2
//   \   \
//   3    3
// 
//
// 
//
// 进阶： 
//
// 你可以运用递归和迭代两种方法解决这个问题吗？ 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 1590 👎 0


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
    private boolean isNodeSame(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }
        return node1.val == node2.val;
    }

    private boolean bfs(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        if (root.left != null && root.right != null) {
            queue.add(root.left);
            queue.add(root.right);
        } else return root.left == null && root.right == null;


        while (!queue.isEmpty()) {
            int size = queue.size();
            // 该队列中总是含有偶数个元素
            for (int i = 0; i < size; i += 2) {
                TreeNode node1 = queue.poll();
                TreeNode node2 = queue.poll();
                if (node1.val != node2.val) {
                    return false;
                }
                if (node1.left != null && node2.right != null) {
                    queue.add(node1.left);
                    queue.add(node2.right);
                } else if (node1.left != null || node2.right != null) {
                    return false;
                }
                if (node1.right != null && node2.left != null) {
                    queue.add(node1.right);
                    queue.add(node2.left);
                } else if (node1.right != null || node2.left != null) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean dfs(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }
        return node1.val == node2.val && dfs(node1.left, node2.right) && dfs(node1.right, node2.left);
    }

    public boolean isSymmetric(TreeNode root) {
//        return bfs(root);
        if(root == null) {
            return true;
        }
        return dfs(root.left, root.right);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
