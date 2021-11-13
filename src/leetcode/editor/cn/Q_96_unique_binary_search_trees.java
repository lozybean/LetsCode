package leetcode.editor.cn.q_96;

import leetcode.editor.cn.def.*;

import java.util.*;

//给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：5
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 19 
// 
// Related Topics 树 二叉搜索树 数学 动态规划 二叉树 👍 1384 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // G[n]: 长度为n的序列BST个数
    // F[i,n]: 以i为root的BST个数
    // G[n] = ∑F[i,n]
    // F[i,n] = G[i-1] * G[n-i]，即左子树BST个数*右子树BST个数
    private int byDp(int n) {
        int[] dp = new int[n + 1];
        // 由于后续做乘法，使用 1
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    // 递归求解，使用path缓存结果降低计算量
    private int[][] path;

    private int byLoop(int start, int end) {
        if (start > end) return 1;
        if (path[start][end] > 0) {
            return path[start][end];
        }
        int res = 0;
        for (int i = start; i <= end; i++) {
            int leftNum = byLoop(start, i - 1);
            int rightNum = byLoop(i + 1, end);
            res += leftNum * rightNum;
        }

        path[start][end] = res;
        return res;
    }

    public int numTrees(int n) {
//        path = new int[n + 1][n + 1];
//        return byLoop(1, n);
        return byDp(n);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
