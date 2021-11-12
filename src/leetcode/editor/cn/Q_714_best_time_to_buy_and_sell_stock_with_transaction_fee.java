package leetcode.editor.cn.q_714;

import leetcode.editor.cn.def.*;

import java.util.*;

//给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。 
//
// 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。 
//
// 返回获得利润的最大值。 
//
// 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。 
//
// 
//
// 示例 1： 
//
// 
//输入：prices = [1, 3, 2, 8, 4, 9], fee = 2
//输出：8
//解释：能够达到的最大利润:  
//在此处买入 prices[0] = 1
//在此处卖出 prices[3] = 8
//在此处买入 prices[4] = 4
//在此处卖出 prices[5] = 9
//总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8 
//
// 示例 2： 
//
// 
//输入：prices = [1,3,7,5,10,3], fee = 3
//输出：6
// 
//
// 
//
// 提示： 
//
// 
// 1 <= prices.length <= 5 * 10⁴ 
// 1 <= prices[i] < 5 * 10⁴ 
// 0 <= fee < 5 * 10⁴ 
// 
// Related Topics 贪心 数组 动态规划 👍 572 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 泛化DP算法
    // 泛化问题：
    // buy[i][k] 表示 第 i 天，第 k 次交易买入后的收益
    // sell[i][k] 表示 第 i 天， 第 k 次交易卖出的收益
    // 在卖出时，表示一次交易，k 才有变化, 交易费用在卖出时结算
    // 边界条件：
    // 1. buy[0][0] = -price[0] ； 第一天买入第一笔，收益为 -price[0]；
    // 2. buy[0][1..k] = -infinity; 非法状态
    // 3. sell[i][0] = 0 ; 什么都没做
    // 4. sell[0][1..k] = -infinity; 非法状态
    // 状态转移方程:
    // 1. buy[i][k] = max(buy[i-1][k], sell[i-1][k] - price[i])
    // 2. sell[i][k] = max(sell[i-1][k], buy[i-1][k-1] + price[i] - fee)
    // (buy, sell) 只和 上一个 (buy, sell) 相关，考虑简化:
    // 1. b[k] = max(b[k], s[k]-price[i])
    // 2. s[k] = max(s[k], b[k-1]+price[i]-fee)

    // 该问题中 k 为无限大，k=k-1
    // 再次简化为:
    // 1. buy = max(buy, sell-price[i])
    // 2. sell = max(sell, buy+prices[i]-fee)
    public int maxProfit(int[] prices, int fee) {
        int buy = -prices[0];
        int sell = 0;
        for (int i = 1; i < prices.length; i++) {
            int newBuy = Math.max(buy, sell - prices[i]);
            int newSell = Math.max(sell, buy + prices[i] - fee);
            buy = newBuy;
            sell = newSell;
        }
        return sell;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
