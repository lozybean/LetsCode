package leetcode.editor.cn.q_354;

import leetcode.editor.cn.def.*;

import java.util.*;

//<p>给你一个二维整数数组 <code>envelopes</code> ，其中 <code>envelopes[i] = [w<sub>i</sub>, h<sub>i</sub>]</code> ，表示第 <code>i</code> 个信封的宽度和高度。</p>
//
//<p>当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。</p>
//
//<p>请计算 <strong>最多能有多少个</strong> 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。</p>
//
//<p><strong>注意</strong>：不允许旋转信封。</p>
// 
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>envelopes = [[5,4],[6,4],[6,7],[2,3]]
//<strong>输出：</strong>3
//<strong>解释：</strong>最多信封的个数为 <code>3, 组合为: </code>[2,3] => [5,4] => [6,7]。</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>envelopes = [[1,1],[1,1],[1,1]]
//<strong>输出：</strong>1
//</pre>
//
//<p> </p>
//
//<p><strong>提示：</strong></p>
//
//<ul>
//	<li><code>1 <= envelopes.length <= 5000</code></li>
//	<li><code>envelopes[i].length == 2</code></li>
//	<li><code>1 <= w<sub>i</sub>, h<sub>i</sub> <= 10<sup>4</sup></code></li>
//</ul>
//<div><div>Related Topics</div><div><li>数组</li><li>二分查找</li><li>动态规划</li><li>排序</li></div></div><br><div><li>👍 607</li><li>👎 0</li></div>

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private int lisByPatientSort(int[] nums) {
        int[] top = new int[nums.length];
        int piles = 0;
        for (int poker : nums) {
            int left = 0;
            int right = piles;
            while (left < right) {
                int mid = (left + right) / 2;
                if (top[mid] < poker) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            if (left == piles) {
                piles++;
            }
            top[left] = poker;
        }
        return piles;
    }

    public int maxEnvelopes(int[][] envelopes) {
        // 排序，按照宽度从小到大排序
        // 在宽度排序的情况下，高度的最长递增子序列，就是最多可以套的数量
        // 由于宽度相同的情况下，不能套，所以宽度相同的时候，取高度最高的
        Arrays.sort(envelopes, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return Integer.compare(o2[1], o1[1]);
            } else {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        int[] height = new int[envelopes.length];
        for (int i = 0; i < envelopes.length; i++) {
            height[i] = envelopes[i][1];
        }
        return lisByPatientSort(height);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
