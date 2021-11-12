package leetcode.editor.cn.q_337;

import leetcode.editor.cn.def.*;

import java.util.*;

//<p>在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为&ldquo;根&rdquo;。 除了&ldquo;根&rdquo;之外，每栋房子有且只有一个&ldquo;父&ldquo;房子与之相连。一番侦察之后，聪明的小偷意识到&ldquo;这个地方的所有房屋的排列类似于一棵二叉树&rdquo;。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。</p>
//
//<p>计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre><strong>输入: </strong>[3,2,3,null,3,null,1]
//
//     <strong>3</strong>
//    / \
//   2   3
//    \   \ 
//     <strong>3</strong>   <strong>1</strong>
//
//<strong>输出:</strong> 7 
//<strong>解释:</strong>&nbsp;小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = <strong>7</strong>.</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre><strong>输入: </strong>[3,4,5,1,3,null,1]
//
//&nbsp;    3
//    / \
//   <strong>4</strong>   <strong>5</strong>
//  / \   \ 
// 1   3   1
//
//<strong>输出:</strong> 9
//<strong>解释:</strong>&nbsp;小偷一晚能够盗取的最高金额&nbsp;= <strong>4</strong> + <strong>5</strong> = <strong>9</strong>.
//</pre>
//<div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>动态规划</li><li>二叉树</li></div></div><br><div><li>👍 1034</li><li>👎 0</li></div>

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
    private int[] dp(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] left = dp(root.left);
        int[] right = dp(root.right);
        int rob = root.val + left[0] + right[0];
        int no_rob = Math.max(left[0], left[1])
                + Math.max(right[0], right[1]);
        return new int[]{no_rob, rob};
    }

    public int rob(TreeNode root) {
        int[] res = dp(root);
        return Math.max(res[0], res[1]);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
