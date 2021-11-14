package leetcode.editor.cn.q_416;

import leetcode.editor.cn.def.*;

import java.util.*;

//<p>给你一个 <strong>只包含正整数 </strong>的 <strong>非空 </strong>数组 <code>nums</code> 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。</p>
//
//<p> </p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,5,11,5]
//<strong>输出：</strong>true
//<strong>解释：</strong>数组可以分割成 [1, 5, 5] 和 [11] 。</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,2,3,5]
//<strong>输出：</strong>false
//<strong>解释：</strong>数组不能分割成两个元素和相等的子集。
//</pre>
//
//<p> </p>
//
//<p><strong>提示：</strong></p>
//
//<ul>
//	<li><code>1 <= nums.length <= 200</code></li>
//	<li><code>1 <= nums[i] <= 100</code></li>
//</ul>
//<div><div>Related Topics</div><div><li>数组</li><li>动态规划</li></div></div><br><div><li>👍 1006</li><li>👎 0</li></div>

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 分割成和相等的两个子集 === 从中选出一个子集，和为总和的一半
    // dp[i][j] 表示 拿第 i 个石头时，是否可以满足总和为 j
    // dp[i][j] = dp[i-1][j] || (dp[i-1][j-nums[i]] && j >= nums[i])
    // 即：1. 上一次就以及达到了j的总和，本次不取
    //    2. 如果容量足够装下当前值，取了之后满足条件
    // 由于 dp[i] 只受 dp[i-1] 的影响，可以省略
    // dp[j] = dp[j] || (dp[j-nums[i]] && j >= nums[i])
    // 初始条件：
    // dp[0] = true
    private boolean byDp(int[] nums) {
        // 只有一个不行
        if (nums.length < 2) return false;
        int sum = Arrays.stream(nums).sum();
        // 和是奇数也不行
        if (sum % 2 != 0) return false;
        sum = sum / 2;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;

        for (int i = 1; i < nums.length; i++) {
            // 由于只能选一个数，如果是正序的话，会依赖小的容量的状态，此时，实际上相当于 i 的位置有可能被重复取
            // 如果是从大到小，则i的位置只能取一次（默认为false）
            for (int j = sum; j >= 1; j--) {
                if(j >= nums[i]) {
                    dp[j] = dp[j] || dp[j - nums[i]];
                }
            }
        }
        return dp[sum];
    }

    public boolean canPartition(int[] nums) {
        return byDp(nums);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
