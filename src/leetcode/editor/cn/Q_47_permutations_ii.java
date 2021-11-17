package leetcode.editor.cn.q_47;

import leetcode.editor.cn.def.*;

import java.util.*;

//给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,2]
//输出：
//[[1,1,2],
// [1,2,1],
// [2,1,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 8 
// -10 <= nums[i] <= 10 
// 
// Related Topics 数组 回溯 👍 863 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private List<List<Integer>> res = new LinkedList<>();
    // 是否有被选择
    private boolean[] vis;

    private void backtrack(int[] nums, List<Integer> track) {
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
        }
        for (int i = 0; i < nums.length; i++) {
            // 全排列时，当vis[i-1]使用过，意味着再选一个相同的数，得到的结果仍然相同
            // 区别子集，子集中，若有重复元素，则条件为前一个相同的元素没有被选择的情况下过滤
            if (vis[i] || (i > 0 && vis[i - 1] && nums[i] == nums[i - 1])) {
                continue;
            }
            vis[i] = true;
            track.add(nums[i]);
            backtrack(nums, track);
            vis[i] = false;
            track.remove(track.size() - 1);
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        vis = new boolean[nums.length];
        List<Integer> track = new LinkedList<>();
        Arrays.sort(nums);
        backtrack(nums, track);
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
