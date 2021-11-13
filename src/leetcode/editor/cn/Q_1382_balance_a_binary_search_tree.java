package leetcode.editor.cn.q_1382;

import leetcode.editor.cn.def.*;

import java.util.*;

//<p>给你一棵二叉搜索树，请你返回一棵&nbsp;<strong>平衡后</strong>&nbsp;的二叉搜索树，新生成的树应该与原来的树有着相同的节点值。</p>
//
//<p>如果一棵二叉搜索树中，每个节点的两棵子树高度差不超过 1 ，我们就称这棵二叉搜索树是&nbsp;<strong>平衡的</strong> 。</p>
//
//<p>如果有多种构造方法，请你返回任意一种。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例：</strong></p>
//
//<p><strong><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/03/15/1515_ex1.png" style="height: 248px; width: 250px;"><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/03/15/1515_ex1_out.png" style="height: 200px; width: 200px;"></strong></p>
//
//<pre><strong>输入：</strong>root = [1,null,2,null,3,null,4,null,null]
//<strong>输出：</strong>[2,1,3,null,null,null,4]
//<strong>解释：</strong>这不是唯一的正确答案，[3,1,4,null,2,null,null] 也是一个可行的构造方案。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul>
//	<li>树节点的数目在&nbsp;<code>1</code>&nbsp;到&nbsp;<code>10^4</code>&nbsp;之间。</li>
//	<li>树节点的值互不相同，且在&nbsp;<code>1</code>&nbsp;到&nbsp;<code>10^5</code> 之间。</li>
//</ul>
//<div><div>Related Topics</div><div><li>贪心</li><li>树</li><li>深度优先搜索</li><li>二叉搜索树</li><li>分治</li><li>二叉树</li></div></div><br><div><li>👍 81</li><li>👎 0</li></div>

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
    // 中序遍历得到递增序列
    private void dfs(TreeNode root, List<Integer> res) {
        if (root == null) return;
        dfs(root.left, res);
        res.add(root.val);
        dfs(root.right, res);
    }

    private TreeNode build(List<Integer> nums, int lower, int upper) {
        if (lower > upper) {
            return null;
        }
        int mid = (lower + upper) / 2;
        TreeNode root = new TreeNode(nums.get(mid));
        root.left = build(nums, lower, mid - 1);
        root.right = build(nums, mid + 1, upper);
        return root;
    }

    public TreeNode balanceBST(TreeNode root) {
        List<Integer> nums = new ArrayList<>();
        dfs(root, nums);
        return build(nums, 0, nums.size() - 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
