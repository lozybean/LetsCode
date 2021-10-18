package leetcode.editor.cn.q_4;

import leetcode.editor.cn.def.*;

import java.util.*;

//给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
// 
//
// 示例 3： 
//
// 
//输入：nums1 = [0,0], nums2 = [0,0]
//输出：0.00000
// 
//
// 示例 4： 
//
// 
//输入：nums1 = [], nums2 = [1]
//输出：1.00000
// 
//
// 示例 5： 
//
// 
//输入：nums1 = [2], nums2 = []
//输出：2.00000
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m 
// nums2.length == n 
// 0 <= m <= 1000 
// 0 <= n <= 1000 
// 1 <= m + n <= 2000 
// -10⁶ <= nums1[i], nums2[i] <= 10⁶ 
// 
//
// 
//
// 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？ 
// Related Topics 数组 二分查找 分治 👍 4571 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private int[] mergeArray(int[] nums1, int[] nums2) {
        if (nums1.length == 0) {
            return nums2;
        }
        if (nums2.length == 0) {
            return nums1;
        }
        int i = 0;
        int j = 0;
        int len = nums1.length + nums2.length;
        int[] res = new int[len];
        int k = 0;
        while (k < len && i < nums1.length && j < nums2.length) {
            if (nums1[i] <= nums2[j]) {
                res[k] = nums1[i];
                i++;
            } else {
                res[k] = nums2[j];
                j++;
            }
            k++;
        }
        // 取完第一个
        if (i >= nums1.length) {
            while (k < len) {
                res[k] = nums2[j];
                j++;
                k++;
            }
        }
        // 取完第二个
        if (j >= nums2.length) {
            while (k < len) {
                res[k] = nums1[i];
                i++;
                k++;
            }
        }
        return res;
    }

    // 时间O(m+n)
    // 空间O(m+n)
    private double byMerge(int[] nums1, int[] nums2) {
        int[] arrayMerged = mergeArray(nums1, nums2);
        if ((arrayMerged.length & 1) == 1) {
            return arrayMerged[arrayMerged.length / 2];
        } else {
            return (arrayMerged[arrayMerged.length / 2 - 1] +
                    arrayMerged[arrayMerged.length / 2]) / 2d;
        }
    }

    // 时间O(m+n) => (m+n) / 2
    // 空间O(1)
    private double bySearch(int[] nums1, int[] nums2) {
        int i = 0;
        int j = 0;
        int n1 = -1;
        int n2 = -1;
        int len = nums1.length + nums2.length;
        int k = 0;
        while (k <= len / 2 && i < nums1.length && j < nums2.length) {
            n2 = n1;
            if (nums1[i] <= nums2[j]) {
                n1 = nums1[i];
                i++;
            } else {
                n1 = nums2[j];
                j++;
            }
            k++;
        }
        // 取完第一个数组
        if (i >= nums1.length) {
            while (k <= len / 2) {
                n2 = n1;
                n1 = nums2[j];
                j++;
                k++;
            }
        }
        // 取完第二个数组
        if (j >= nums2.length) {
            while (k <= len / 2) {
                n2 = n1;
                n1 = nums1[i];
                i++;
                k++;
            }
        }
        if ((len & 1) == 1) {
            return n1;
        } else {
            return (n1 + n2) / 2d;
        }
    }

    private int findKth(int[] nums1, int s1, int[] nums2, int s2, int k) {
        int len1 = nums1.length - s1;
        int len2 = nums2.length - s2;
        // 不失一般性，令 len1 <= len2
        if (len1 > len2) return findKth(nums2, s2, nums1, s1, k);
        if (len1 == 0) {
            return nums2[s2 + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[s1], nums2[s2]);
        }
        int i = s1 + Math.min(len1, k / 2) - 1;
        int j = s2 + Math.min(len2, k / 2) - 1;
        if (nums1[i] < nums2[j]) {
            // 排除掉nums1的 [s1..i] 元素
            return findKth(nums1, i + 1, nums2, s2, k - (i + 1 - s1));
        } else {
            return findKth(nums1, s1, nums2, j + 1, k - (j + 1 - s2));
        }
    }

    // O(log(n+m))
    private double byFindKth(int[] nums1, int[] nums2) {
        int n = nums1.length + nums2.length;
        if ((n & 1) == 0) {
            return (findKth(nums1, 0, nums2, 0, (n + 1) / 2) +
                    findKth(nums1, 0, nums2, 0, (n + 2) / 2)) / 2d;
        } else {
            return findKth(nums1, 0, nums2, 0, n / 2 + 1);
        }
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//        return byMerge(nums1, nums2);
//        return bySearch(nums1, nums2);
        return byFindKth(nums1, nums2);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
