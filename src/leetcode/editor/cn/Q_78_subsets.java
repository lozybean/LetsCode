package leetcode.editor.cn.q_78;

import leetcode.editor.cn.def.*;

import java.util.*;

//给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。 
//
// 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0]
//输出：[[],[0]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10 
// -10 <= nums[i] <= 10 
// nums 中的所有元素 互不相同 
// 
// Related Topics 位运算 数组 回溯 👍 1388 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> res = new LinkedList<>();

    // 按照该回溯的结果
    // [[],[1],[2],[3],[1,2],[1,3],[2,3],[1,2,3]]
    private void backtrack(int[] nums, List<Integer> track, int len) {
        if (track.size() == len) {
            res.add(new LinkedList<>(track));
        }
        for (int i = 0; i < nums.length; i++) {
            if (!track.isEmpty() && track.get(track.size() - 1) >= nums[i]) {
                continue;
            }
            track.add(nums[i]);
            backtrack(nums, track, len);
            track.remove(track.size() - 1);
        }
    }

    // 按照开始数字排序：
    // [[],[1],[1,2],[1,2,3],[1,3],[2],[2,3],[3]]
    private void backtrack2(int[] nums, List<Integer> track, int start) {
        res.add(new LinkedList<>(track));
        for (int i = start; i < nums.length; i++) {
            track.add(nums[i]);
            backtrack2(nums, track, i + 1);
            track.remove(track.size() - 1);
        }
    }

    private List<List<Integer>> byMask(int[] nums) {
        List<List<Integer>> ans  = new LinkedList<>();
        List<Integer> t = new LinkedList<>();
        int n = nums.length;
        // mask < 2 * n
        for (int mask = 0; mask < (1 << n); ++mask) {
            t.clear();
            for (int i = 0; i < n; ++i) {
                // 2 * i 表示第 i 个位置
                if ((mask & (1 << i)) != 0) {
                    t.add(nums[i]);
                }
            }
            ans.add(new ArrayList<>(t));
        }
        return ans;
    }

    public List<List<Integer>> subsets(int[] nums) {
//        List<Integer> track = new LinkedList<>();
////        for (int len = 0; len <= nums.length; len++) {
////            backtrack(nums, track, len);
////        }
//        backtrack2(nums, track, 0);
//        return res;
        return byMask(nums);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
