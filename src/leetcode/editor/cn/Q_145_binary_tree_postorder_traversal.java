package leetcode.editor.cn.q_145;

import leetcode.editor.cn.def.*;

import java.util.*;

//给定一个二叉树，返回它的 后序 遍历。 
//
// 示例: 
//
// 输入: [1,null,2,3]  
//   1
//    \
//     2
//    /
//   3 
//
//输出: [3,2,1] 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 深度优先搜索 二叉树 👍 699 👎 0


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
    public void postorderByRecursive(List<Integer> ans, TreeNode node) {
        if (node == null) {
            return;
        }
        postorderByRecursive(ans, node.left);
        postorderByRecursive(ans, node.right);
        ans.add(node.val);
    }

    public List<Integer> postorderByStack(TreeNode node) {
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode pre = null;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                if (node.right == null || node.right == pre) {
                    // 右子树为空，或者重新走到根节点
                    ans.add(node.val);
                    pre = node;
                    node = null;
                } else {
                    // 如果有右子树，根节点重新入栈
                    stack.push(node);
                    node = node.right;
                }
            }
        }
        return ans;
    }

    private List<Integer> leftChildToPrePath( TreeNode node) {
        List<Integer> path = new ArrayList<>();
        while(node != null) {
            path.add(node.val);
            node = node.right;
        }
        Collections.reverse(path);
        return path;
    }

    public List<Integer> postorderByMorris(TreeNode node) {
        List<Integer> ans = new ArrayList<>();
        TreeNode dump = new TreeNode();
        dump.left = node;
        TreeNode pre;
        node = dump;
        while (node != null) {
            if (node.left != null) {
                // 将根节点连接到左子树的最右子树的右子树上
                pre = node.left;
                while (pre.right != null && pre.right != node) {
                    pre = pre.right;
                }

                if (pre.right == null) {
                    // 没连上，则连接
                    pre.right = node;
                    node = node.left;
                } else {
                    // 已经连上了，则断开，且走完了左子树
                    pre.right = null;
                    // 此时，反向输出 左孩子 到 前驱节点的一直往右走的路径
                    ans.addAll(this.leftChildToPrePath(node.left));
                    node = node.right;
                }
            } else {
                node = node.right;
            }
        }
        return ans;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
//        List<Integer> ans = new ArrayList<>();
//        postorderByRecursive(ans, root);
//        return ans;

//        return postorderByStack(root);
        return postorderByMorris(root);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
