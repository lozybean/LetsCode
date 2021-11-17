package leetcode.editor.cn.q_46;

import leetcode.editor.cn.def.*;

import java.util.*;

//给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1]
//输出：[[0,1],[1,0]]
// 
//
// 示例 3： 
//
// 
//输入：nums = [1]
//输出：[[1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 6 
// -10 <= nums[i] <= 10 
// nums 中的所有整数 互不相同 
// 
// Related Topics 数组 回溯 👍 1632 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private List<List<Integer>> res = new LinkedList<>();

    private void backtrackSimple(int[] nums, List<Integer> stack) {
        if (nums.length == stack.size()) {
            res.add(new LinkedList<>(stack));
        }
        for (int i = 0; i < nums.length; i++) {
            if (stack.contains(nums[i])) {
                continue;
            }
            stack.add(nums[i]);
            backtrackSimple(nums, stack);
            stack.remove(stack.size() - 1);
        }
    }

    private void backtrack(List<Integer> stack, int first, int n) {
        if (first == n) {
            res.add(new LinkedList<>(stack));
            return;
        }
        for (int i = first; i < n; i++) {
            Collections.swap(stack, first, i);
            backtrack(stack, first + 1, n);
            Collections.swap(stack, first, i);
        }
    }

    // 增加used状态：意味着不能重复取同一个数
    boolean[] used;
    private void backtrack2(int[] nums, List<Integer> stack) {
        if (stack.size() == nums.length) {
            res.add(new LinkedList<>(stack));
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            stack.add(nums[i]);
            backtrack2(nums, stack);
            used[i] = false;
            stack.remove(stack.size() - 1);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<Integer> stack = new LinkedList<>();
//        for (int num : nums) {
//            stack.add(num);
//        }
        used = new boolean[nums.length];
        backtrack2(nums, stack);
//        backtrack(stack, 0, nums.length);
        return res;


    }
}
//leetcode submit region end(Prohibit modification and deletion)
