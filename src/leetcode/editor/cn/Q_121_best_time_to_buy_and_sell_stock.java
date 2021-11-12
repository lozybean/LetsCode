package leetcode.editor.cn.q_121;

//给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
//
// 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。 
//
// 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。 
//
// 
//
// 示例 1： 
//
// 
//输入：[7,1,5,3,6,4]
//输出：5
//解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
//     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
// 
//
// 示例 2： 
//
// 
//输入：prices = [7,6,4,3,1]
//输出：0
//解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= prices.length <= 10⁵ 
// 0 <= prices[i] <= 10⁴ 
// 
// Related Topics 数组 动态规划 👍 1926 👎 0


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

    // 该问题中 k 恒定为 1
    // 简化为:
    // 1. sell = max(sell, buy+price[i])
    // 2. buy = max(buy, -price[i])
    // 优化后和遍历的版本一致
    private int byDpK_1(int[] prices) {
        int max_buy = -prices[0];
        int max_sell = 0;

        for (int i = 1; i < prices.length; i++) {
            int newSell = Math.max(max_sell, max_buy + prices[i]);
            int newBuy = Math.max(max_buy, -prices[i]);
            max_sell = newSell;
            max_buy = newBuy;
        }
        return max_sell;
    }

    // 遍历
    // 时间复杂度：O(n)
    // 2ms 92.93% beat
    private int byLoopCost(int[] prices) {
        // cost[i] 表示第 i 天的最小成本
//        int[] cost = new int[prices.length];
//        cost[0] = prices[0];
//        for (int i = 1; i < prices.length; i++) {
//            cost[i] = Math.min(cost[i - 1], prices[i]);
//        }
        // dp 状态只和前一个状态有关，可以简化
        int minCost = prices[0];
        int maxProfit = 0;
        // 第0天卖掉不能赚钱
        for (int i = 1; i < prices.length; i++) {
            if (maxProfit < prices[i] - minCost) {
                maxProfit = prices[i] - minCost;
            }
            if (minCost > prices[i]) {
                minCost = prices[i];
            }
        }
        return maxProfit;
    }


    public int maxProfit(int[] prices) {
//        return byLoopCost(prices);
        return byDpK_1(prices);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
