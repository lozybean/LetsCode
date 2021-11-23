package leetcode.editor.cn.q_30;

import leetcode.editor.cn.def.*;

import java.util.*;

//给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。 
//
// 注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "barfoothefoobarman", words = ["foo","bar"]
//输出：[0,9]
//解释：
//从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
//输出的顺序不重要, [9,0] 也是有效答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
//输出：[6,9,12]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10⁴ 
// s 由小写英文字母组成 
// 1 <= words.length <= 5000 
// 1 <= words[i].length <= 30 
// words[i] 由小写英文字母组成 
// 
// Related Topics 哈希表 字符串 滑动窗口 👍 569 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        int wordLen = words[0].length();
        int expectedLen = wordLen * words.length;
        List<Integer> res = new LinkedList<>();
        if (s.length() < expectedLen) {
            return res;
        }

        Map<String, Integer> window = new HashMap<>();
        Map<String, Integer> need = new HashMap<>();
        for (String word : words) {
            need.put(word, need.getOrDefault(word, 0) + 1);
        }


        char[] chars = s.toCharArray();
        for (int i = 0; i < wordLen; i++) {
//            System.out.printf("起始点偏移为：%d%n", i);
            int left = i;
            int right = i;
            int valid = 0;
            window.clear();
            while (right <= chars.length - wordLen) {
                String word = String.valueOf(chars, right, wordLen);
                right += wordLen;
                if (need.containsKey(word)) {
                    window.put(word, window.getOrDefault(word, 0) + 1);
                    if (Objects.equals(window.get(word), need.get(word))) {
                        valid++;
                    }
//                    right += wordLen;
                } else {
//                    right++;
                    // 中间不能间隔别的，一旦匹配失败，则需要更新左指针
                    left = right;
                    window.clear();
                    valid = 0;
                }

                // 右指针满足条件，缩小窗口找左指针
                while (valid == need.size()) {
//                System.out.printf("条件达成：right: %d %n", right);
                    word = String.valueOf(chars, left, wordLen);
                    if (Objects.equals(window.get(word), need.get(word))) {
//                    System.out.printf("缩小到边缘：%d, 长度为: %d, %n", left, right - left);
                        // 检查是否满足条件：
                        if (right - left == expectedLen) {
                            // 记录结果，清空窗口
                            res.add(left);
                        }
                        valid--;
                    }

                    // 否则缩小窗口
                    window.put(word, window.get(word) - 1);
                    left += wordLen;
//                System.out.printf("缩小窗口：left: %d, right: %d, valid: %d %n", left, right, valid);
                }
            }
        }

//        System.out.println("结果：" + res);
        return res;
    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
////        solution.findSubstring("lingmindraboofooowingdingbarrwingmonkeypoundcake", new String[]{
////                "fooo", "barr", "wing", "ding", "wing"
////        });
//        solution.findSubstring("aaaaaaaaaaaaaa", new String[]{
//                "aa", "aa"
//        });
//    }
}
//leetcode submit region end(Prohibit modification and deletion)
