package leetcode.editor.cn.q_5;

import leetcode.editor.cn.def.*;

import java.util.*;

//给你一个字符串 s，找到 s 中最长的回文子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 示例 3： 
//
// 
//输入：s = "a"
//输出："a"
// 
//
// 示例 4： 
//
// 
//输入：s = "ac"
//输出："a"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母（大写和/或小写）组成 
// 
// Related Topics 字符串 动态规划 👍 4202 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return right - left - 1;
    }

    private String byExpandAroundCenter(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        if (s.length() == 1) {
            return s;
        }
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            // 以单个字母为中心扩展
            int len1 = expandAroundCenter(s, i, i);
            // 以两个字母为中心扩展
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > (end - start)) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private String byDp(String s) {
        int len = s.length();
        if (len == 1) {
            return s;
        }
        //bp[i][j] 表示 s[i .. j] 为回文
        boolean[][] dp = new boolean[len][len];
        // 所有长度为1的
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        char[] chars = s.toCharArray();

        int maxLen = 1;
        int begin = 0;
        for (int L = 2; L <= len; L++) {
            for (int i = 0; i < len; i++) {
                int j = L + i - 1;
                if (j >= len) {
                    break;
                }
                if (chars[i] != chars[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i == 1) {
                        // 相邻
                        dp[i][j] = true;
                    } else {
                        // 否则肯定由一个更小的回文扩展而来
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                if (dp[i][j] && (j - i + 1) > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }

            }
        }


        return s.substring(begin, maxLen + begin);
    }

    public String longestPalindrome(String s) {
//        return byDp(s);
        return byExpandAroundCenter(s);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
