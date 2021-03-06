package leetcode.editor.cn.q_105;

import leetcode.editor.cn.def.*;

import java.util.*;

//给定一棵树的前序遍历 preorder 与中序遍历 inorder。请构造二叉树并返回其根节点。 
//
// 
//
// 示例 1: 
//
// 
//Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//Output: [3,9,20,null,null,15,7]
// 
//
// 示例 2: 
//
// 
//Input: preorder = [-1], inorder = [-1]
//Output: [-1]
// 
//
// 
//
// 提示: 
//
// 
// 1 <= preorder.length <= 3000 
// inorder.length == preorder.length 
// -3000 <= preorder[i], inorder[i] <= 3000 
// preorder 和 inorder 均无重复元素 
// inorder 均出现在 preorder 
// preorder 保证为二叉树的前序遍历序列 
// inorder 保证为二叉树的中序遍历序列 
// 
// Related Topics 树 数组 哈希表 分治 二叉树 👍 1274 👎 0


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
    private TreeNode recoveryByPreorderAndInorder(int[] preorder, int preStart, int preEnd, int inStart,
                                                  Map<Integer, Integer> inMap) {
        if (preStart > preEnd) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preStart]);
        int rootPosition = inMap.get(root.val);
        int leftChildLen = rootPosition - inStart;
        root.left = recoveryByPreorderAndInorder(preorder, preStart + 1, preStart + leftChildLen,
                inStart,
                inMap);
        root.right = recoveryByPreorderAndInorder(preorder, preStart + leftChildLen + 1, preEnd,
                rootPosition + 1,
                inMap);
        return root;
    }


    public TreeNode buildTree(int[] preorder, int[] inorder) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return recoveryByPreorderAndInorder(preorder, 0, preorder.length - 1,  0, map);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
