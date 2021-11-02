package leetcode.editor.cn.q_437;

import leetcode.editor.cn.def.*;

import java.util.*;

//给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。 
//
// 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
//输出：3
//解释：和等于 8 的路径有 3 条，如图所示。
// 
//
// 示例 2： 
//
// 
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//输出：3
// 
//
// 
//
// 提示: 
//
// 
// 二叉树的节点个数的范围是 [0,1000] 
// -10⁹ <= Node.val <= 10⁹ 
// -1000 <= targetSum <= 1000 
// 
// Related Topics 树 深度优先搜索 二叉树 👍 1130 👎 0


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
    private int dfs(TreeNode node, int targetSum, int currentSum, Map<Integer, Integer> prefixSum) {
        if (node == null) {
            return 0;
        }
        currentSum += node.val;
        // 若从 pi 开始到 pn 的路径和为 targetSum，该路径唯一
        // 从root开始到 pn 的路径和为 currentSum
        // root到pi的路径和为 currentSum - targetSum
        // 路径和为targetSum的子路径，与root到pi的路径数相等
        int ans = prefixSum.getOrDefault(currentSum - targetSum, 0);
        prefixSum.put(currentSum, prefixSum.getOrDefault(currentSum, 0) + 1);
        ans += dfs(node.left, targetSum, currentSum, prefixSum);
        ans += dfs(node.right, targetSum, currentSum, prefixSum);
        prefixSum.put(currentSum, prefixSum.getOrDefault(currentSum, 0) - 1);
        return ans;
    }

    public int pathSum(TreeNode root, int targetSum) {
        Map<Integer, Integer> prefixSum = new HashMap<>();

        prefixSum.put(0, 1);
        return dfs(root, targetSum, 0, prefixSum);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
