package leetcode.editor.cn.q_167;

import leetcode.editor.cn.def.*;

import java.util.*;

//给定一个已按照 非递减顺序排列 的整数数组 numbers ，请你从数组中找出两个数满足相加之和等于目标数 target 。 
//
// 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 1 开始计数 ，所以答案数组应当满足 1 <= answer[0]
// < answer[1] <= numbers.length 。 
//
// 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。 
// 
//
// 示例 1： 
//
// 
//输入：numbers = [2,7,11,15], target = 9
//输出：[1,2]
//解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
// 
//
// 示例 2： 
//
// 
//输入：numbers = [2,3,4], target = 6
//输出：[1,3]
// 
//
// 示例 3： 
//
// 
//输入：numbers = [-1,0], target = -1
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 2 <= numbers.length <= 3 * 10⁴ 
// -1000 <= numbers[i] <= 1000 
// numbers 按 非递减顺序 排列 
// -1000 <= target <= 1000 
// 仅存在一个有效答案 
// 
// Related Topics 数组 双指针 二分查找 👍 614 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 二分查找版本，时间复杂度 O(nlogn)
    // 3ms 26.28% beat
    private int[] byBinarySort(int[] numbers, int target) {
        // 先固定第一个数
        for (int i = 0; i < numbers.length; i++) {
            // 适当剪枝
            if (numbers[i] + numbers[numbers.length - 1] < target) {
                continue;
            }
            int left = i + 1;
            int right = numbers.length - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (numbers[mid] + numbers[i] > target) {
                    // 太大了，往👈🏻小的找
                    right = mid - 1;
                } else if (numbers[mid] + numbers[i] < target) {
                    // 太小了，往👉🏻大的找
                    left = mid + 1;
                } else {
                    return new int[]{i + 1, mid + 1};
                }
            }
        }
        return new int[]{};
    }

    // 非递减序列，已经有排序，双指针，O(n)
    // 0ms 100% beat
    private int[] byDoublePtr(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            // 去重
            while (left > 0 && left < right && numbers[left - 1] == numbers[left]) left++;
            while (right < numbers.length - 1 && left < right && numbers[right + 1] == numbers[right]) right--;
            if (numbers[left] + numbers[right] > target) {
                right--;
            } else if (numbers[left] + numbers[right] < target) {
                left++;
            } else {
                // 下标从1开始
                return new int[]{left + 1, right + 1};
            }
        }

        return new int[]{};
    }

    public int[] twoSum(int[] numbers, int target) {
//        return byDoublePtr(numbers, target);
        return byBinarySort(numbers, target);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
