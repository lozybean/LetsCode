package leetcode.editor.cn.q_889;

import leetcode.editor.cn.def.*;

import java.util.*;

//<p>返回与给定的前序和后序遍历匹配的任何二叉树。</p>
//
//<p>&nbsp;<code>pre</code>&nbsp;和&nbsp;<code>post</code>&nbsp;遍历中的值是不同的正整数。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例：</strong></p>
//
//<pre><strong>输入：</strong>pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
//<strong>输出：</strong>[1,2,3,4,5,6,7]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul>
//	<li><code>1 &lt;= pre.length == post.length &lt;= 30</code></li>
//	<li><code>pre[]</code>&nbsp;和&nbsp;<code>post[]</code>&nbsp;都是&nbsp;<code>1, 2, ..., pre.length</code>&nbsp;的排列</li>
//	<li>每个输入保证至少有一个答案。如果有多个答案，可以返回其中一个。</li>
//</ul>
//<div><div>Related Topics</div><div><li>树</li><li>数组</li><li>哈希表</li><li>分治</li><li>二叉树</li></div></div><br><div><li>👍 194</li><li>👎 0</li></div>

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

    private TreeNode helper(int[] preorder, int preStart, int preEnd,
                            int[] postorder, int postStart, int postEnd,
                            Map<Integer, Integer> postMap) {
        if (preStart > preEnd) {
            return null;
        }
        if (preStart == preEnd) {
            return new TreeNode(preorder[preStart]);
        }
        // 前序遍历的第一个为root
        int val = preorder[preStart];
        TreeNode root = new TreeNode(val);
        // 前序遍历的第二个为左子树的root
        int leftVal = preorder[preStart + 1];
        // 左子树root在后续遍历中的位置，是最后一个元素，用于计算左子树长度
        int pos = postMap.get(leftVal);
        int leftChildLen = pos - postStart + 1;
        root.left = helper(preorder, preStart + 1, preStart + leftChildLen,
                postorder, postStart, postStart + leftChildLen - 1,
                postMap
        );
        root.right = helper(preorder, preStart + leftChildLen + 1, preEnd,
                postorder, postStart + leftChildLen, postEnd,
                postMap);
        return root;
    }

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        Map<Integer, Integer> postOrderMap = new HashMap<>();
        for (int i = 0; i < postorder.length; i++) {
            postOrderMap.put(postorder[i], i);
        }
        return helper(preorder, 0, postorder.length - 1,
                postorder, 0, postorder.length - 1,
                postOrderMap);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
