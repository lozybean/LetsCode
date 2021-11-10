package leetcode.editor.cn.q_76;

import leetcode.editor.cn.def.*;

import java.util.*;

//给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。 
//
// 
//
// 注意： 
//
// 
// 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。 
// 如果 s 中存在这样的子串，我们保证它是唯一的答案。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "ADOBECODEBANC", t = "ABC"
//输出："BANC"
// 
//
// 示例 2： 
//
// 
//输入：s = "a", t = "a"
//输出："a"
// 
//
// 示例 3: 
//
// 
//输入: s = "a", t = "aa"
//输出: ""
//解释: t 中两个字符 'a' 均应包含在 s 的子串中，
//因此没有符合条件的子字符串，返回空字符串。 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length, t.length <= 10⁵ 
// s 和 t 由英文字母组成 
// 
//
// 
//进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？ Related Topics 哈希表 字符串 滑动窗口 👍 1419 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String minWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (char tc : t.toCharArray()) {
            need.put(tc, need.getOrDefault(tc, 0) + 1);
        }
        // 有效的覆盖字母数
        int valid = 0;
        // [left, right) 为最小子串
        int left = 0;
        int right = 0;
        char[] sChars = s.toCharArray();

        // 记录最小覆盖子串
        boolean find = false;
        int start = 0;
        int end = s.length();
        while (right < s.length()) {
            // 扩大窗口
            char c = sChars[right];
            // right++ 放在缩小窗口之前，表示左闭右开 [left, right)
            right++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (Objects.equals(window.get(c), need.get(c))) {
                    valid++;
                }
            }

            // 满足条件，尝试缩小窗口
            while (valid == need.size()) {
                find = true;
                if (right - left < end - start) {
                    end = right;
                    start = left;
                }
                c = sChars[left];
                left++;
                if (need.containsKey(c)) {
                    if (Objects.equals(need.get(c), window.get(c))) {
                        valid--;
                    }
                    window.put(c, window.get(c) - 1);
                }
            }
        }
        if (find) {
            return s.substring(start, end);
        } else {
            return "";
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)
