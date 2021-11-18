package leetcode.editor.cn.q_22;

import leetcode.editor.cn.def.*;

import java.util.*;

//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 有效括号组合需满足：左括号必须以正确的顺序闭合。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：["()"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 8 
// 
// Related Topics 字符串 动态规划 回溯 👍 2154 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    List<String> res = new LinkedList<>();

    private void backtrack(int left, int right, char[] track, int i) {
        // ( 可用数量大于 ) => ( 较少，不合法
        if (left > right) return;

        if (left < 0 || right < 0) return;
        if (left == 0 && right == 0) {
            res.add(String.valueOf(track));
            return;
        }
        track[i] = '(';
        backtrack(left - 1, right, track, i + 1);
        track[i] = ')';
        backtrack(left, right - 1, track, i + 1);
    }

    public List<String> generateParenthesis(int n) {
        char[] track = new char[n * 2];
        backtrack(n, n, track, 0);
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
