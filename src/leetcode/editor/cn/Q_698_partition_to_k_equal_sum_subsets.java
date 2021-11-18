package leetcode.editor.cn.q_698;

import leetcode.editor.cn.def.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//给定一个整数数组 nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。 
//
// 示例 1： 
//
// 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
//输出： True
//说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= len(nums) <= 16 
// 0 < nums[i] < 10000 
// 
// Related Topics 位运算 记忆化搜索 数组 动态规划 回溯 状态压缩 👍 443 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // index 位置的数
    // 对所有的bucket穷举
    // 22ms 31.09% beat
    private boolean backtrack(int[] nums, int[] buckets, int target, int index) {
        if (index == nums.length) {
            for (int bucket : buckets) {
                if (bucket != target) {
                    return false;
                }
            }
            return true;
        }

        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] + nums[index] > target) {
                continue;
            }
            buckets[i] += nums[index];
            if (backtrack(nums, buckets, target, index + 1)) {
                return true;
            }
            buckets[i] -= nums[index];
        }
        return false;
    }

    // 第 k 个桶，桶当前值为 bucket
    // 2ms 50.96% beat
    private boolean backtrack2(int[] nums, int k, int target, int bucket, boolean[] used, int start) {
        if (k == 0) {
            return true;
        }
        if (bucket == target) {
            // 装下一个桶
            return backtrack2(nums, k - 1, target, 0, used, 0);
        }
        for (int i = start; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            if(nums[i] + bucket > target) {
                continue;
            }
            bucket += nums[i];
            used[i] = true;
            if (backtrack2(nums, k, target, bucket, used, i + 1)) return true;
            bucket -= nums[i];
            used[i] = false;
        }
        return false;
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (k > nums.length) return false;
        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0) return false;

        Arrays.sort(nums);
        for (int i = 0, j = nums.length - 1; i < j; i++, j--) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
//        int[] buckets = new int[k];
//        return backtrack(nums, buckets, sum / k, 0);

        boolean[] used = new boolean[nums.length];
        return backtrack2(nums, k, sum / k, 0, used, 0);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
