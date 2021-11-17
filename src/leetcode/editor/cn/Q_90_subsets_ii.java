package leetcode.editor.cn.q_90;

import leetcode.editor.cn.def.*;

import java.util.*;

//给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。 
//
// 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。 
//
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,2]
//输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
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
// 
// 
// 
// Related Topics 位运算 数组 回溯 👍 690 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> res = new LinkedList<>();
    boolean[] used;

    // 相比Q78(子集) ，增加一个已使用的状态
    // [[],[1],[2],[1,2],[2,2],[1,2,2]]
    private void backtrack(int[] nums, List<Integer> track, int len) {
        if (track.size() == len) {
            res.add(new LinkedList<>(track));
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            if (!track.isEmpty() && track.get(track.size() - 1) > nums[i]) {
                continue;
            }
            // 去重
            // 若前一个数和当前相同，且前一个数未被使用，则当前选择必然在前一个数被使用的情况下包含，可以跳过
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            used[i] = true;
            track.add(nums[i]);
            backtrack(nums, track, len);
            used[i] = false;
            track.remove(track.size() - 1);
        }
    }

    // [[],[1],[1,2],[1,2,2],[2],[2,2]]
    private void backtrack2(int[] nums, List<Integer> track, int start) {
        res.add(new LinkedList<>(track));
        for (int i = start; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            used[i] = true;
            track.add(nums[i]);
            backtrack2(nums, track, i + 1);
            used[i] = false;
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
            boolean flag=true;
            for (int i = 0; i < n; ++i) {
                // 2 * i 表示第 i 个位置
                if ((mask & (1 << i)) != 0) {
                    if(i > 0 && nums[i-1] == nums[i] && ((mask & (1 << (i-1))) == 0 )) {
                        flag=false;
                        break;
                    }
                    t.add(nums[i]);
                }
            }
            if(flag){
                ans.add(new ArrayList<>(t));
            }

        }
        return ans;
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
//        List<Integer> track = new LinkedList<>();
//        used = new boolean[nums.length];
////        for (int len = 0; len <= nums.length; len++) {
////            backtrack(nums, track, len);
////        }
//        backtrack2(nums, track, 0);
//        return res;
        return byMask(nums);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
