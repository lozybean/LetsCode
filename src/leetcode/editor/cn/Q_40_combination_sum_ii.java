package leetcode.editor.cn.q_40;

import leetcode.editor.cn.def.*;

import java.util.*;

//给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。 
//
// candidates 中的每个数字在每个组合中只能使用一次。 
//
// 注意：解集不能包含重复的组合。 
//
// 
//
// 示例 1: 
//
// 
//输入: candidates = [10,1,2,7,6,1,5], target = 8,
//输出:
//[
//[1,1,6],
//[1,2,5],
//[1,7],
//[2,6]
//] 
//
// 示例 2: 
//
// 
//输入: candidates = [2,5,2,1,2], target = 5,
//输出:
//[
//[1,2,2],
//[5]
//] 
//
// 
//
// 提示: 
//
// 
// 1 <= candidates.length <= 100 
// 1 <= candidates[i] <= 50 
// 1 <= target <= 30 
// 
// Related Topics 数组 回溯 👍 739 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> res = new LinkedList<>();
    boolean[] used;

    private void backtrack(int[] candidates, int target, List<Integer> track, int start) {
        if (target == 0) {
            res.add(new LinkedList<>(track));
        }
        if (target < 0) {
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > target) {
                continue;
            }
            if (i > 0 && candidates[i] == candidates[i - 1] && !used[i - 1]) {
                continue;
            }
            used[i] = true;
            track.add(candidates[i]);
            backtrack(candidates, target - candidates[i], track, i + 1);
            used[i] = false;
            track.remove(track.size() - 1);
        }
    }

    // 同 Q90 子集II
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<Integer> track = new LinkedList<>();
        used = new boolean[candidates.length];
        backtrack(candidates, target, track, 0);
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
