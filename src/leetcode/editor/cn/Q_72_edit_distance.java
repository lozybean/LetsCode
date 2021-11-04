package leetcode.editor.cn.q_72;

import leetcode.editor.cn.def.*;

import java.util.*;

//给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。 
//
// 你可以对一个单词进行如下三种操作： 
//
// 
// 插入一个字符 
// 删除一个字符 
// 替换一个字符 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：word1 = "horse", word2 = "ros"
//输出：3
//解释：
//horse -> rorse (将 'h' 替换为 'r')
//rorse -> rose (删除 'r')
//rose -> ros (删除 'e')
// 
//
// 示例 2： 
//
// 
//输入：word1 = "intention", word2 = "execution"
//输出：5
//解释：
//intention -> inention (删除 't')
//inention -> enention (将 'i' 替换为 'e')
//enention -> exention (将 'n' 替换为 'x')
//exention -> exection (将 'n' 替换为 'c')
//exection -> execution (插入 'u')
// 
//
// 
//
// 提示： 
//
// 
// 0 <= word1.length, word2.length <= 500 
// word1 和 word2 由小写英文字母组成 
// 
// Related Topics 字符串 动态规划 👍 1897 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minDistance(String word1, String word2) {
        // dp[i][j]表示word1[0:i]，word2[0:j]的编辑距离
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        // base case
        // 当word1长度为0时，最小操作是往word1不断插入word2的字母
        for (int i = 0; i <= word2.length(); i++) {
            dp[0][i] = i;
        }
        // 同理
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                // 状态转移方程:
                // 1. 如果当前位置字母相同，不需要操作，操作次数和上一个状态一致
                // 注意字符串的下标是从0开始
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(
                            Math.min(
                                    dp[i - 1][j] + 1, // word1 插入一个字母
                                    dp[i - 1][j - 1] + 1 // word1 修改一个字母
                            ),
                            dp[i][j - 1] + 1 // word1 删除一个字母
                    );
                }
            }
        }

        return dp[word1.length()][word2.length()];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
