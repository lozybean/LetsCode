package leetcode.editor.cn.q_556;

import leetcode.editor.cn.def.*;

import java.util.*;

//给你一个正整数 n ，请你找出符合条件的最小整数，其由重新排列 n 中存在的每位数字组成，并且其值大于 n 。如果不存在这样的正整数，则返回 -1 。 
//
// 注意 ，返回的整数应当是一个 32 位整数 ，如果存在满足题意的答案，但不是 32 位整数 ，同样返回 -1 。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 12
//输出：21
// 
//
// 示例 2： 
//
// 
//输入：n = 21
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 2³¹ - 1 
// 
// Related Topics 数学 双指针 字符串 👍 168 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int nextGreaterElement(int n) {
        if (n < 10) {
            return -1;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        long k = 1;
        while (n != 0) {
            int i = n % 10;
            n = n / 10;
            if (!stack.isEmpty() && stack.peek() > i) {
                // 单调栈，找到第一对升序数字
                // n复原，i 进栈底，中间留一位给栈中最小且比i大的数字
                long nn = n * k * 10;
                long kk = k / 10;
                boolean find = false;
                boolean used = false;
                while (!stack.isEmpty()) {
                    int j = stack.removeLast();
                    if (i >= j) {
                        nn += j * kk;
                        kk /= 10;
                    } else {
                        if (!find) {
                            // 第一个大于i的数
                            nn += j * k;
                            find = true;
                            if (stack.isEmpty()) {
                                nn += i * kk;
                            }
                        } else if (!used) {
                            nn += i * kk;
                            kk /= 10;
                            used = true;
                            nn += j * kk;
                            kk /= 10;
                        } else {
                            nn += j * kk;
                            kk /= 10;
                        }
                    }
                }
                if (nn > Integer.MAX_VALUE) {
                    return -1;
                } else {
                    return (int) nn;
                }
            }
            k *= 10;
            stack.push(i);
        }
        return -1;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
