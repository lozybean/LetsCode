package leetcode.editor.cn.q_678;

import leetcode.editor.cn.def.*;

import java.util.*;

//给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则： 
//
// 
// 任何左括号 ( 必须有相应的右括号 )。 
// 任何右括号 ) 必须有相应的左括号 ( 。 
// 左括号 ( 必须在对应的右括号之前 )。 
// * 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。 
// 一个空字符串也被视为有效字符串。 
// 
//
// 示例 1: 
//
// 
//输入: "()"
//输出: True
// 
//
// 示例 2: 
//
// 
//输入: "(*)"
//输出: True
// 
//
// 示例 3: 
//
// 
//输入: "(*))"
//输出: True
// 
//
// 注意: 
//
// 
// 字符串大小将在 [1，100] 范围内。 
// 
// Related Topics 栈 贪心 字符串 动态规划 👍 415 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private boolean byStack(String s) {
        Deque<Integer> parenthesesStack = new LinkedList<>();
        Deque<Integer> starStack = new LinkedList<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') parenthesesStack.push(i);
            if (chars[i] == '*') starStack.push(i);
            if (chars[i] == ')') {
                if (!parenthesesStack.isEmpty()) {
                    parenthesesStack.pop();
                } else {
                    if (starStack.isEmpty()) return false;
                    starStack.pop();
                }
            }
        }
        while (!parenthesesStack.isEmpty()) {
            int pos = parenthesesStack.pop();
            // 在 ( 前面的 * 不能替换
            while (!starStack.isEmpty() && starStack.peek() < pos) {
                starStack.pop();
            }
            if (starStack.isEmpty()) return false;
            // 用 * 替换 )
            starStack.pop();
        }
        return true;
    }

    private boolean byCount(String s) {
        // 最小未匹配
        int min = 0;
        // 最大未匹配
        int max = 0;
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '(') {
                // 必有未匹配
                min++;
                max++;
            }
            if (c == ')') {
                // 匹配，减一
                min = Math.max(min - 1, 0);
                max--;
                // 没有更多 ( 用于匹配
                if (max < 0) return false;
            }
            if (c == '*') {
                // 使用 ) 匹配，未匹配减少1
                min = Math.max(min - 1, 0);
                // 用作 ( ，未匹配增加
                max++;
            }
        }
        return min == 0;
    }

    public boolean checkValidString(String s) {
//        return byStack(s);
        return byCount(s);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
