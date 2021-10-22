package leetcode.editor.cn.q_229;

import leetcode.editor.cn.def.*;

import java.util.*;

//给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：[3,2,3]
//输出：[3] 
//
// 示例 2： 
//
// 
//输入：nums = [1]
//输出：[1]
// 
//
// 示例 3： 
//
// 
//输入：[1,1,1,3,3,2,2,2]
//输出：[1,2] 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5 * 10⁴ 
// -10⁹ <= nums[i] <= 10⁹ 
// 
//
// 
//
// 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1)的算法解决此问题。 
// Related Topics 数组 哈希表 计数 排序 👍 493 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 11ms 28.12% beat
    // 41.9MB 68.96% beat
    private List<Integer> byCount(int[] nums) {
        int n = nums.length;
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            int c = count.getOrDefault(num, 0) + 1;
            if (n / 3 < c && c < n / 3 + 2) {
                result.add(num);
            }
            count.put(num, c);
        }
        return result;
    }

    private List<Integer> byVote(int[] nums) {
        // 超过 [n/3] 最多两个
        int ele1 = 0;
        int vote1 = 0;
        int ele2 = 0;
        int vote2 = 0;

        for (int num : nums) {
            if (vote1 > 0 && ele1 == num) {
                vote1++;
            } else if (vote2 > 0 && ele2 == num) {
                vote2++;
            } else if (vote1 == 0) {
                ele1 = num;
                vote1++;
            } else if (vote2 == 0) {
                ele2 = num;
                vote2++;
            } else {
                vote1--;
                vote2--;
            }

        }

        int count1 = 0;
        int count2 = 0;
        int n = nums.length;
        for (int num : nums) {
            if (num == ele1 && vote1 > 0) {
                count1++;
            }
            if (num == ele2 && vote2 > 0) {
                count2++;
            }
        }
        List<Integer> res = new ArrayList<>();
        if (count1 > n / 3) {
            res.add(ele1);
        }
        if (count2 > n / 3) {
            res.add(ele2);
        }
        return res;
    }

    public List<Integer> majorityElement(int[] nums) {
//        return byCount(nums);
        return byVote(nums);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
