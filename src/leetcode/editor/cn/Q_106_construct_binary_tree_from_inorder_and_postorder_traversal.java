package leetcode.editor.cn.q_106;

import leetcode.editor.cn.def.*;

import java.util.*;

//根据一棵树的中序遍历与后序遍历构造二叉树。 
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 中序遍历 inorder = [9,3,15,20,7]
//后序遍历 postorder = [9,15,7,20,3] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
// Related Topics 树 数组 哈希表 分治 二叉树 👍 607 👎 0


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
    private TreeNode recoveryByInorderAndPostOrder(int[] inorder, int inStart, int inEnd,
                                                   int[] postorder, int postStart, int postEnd,
                                                   Map<Integer, Integer> inMap) {
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postEnd]);
        int rootPosition = inMap.get(root.val);
        int leftChildLen = rootPosition - inStart;

        root.left = recoveryByInorderAndPostOrder(inorder, inStart, rootPosition - 1,
                postorder, postStart, leftChildLen + postStart - 1,
                inMap);
        root.right = recoveryByInorderAndPostOrder(inorder, rootPosition + 1, inEnd,
                postorder, postStart + leftChildLen, postEnd - 1,
                inMap);
        return root;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return recoveryByInorderAndPostOrder(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, map);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
