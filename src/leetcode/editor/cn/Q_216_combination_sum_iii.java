package leetcode.editor.cn.q_216;

import leetcode.editor.cn.def.*;

import java.util.*;

//找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。 
//
// 说明： 
//
// 
// 所有数字都是正整数。 
// 解集不能包含重复的组合。 
// 
//
// 示例 1: 
//
// 输入: k = 3, n = 7
//输出: [[1,2,4]]
// 
//
// 示例 2: 
//
// 输入: k = 3, n = 9
//输出: [[1,2,6], [1,3,5], [2,3,4]]
// 
// Related Topics 数组 回溯 👍 387 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> res = new LinkedList<>();
    boolean[] used = new boolean[10];

    // 组合问题，增加start偏移量，防止重复
    private void backtrack(int k, int n, List<Integer> track, int start) {
        if (track.size() == k && n == 0) {
            res.add(new LinkedList<>(track));
        }
        if (n < 0) {
            return;
        }
        for (int i = start; i <= 9; i++) {
            if (i > n || used[i]) {
                continue;
            }
            used[i] = true;
            track.add(i);
            backtrack(k, n - i, track, i + 1);
            used[i] = false;
            track.remove(track.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<Integer> track = new LinkedList<>();
        backtrack(k, n, track, 1);
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
