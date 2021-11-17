package leetcode.editor.cn.q_56;

import leetcode.editor.cn.def.*;

import java.util.*;

//以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返
//回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。 
//
// 
//
// 示例 1： 
//
// 
//输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出：[[1,6],[8,10],[15,18]]
//解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 
//
// 示例 2： 
//
// 
//输入：intervals = [[1,4],[4,5]]
//输出：[[1,5]]
//解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。 
//
// 
//
// 提示： 
//
// 
// 1 <= intervals.length <= 10⁴ 
// intervals[i].length == 2 
// 0 <= starti <= endi <= 10⁴ 
// 
// Related Topics 数组 排序 👍 1177 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 1) {
            return new int[][]{new int[]{intervals[0][0], intervals[0][1]}};
        }
        int[][] res = new int[intervals.length][2];
        int k = 0;
        Arrays.sort(intervals, Comparator.comparingInt(i0 -> i0[0]));
        int right = 0;
        int start;
        int end;
        while (right < intervals.length) {
            start = intervals[right][0];
            end = intervals[right][1];
            while (right < intervals.length - 1 && intervals[right + 1][0] <= end) {
                right++;
                start = Math.min(start, intervals[right][0]);
                end = Math.max(end, intervals[right][1]);

            }
            if (right == intervals.length - 1 && intervals[right][0] <= end) {
                // 如果是因为到最后一个元素退出，则需要额外判断
                start = Math.min(start, intervals[right][0]);
                end = Math.max(end, intervals[right][1]);
                res[k++] = new int[]{start, end};
                break;
            }

            res[k++] = new int[]{start, end};
            right++;
        }

        return Arrays.copyOf(res, k);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
