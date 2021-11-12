package leetcode.editor.cn.q_123;

import leetcode.editor.cn.def.*;

import java.util.*;

//给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。 
//
// 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。 
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
//
// 
//
// 示例 1: 
//
// 
//输入：prices = [3,3,5,0,0,3,1,4]
//输出：6
//解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
//     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。 
//
// 示例 2： 
//
// 
//输入：prices = [1,2,3,4,5]
//输出：4
//解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。   
//     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。   
//     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
// 
//
// 示例 3： 
//
// 
//输入：prices = [7,6,4,3,1] 
//输出：0 
//解释：在这个情况下, 没有交易完成, 所以最大利润为 0。 
//
// 示例 4： 
//
// 
//输入：prices = [1]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= prices.length <= 10⁵ 
// 0 <= prices[i] <= 10⁵ 
// 
// Related Topics 数组 动态规划 👍 918 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 泛化DP算法
    // 泛化问题：
    // buy[i][k] 表示 第 i 天，第 k 次交易买入后的收益
    // sell[i][k] 表示 第 i 天， 第 k 次交易卖出的收益
    // 在卖出时，表示一次交易，k 才有变化
    // 边界条件：
    // 1. buy[0][0] = -price[0] ； 第一天买入第一笔，收益为 -price[0]；
    // 2. buy[0][1..k] = -infinity; 非法状态
    // 3. sell[i][0] = 0 ; 什么都没做
    // 4. sell[0][1..k] = -infinity; 非法状态
    // 状态转移方程:
    // 1. buy[i][k] = max(buy[i-1][k], sell[i-1][k] - price[i])
    // 2. sell[i][k] = max(sell[i-1][k], buy[i-1][k-1] + price[i])
    // (buy, sell) 只和 上一个 (buy, sell) 相关，考虑简化:
    // 1. b[k] = max(b[k], s[k]-price[i])
    // 2. s[k] = max(s[k], b[k-1]+price[i])
    // NOTE:
    // 此时会有一个状态覆盖问题，即计算 s[k] 时，b[k-1]已经是buy[i][k-1]，而不是buy[i-1][k-1]了
    // b[k-1] = buy[i][k-1] = max(buy[i-1][k-1], sell[i-1][k-1]-price[i])
    // s[k] = max(s[k], buy[i-1][k-1]+price[i], sell[i-1][k-1])
    // 相比 max(s[k], b[k-1]+price[i]) 多了 sell[i-1][k-1] 一项，相当于卖出后接着卖出，收益为0，可以省略

    // 该问题中 k 为 min(k, n / 2)
    private int byDpK_2(int[] prices) {
        int k = Math.min(2, prices.length / 2);
        if (k == 0) {
            return 0;
        }
        int[] buy = new int[k + 1];
        int[] sell = new int[k + 1];
        // 初始条件
        buy[0] = -prices[0];
        sell[0] = 0;
        for (int i = 1; i <= k; ++i) {
            // 除2防止溢出后，相减变成很大的正数
            buy[i] = sell[i] = Integer.MIN_VALUE / 2;
        }
        // 转移方程
        for (int i = 1; i < prices.length; i++) {
            buy[0] = Math.max(buy[0], sell[0] - prices[i]);
            for (int j = 1; j <= k; j++) {
                buy[j] = Math.max(buy[j], sell[j] - prices[i]);
                sell[j] = Math.max(sell[j], buy[j - 1] + prices[i]);
            }
        }

        return Math.max(sell[1], sell[k]);
    }

    public int maxProfit(int[] prices) {
        return byDpK_2(prices);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
