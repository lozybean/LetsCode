package leetcode.editor.cn.q_3;

import leetcode.editor.cn.def.*;

import java.util.*;

//给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
//
// 示例 4: 
//
// 
//输入: s = ""
//输出: 0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 5 * 10⁴ 
// s 由英文字母、数字、符号和空格组成 
// 
// Related Topics 哈希表 字符串 滑动窗口 👍 6390 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 7ms, 41.34% beat
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> window = new HashMap<>();
        char[] sChar = s.toCharArray();
        int left = 0;
        int right = 0;
        int len = 0;
        while (right < s.length()) {
            char c = sChar[right];
            right++;
            window.put(c, window.getOrDefault(c, 0) + 1);

            // 如果有重复，则缩小窗口
            while (window.get(c) > 1) {
                char d = sChar[left];
                left++;
                window.put(d, window.get(d) - 1);
            }

            if (len < right - left) {
                len = right - left;
            }
        }
        return len;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
