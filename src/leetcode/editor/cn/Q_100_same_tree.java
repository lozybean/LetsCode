package leetcode.editor.cn.q_100;

import leetcode.editor.cn.def.*;

import java.util.*;

//给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。 
//
// 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。 
//
// 
//
// 示例 1： 
//
// 
//输入：p = [1,2,3], q = [1,2,3]
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：p = [1,2], q = [1,null,2]
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：p = [1,2,1], q = [1,1,2]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 两棵树上的节点数目都在范围 [0, 100] 内 
// -10⁴ <= Node.val <= 10⁴ 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 708 👎 0


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
    public boolean byRecursive(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        boolean res = p.val == q.val;
        boolean leftChildIsSameTree = byRecursive(p.left, q.left);
        boolean rightChildIsSameTree = byRecursive(p.right, q.right);
        return res && leftChildIsSameTree && rightChildIsSameTree;
    }

    public boolean byMorris(TreeNode p, TreeNode q) {
        TreeNode pre1;
        TreeNode pre2;
        while (p != null && q != null) {
            if (p.val != q.val) {
                return false;
            }
            if (p.left != null && q.left != null) {
                pre1 = p.left;
                pre2 = q.left;
                if (pre1.val != pre2.val) {
                    return false;
                }
                while (pre1.right != null && pre1.right != p
                        && pre2.right != null && pre2.right != q) {
                    pre1 = pre1.right;
                    pre2 = pre2.right;
                    if (pre1.val != pre2.val) {
                        return false;
                    }
                }

                if (pre1.right == null && pre2.right == null) {
                    // 都未成环，且到达最右
                    pre1.right = p;
                    pre2.right = q;
                    p = p.left;
                    q = q.left;
                } else if (pre1.right == p && pre2.right == q) {
                    // 都已成环
                    pre1.right = null;
                    pre2.right = null;
                    p = p.right;
                    q = q.right;
                } else {
                    return false;
                }
            } else if (p.left == null && q.left == null) {
                p = p.right;
                q = q.right;
            } else {
                return false;
            }
        }
        return p == null && q == null;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
//        return byRecursive(p, q);
        return byMorris(p, q);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
