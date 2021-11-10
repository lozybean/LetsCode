package leetcode.editor.cn.q_300;

import leetcode.editor.cn.def.*;

import java.util.*;

//给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。 
//
// 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序
//列。 
// 
//
// 示例 1： 
//
// 
//输入：nums = [10,9,2,5,3,7,101,18]
//输出：4
//解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1,0,3,2,3]
//输出：4
// 
//
// 示例 3： 
//
// 
//输入：nums = [7,7,7,7,7,7,7]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2500 
// -10⁴ <= nums[i] <= 10⁴ 
// 
//
// 
//
// 进阶： 
//
// 
// 你可以设计时间复杂度为 O(n²) 的解决方案吗？ 
// 你能将算法的时间复杂度降低到 O(n log(n)) 吗? 
// 
// Related Topics 数组 二分查找 动态规划 👍 1992 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 61ms 61% beat
    private int byDp(int[] nums) {
        // dp[i] 表示 nums[0:i]的最长子序列长度
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            // 长度至少为1
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int max = 0;
        for (int l : dp) {
            if (l > max) {
                max = l;
            }
        }
        return max;
    }

    // 3ms 82.3% beat
    private int byPatientSort(int[] nums) {
        // 牌堆顶
        int[] top = new int[nums.length];
        // 堆数量
        int piles = 0;
        for (int i = 0; i < nums.length; i++) {
            int poker = nums[i];
            int left = 0;
            int right = piles;
            while (left < right) {
                int mid = (left + right) / 2;
                if (poker > top[mid]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            // 新建一个堆
            if (left == piles) {
                piles++;
            }
            top[left] = poker;
        }
        return piles;
    }

    public int lengthOfLIS(int[] nums) {
//        return byDp(nums);
        return byPatientSort(nums);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
