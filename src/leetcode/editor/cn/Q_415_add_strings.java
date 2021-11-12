package leetcode.editor.cn.q_415;

import leetcode.editor.cn.def.*;

import java.util.*;

//给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
//
// 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。 
//
// 
//
// 示例 1： 
//
// 
//输入：num1 = "11", num2 = "123"
//输出："134"
// 
//
// 示例 2： 
//
// 
//输入：num1 = "456", num2 = "77"
//输出："533"
// 
//
// 示例 3： 
//
// 
//输入：num1 = "0", num2 = "0"
//输出："0"
// 
//
// 
//
// 
//
// 提示： 
//
// 
// 1 <= num1.length, num2.length <= 10⁴ 
// num1 和num2 都只包含数字 0-9 
// num1 和num2 都不包含任何前导零 
// 
// Related Topics 数学 字符串 模拟 👍 466 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String addStrings(String num1, String num2) {
        // 不失一般性，令 num1 的长度大于等于 num2
        if (num1.length() < num2.length()) {
            return addStrings(num2, num1);
        }
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        char[] res = new char[chars1.length + 1];
        int carry = 0;
        for (int i = 0; i < chars1.length; i++) {
            int c1 = chars1[chars1.length - i - 1] - '0';
            int pos = res.length - i - 1;
            if (i < chars2.length) {
                int c2 = chars2[chars2.length - i - 1] - '0';
                int n = c1 + c2 + carry;
                if (n >= 10) {
                    carry = 1;
                    res[pos] = (char) (n - 10 + '0');
                } else {
                    res[pos] = (char) (n + '0');
                    carry = 0;
                }
            } else {
                int n = c1 + carry;
                if (n >= 10) {
                    carry = 1;
                    res[pos] = (char) (n - 10 + '0');
                } else {
                    res[pos] = (char) (n + '0');
                    carry = 0;
                }
            }
        }
        if (carry == 1) {
            res[0] = (char) (1 + '0');
            return String.valueOf(res);
        } else {
            return String.valueOf(res, 1, chars1.length);
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)
