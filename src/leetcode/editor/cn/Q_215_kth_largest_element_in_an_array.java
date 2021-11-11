package leetcode.editor.cn.q_215;

import leetcode.editor.cn.def.*;

import java.util.*;

//给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。 
//
// 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。 
//
// 
//
// 示例 1: 
//
// 
//输入: [3,2,1,5,6,4] 和 k = 2
//输出: 5
// 
//
// 示例 2: 
//
// 
//输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
//输出: 4 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= nums.length <= 10⁴ 
// -10⁴ <= nums[i] <= 10⁴ 
// 
// Related Topics 数组 分治 快速选择 排序 堆（优先队列） 👍 1368 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解法1: 排序后取topK, 时间复杂度 O(nlogn)
    // 2ms 83.33% beat
    private int bySort(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // 解法2: 只对前k个排序，时间复杂度 O(nk)
    // 43ms, 6.32% beat
    private int byBubbleSortTopK(int[] nums, int k) {
        for (int i = 0; i < k; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] < nums[j]) {
                    swap(nums, i, j);
                }
            }
        }
        return nums[k - 1];
    }

    // 按照顺序构建大顶堆
    private void buildMaxHeap(int[] nums, int heapSize) {
        for (int i = heapSize / 2; i >= 0; i--) {
            adjustHeap(nums, i, heapSize);
        }
    }

    // 调整为大顶堆；完美二叉树，用数值表示
    private void adjustHeap(int[] nums, int i, int heapSize) {
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        int max = i;
        if (left < heapSize && nums[left] > nums[max]) {
            max = left;
        }
        if (right < heapSize && nums[right] > nums[max]) {
            max = right;
        }
        if (max != i) {
            swap(nums, max, i);
            adjustHeap(nums, max, heapSize);
        }
    }

    // 解法3: 堆排序选出前K个，只排序前K个，时间复杂度 O(nlogk)
    // 2ms 83.33% beat
    private int byHeapSortTopK(int[] nums, int k) {
        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize);
        // 排 k - 1 次
        for (int i = 1; i < k; i++) {
            // 排除第i大的数
            swap(nums, nums.length - i, 0);
            heapSize--;
            adjustHeap(nums, 0, heapSize);
        }
        return nums[0];
    }

    private int partition(int[] nums, int start, int end) {
        int x = nums[end];
        int i = start;
        for (int j = start; j < end; j++) {
            // 从大到小分区，比 > x 小的放左边，
            if (nums[j] > x) {
                swap(nums, i, j);
                i++;
            }
        }
        // <=x 的放右边
        // 因为找的是topk，需要往左侧找，相等的放右边可以减少一点点比较
        swap(nums, i, end);
        return i;
    }

    private Random random = new Random();

    // 增加随机过程，防止快速排序衰减
    private int randomPartition(int[] nums, int start, int end) {
        int i = random.nextInt(end - start + 1) + start;
        swap(nums, i, end);
        return partition(nums, start, end);
    }

    // 解法4: 快速选择，时间复杂度O(n) , 最差情况下，每次都分成1,n-1份时，为 O(n**2)
    // 未增加随机过程：15ms ,19.42% beat
    // 增加随机过程后：1ms 98.90% beat
    private int byQuickSelect(int[] nums, int start, int end, int k) {
        // 当前是第q大, q是下标，q+1是次序
        int q = randomPartition(nums, start, end);
        if (q + 1 == k) {
            return nums[q];
        } else if (q + 1 > k) {
            // 排名不够，还要往👈🏻找找
            return byQuickSelect(nums, start, q - 1, k);
        } else {
            // 排名太高了，往👉🏻找找
            return byQuickSelect(nums, q + 1, end, k);
        }
    }

    public int findKthLargest(int[] nums, int k) {
//        return bySort(nums, k);
//        return byBubbleSortTopK(nums, k);
//        return byHeapSortTopK(nums, k);
        return byQuickSelect(nums, 0, nums.length - 1, k);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
