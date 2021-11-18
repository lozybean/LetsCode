package leetcode.editor.cn.q_20;

import leetcode.editor.cn.def.*;

import java.util.*;

//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。 
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "()"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：s = "()[]{}"
//输出：true
// 
//
// 示例 3： 
//
// 
//输入：s = "(]"
//输出：false
// 
//
// 示例 4： 
//
// 
//输入：s = "([)]"
//输出：false
// 
//
// 示例 5： 
//
// 
//输入：s = "{[]}"
//输出：true 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10⁴ 
// s 仅由括号 '()[]{}' 组成 
// 
// Related Topics 栈 字符串 👍 2770 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public boolean isValid(String s) {
        Deque<Integer> stack = new LinkedList<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '(') {
                stack.push(1);
            }
            if (c == ')') {
                if (stack.isEmpty() || stack.peek() != 1) return false;
                stack.pop();
            }
            if (c == '[') {
                stack.push(2);
            }
            if (c == ']') {
                if (stack.isEmpty() || stack.peek() != 2) return false;
                stack.pop();
            }
            if (c == '{') {
                stack.push(3);
            }
            if (c == '}') {
                if(stack.isEmpty() || stack.peek() != 3) return false   ;
                stack.pop();
            }

        }
        return stack.isEmpty();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
