package leetcode.editor.cn.q_15;

import leetcode.editor.cn.def.*;

import java.util.*;

//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重
//复的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 3000 
// -10⁵ <= nums[i] <= 10⁵ 
// 
// Related Topics 数组 双指针 排序 👍 3908 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();

        if (len < 3) {
            return res;
        }
        Arrays.sort(nums);

        for (int i = 0; i <= len - 3; i++) {
            // 跳过重复
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            if (nums[i] + nums[i + 1] + nums[i + 2] > 0) break;
            if (nums[i] + nums[len - 1] + nums[len - 2] < 0) continue;

            // 双指针，分别从最小和最大开始
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 找到下一个不重复的
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                } else if (sum < 0) {
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    left++;
                } else {
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    right--;
                }
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
