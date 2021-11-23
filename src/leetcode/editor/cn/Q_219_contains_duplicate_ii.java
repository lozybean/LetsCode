package leetcode.editor.cn.q_219;

import leetcode.editor.cn.def.*;

import java.util.*;

//给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的 绝对值
// 至多为 k。 
//
// 
//
// 示例 1: 
//
// 输入: nums = [1,2,3,1], k = 3
//输出: true 
//
// 示例 2: 
//
// 输入: nums = [1,0,1,1], k = 1
//输出: true 
//
// 示例 3: 
//
// 输入: nums = [1,2,3,1,2,3], k = 2
//输出: false 
// Related Topics 数组 哈希表 滑动窗口 👍 337 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // O(KN), 1556ms, 9.11% beat
    private boolean byLoop(int[] nums, int k) {
        if (nums.length < 2) {
            return false;
        }

        for (int i = 1; i <= k; i++) {
            int left = 0;
            int right = left + i;
            while (right < nums.length) {
                if (nums[left] == nums[right]) {
                    return true;
                }
                right++;
                left++;
            }
        }
        return false;
    }

    private boolean byHashMap(int[] nums, int k) {
        if (nums.length < 2) {
            return false;
        }
        Map<Integer, Integer> position = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // 默认值为-k-1，i>=0, 此时 i - (-k-1) 必然大于 k
            // 即第一次碰到某个数的时候，必然为false
            if (i - position.getOrDefault(nums[i], -k-1) <= k) {
                return true;
            }
            position.put(nums[i], i);
        }
        return false;
    }

    private boolean bySet(int[] nums, int k) {
        if(nums.length < 2) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        for(int i = 0;i<nums.length;i++){
            if(set.contains(nums[i])) return true;
            set.add(nums[i]);
            // 移除太前面的
            if(set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
//        return byLoop(nums, k);
//        return byHashMap(nums, k);
        return bySet(nums, k);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
