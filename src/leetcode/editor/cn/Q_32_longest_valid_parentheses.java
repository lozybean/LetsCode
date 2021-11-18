package leetcode.editor.cn.q_32;

import leetcode.editor.cn.def.*;

import java.util.*;

//给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：s = "(()"
//输出：2
//解释：最长有效括号子串是 "()"
// 
//
// 示例 2： 
//
// 
//输入：s = ")()())"
//输出：4
//解释：最长有效括号子串是 "()()"
// 
//
// 示例 3： 
//
// 
//输入：s = ""
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 3 * 10⁴ 
// s[i] 为 '(' 或 ')' 
// 
// 
// 
// Related Topics 栈 字符串 动态规划 👍 1530 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private int byStack(String s) {
        char[] chars = s.toCharArray();
        Deque<Integer> stack = new LinkedList<>();
        // 栈顶表示最后一个未被匹配的右括号
        stack.push(-1);
        int max = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                stack.push(i);
            }
            if (chars[i] == ')') {
                stack.pop();
                if (stack.isEmpty()) {
                    // 未匹配的右括号
                    stack.push(i);
                } else {
                    int len = i - stack.peek();
                    if (max < len) {
                        max = len;
                    }
                }
            }
        }
        return max;
    }

    private int byCount(String s) {
        int left = 0;
        int right = 0;
        int max = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') left++;
            if (chars[i] == ')') right++;
            if (right > left) {
                left = 0;
                right = 0;
            }
            if (left == right) {
                max = Math.max(max, left + right);
            }
        }
        left = 0;
        right = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == '(') left++;
            if (chars[i] == ')') right++;
            if (right < left) {
                right = 0;
                left = 0;
            }
            if (left == right) {
                max = Math.max(max, left + right);
            }
        }
        return max;
    }

    public int longestValidParentheses(String s) {
//        return byStack(s);
        return byCount(s);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
