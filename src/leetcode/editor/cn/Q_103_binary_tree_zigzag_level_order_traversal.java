package leetcode.editor.cn.q_103;

import leetcode.editor.cn.def.*;

import java.util.*;

//给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。 
//
// 例如： 
//给定二叉树 [3,9,20,null,null,15,7], 
//
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回锯齿形层序遍历如下： 
//
// 
//[
//  [3],
//  [20,9],
//  [15,7]
//]
// 
// Related Topics 树 广度优先搜索 二叉树 👍 535 👎 0


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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Deque<TreeNode> ascStack = new LinkedList<>();
        Deque<TreeNode> descStack = new LinkedList<>();
        boolean odd = true;
        ascStack.push(root);
        while (!ascStack.isEmpty() || !descStack.isEmpty()) {
            List<Integer> layer = new ArrayList<>();
            int size = odd ? ascStack.size() : descStack.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = odd ? ascStack.pop() : descStack.pop();
                layer.add(node.val);
                if (odd) {
                    if (node.left != null) {
                        descStack.push(node.left);
                    }
                    if (node.right != null) {
                        descStack.push(node.right);
                    }
                } else {
                    if (node.right != null) {
                        ascStack.push(node.right);
                    }
                    if (node.left != null) {
                        ascStack.push(node.left);
                    }
                }
            }
            odd = !odd;
            ans.add(layer);
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
