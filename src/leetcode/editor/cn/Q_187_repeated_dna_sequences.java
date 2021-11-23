package leetcode.editor.cn.q_187;

import java.util.*;

//所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，例如："ACGAATTCCG"。在研究 DNA 时，识别 DNA 中的重复
//序列有时会对研究非常有帮助。 
//
// 编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
//输出：["AAAAACCCCC","CCCCCAAAAA"]
// 
//
// 示例 2： 
//
// 
//输入：s = "AAAAAAAAAAAAA"
//输出：["AAAAAAAAAA"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 10⁵ 
// s[i] 为 'A'、'C'、'G' 或 'T' 
// 
// Related Topics 位运算 哈希表 字符串 滑动窗口 哈希函数 滚动哈希 👍 301 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private static final Integer LEN = 10;

    private List<String> byStringMap(String s) {
        List<String> res = new LinkedList<>();
        if (s.length() <= LEN) {
            return res;
        }
        Map<String, Integer> window = new HashMap<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i <= chars.length - LEN; i++) {
            // copyValueOf 的时间复杂度：10
            String word = String.valueOf(chars, i, LEN);
            window.put(word, window.getOrDefault(word, 0) + 1);
            if (window.get(word) == 2) {
                res.add(word);
            }
        }
        return res;
    }

    private List<String> byIntegerMap(String s) {
        List<String> res = new LinkedList<>();
        if (s.length() <= LEN) {
            return res;
        }
        Map<Character, Integer> bin = new HashMap<>() {{
            put('A', 0); //00
            put('T', 1); //01
            put('C', 2); //10
            put('G', 3); //11
        }};
        Map<Integer, Integer> window = new HashMap<>();
        char[] chars = s.toCharArray();
        int x = 0;
        for (int i = 0; i < LEN - 1; i++) {
            x = (x << 2) | bin.get(chars[i]);
        }
        for (int i = LEN - 1; i < chars.length; i++) {
            // 使用位运算
            // 1. 先让出两位，再加入新字符，仅保留低20位
            x = ((x << 2) | bin.get(chars[i])) & ((1 << (2 * LEN)) - 1);
            window.put(x, window.getOrDefault(x, 0) + 1);
            if (window.get(x) == 2) {
                res.add(String.valueOf(chars, i - LEN + 1, LEN));
            }
        }
        return res;
    }

    public List<String> findRepeatedDnaSequences(String s) {
//        return byStringMap(s);
        return byIntegerMap(s);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
