package leetcode.editor.cn.q_77;

import leetcode.editor.cn.def.*;

import java.util.*;

//给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。 
//
// 你可以按 任何顺序 返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 4, k = 2
//输出：
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
//
// 示例 2： 
//
// 
//输入：n = 1, k = 1
//输出：[[1]] 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 20 
// 1 <= k <= n 
// 
// Related Topics 数组 回溯 👍 770 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> res = new LinkedList<>();
    boolean[] used;

    // 组合问题，避免重复，需要增加start偏移
    private void backtrack(int n, int k, List<Integer> track, int start) {
        if (track.size() == k) {
            res.add(new LinkedList<>(track));
        }
        for (int i = start; i <= n; i++) {
            if (used[i] || track.size() > k) {
                continue;
            }
            used[i] = true;
            track.add(i);
            backtrack(n, k, track, i + 1);
            used[i] = false;
            track.remove(track.size() - 1);
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        List<Integer> track = new LinkedList<>();
        used = new boolean[n + 1];
        backtrack(n, k, track, 1);
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
