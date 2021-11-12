package leetcode.editor.cn.q_122;

import leetcode.editor.cn.def.*;

import java.util.*;

//给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。 
//
// 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。 
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
//
// 
//
// 示例 1: 
//
// 
//输入: prices = [7,1,5,3,6,4]
//输出: 7
//解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
//     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
// 
//
// 示例 2: 
//
// 
//输入: prices = [1,2,3,4,5]
//输出: 4
//解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
//     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
// 
//
// 示例 3: 
//
// 
//输入: prices = [7,6,4,3,1]
//输出: 0
//解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。 
//
// 
//
// 提示： 
//
// 
// 1 <= prices.length <= 3 * 10⁴ 
// 0 <= prices[i] <= 10⁴ 
// 
// Related Topics 贪心 数组 动态规划 👍 1444 👎 0


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

    // 该问题中 k 为无限大，k=k-1
    // 简化为:
    // 1. buy = max(buy, sell-price[i])
    // 2. sell = max(sell, buy+price[i])
    // 此时，(buy, sell)状态只和前一个(buy, sell)状态有关
    private int byDpK_inf(int[] prices) {
        int max_buy = -prices[0];
        int max_sell = 0;

        for (int i = 1; i < prices.length; i++) {
            max_buy = Math.max(max_buy, max_sell - prices[i]);
            max_sell = Math.max(max_sell, max_buy + prices[i]);
        }
        return max_sell;
    }

    private int byLoop(int[] prices) {
        int res = 0;
        // 可以多次买卖，那就有钱就赚
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                res += prices[i] - prices[i - 1];
            }
        }
        return res;
    }

    public int maxProfit(int[] prices) {
//        return byLoop(prices);
        return byDpK_inf(prices);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
