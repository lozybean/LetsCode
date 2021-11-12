package leetcode.editor.cn.q_309;

import leetcode.editor.cn.def.*;

import java.util.*;

//给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。 
//
// 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）: 
//
// 
// 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
// 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。 
// 
//
// 示例: 
//
// 输入: [1,2,3,0,2]
//输出: 3 
//解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出] 
// Related Topics 数组 动态规划 👍 941 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 泛化DP算法
    // 泛化问题：
    // buy[i][k] 表示 第 i 天，第 k 次交易买入后的收益
    // cold[i][k] 表示 第 i 天， 第 k 次交易后的冷冻期
    // warm[i][k] 表示 第 i 天， 第 k 次交易卖出后，且非冷冻期
    // 在卖出时，表示一次交易，k 才有变化
    // 边界条件：
    // 1. buy[0][0] = -price[0] ； 第一天买入第一笔，收益为 -price[0]；
    // 2. buy[0][1..k] = -infinity; 非法状态
    // 3. cold[i][0] = warm[i][0] = 0 ; 什么都没做
    // 4. cold[0][1..k] = -infinity; 非法状态
    // 5. warm[0..1][1..k] = -infinity; 非法状态
    // 状态转移方程:
    // 1. buy[i][k] = max(buy[i-1][k], warm[i-1][k] - price[i])
    // 2. cold[i][k] = buy[i-1][k-1] + prices[i]
    // 3. warm[i][k] = max(warm[i-1][k], cold[i-1][k])
    // (buy, cold, warm) 只和上一个状态有关
    // 1. b[k] = max(b[k], w[k] - prices[i])
    // 2. c[k] = b[k-1] + prices[i]
    // 3. w[k] = max(w[k], c[k])

    // 该问题中 k 为无限大，k=k-1
    // 再次简化为:
    // 1. buy = max(buy, warm-price[i])
    // 2. cold = buy + prices[i]
    // 3. warm = max(warm, cold)
    public int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int buy = -prices[0];
        int cold = 0;
        int warm = 0;

        for (int i = 1; i < prices.length; i++) {
            int newBuy = Math.max(buy, warm - prices[i]);
            int newCold = buy + prices[i];
            int newWarm = Math.max(warm, cold);
            buy = newBuy;
            cold = newCold;
            warm = newWarm;
        }

        return Math.max(cold, warm);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
