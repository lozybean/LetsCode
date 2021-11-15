package leetcode.editor.cn.q_494;

import leetcode.editor.cn.def.*;

import java.util.*;

//给你一个整数数组 nums 和一个整数 target 。 
//
// 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ： 
//
// 
// 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。 
// 
//
// 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,1,1,1], target = 3
//输出：5
//解释：一共有 5 种方法让最终目标和为 3 。
//-1 + 1 + 1 + 1 + 1 = 3
//+1 - 1 + 1 + 1 + 1 = 3
//+1 + 1 - 1 + 1 + 1 = 3
//+1 + 1 + 1 - 1 + 1 = 3
//+1 + 1 + 1 + 1 - 1 = 3
// 
//
// 示例 2： 
//
// 
//输入：nums = [1], target = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 20 
// 0 <= nums[i] <= 1000 
// 0 <= sum(nums[i]) <= 1000 
// -1000 <= target <= 1000 
// 
// Related Topics 数组 动态规划 回溯 👍 945 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        // sum - neg = pos
        // target = pos - neg
        // target = sum - 2 * neg
        // neg = (sum - target) / 2
        int sum = Arrays.stream(nums).sum();
        if (sum < target) {
            return 0;
        }
        if ((sum - target) % 2 != 0) {
            return 0;
        }
        int neg = (sum - target) / 2;

        // 从数组中选择若干个数，使其和为neg，转换为 01背包问题
        // dp[i] 为和为i时，可选择的数量
        int[] dp = new int[neg + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = neg; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[neg];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
