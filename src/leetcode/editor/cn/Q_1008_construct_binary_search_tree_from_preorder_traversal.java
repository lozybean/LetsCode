package leetcode.editor.cn.q_1008;

import leetcode.editor.cn.def.*;

import java.util.*;

//<p>返回与给定前序遍历&nbsp;<code>preorder</code> 相匹配的二叉搜索树（binary <strong>search</strong> tree）的根结点。</p>
//
//<p><em>(回想一下，二叉搜索树是二叉树的一种，其每个节点都满足以下规则，对于&nbsp;<code>node.left</code>&nbsp;的任何后代，值总 <code>&lt; node.val</code>，而 <code>node.right</code> 的任何后代，值总 <code>&gt; node.val</code>。此外，前序遍历首先显示节点&nbsp;<code>node</code> 的值，然后遍历 <code>node.left</code>，接着遍历 <code>node.right</code>。）</em></p>
//
//<p>题目保证，对于给定的测试用例，总能找到满足要求的二叉搜索树。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例：</strong></p>
//
//<pre><strong>输入：</strong>[8,5,1,7,10,12]
//<strong>输出：</strong>[8,5,10,1,7,null,12]
//<img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/03/08/1266.png" style="height: 200px; width: 306px;">
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul>
//	<li><code>1 &lt;= preorder.length &lt;= 100</code></li>
//	<li><code>1 &lt;= preorder[i]&nbsp;&lt;= 10^8</code></li>
//	<li><code>preorder</code> 中的值互不相同</li>
//</ul>
//<div><div>Related Topics</div><div><li>栈</li><li>树</li><li>二叉搜索树</li><li>数组</li><li>二叉树</li><li>单调栈</li></div></div><br><div><li>👍 176</li><li>👎 0</li></div>

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
    int index = 0;

    private TreeNode helper(int[] preorder, int lower, int upper) {
        if (index >= preorder.length) return null;
        int val = preorder[index];
        if (val < lower || val > upper) {
            return null;
        }
        index++;
        TreeNode root = new TreeNode(val);
        root.left = helper(preorder, lower, val);
        root.right = helper(preorder, val, upper);
        return root;
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        return helper(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
