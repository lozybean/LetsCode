package leetcode.editor.cn.q_295;

import java.util.PriorityQueue;
import java.util.Queue;

//<p>中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。</p>
//
//<p>例如，</p>
//
//<p>[2,3,4]&nbsp;的中位数是 3</p>
//
//<p>[2,3] 的中位数是 (2 + 3) / 2 = 2.5</p>
//
//<p>设计一个支持以下两种操作的数据结构：</p>
//
//<ul>
//	<li>void addNum(int num) - 从数据流中添加一个整数到数据结构中。</li>
//	<li>double findMedian() - 返回目前所有元素的中位数。</li>
//</ul>
//
//<p><strong>示例：</strong></p>
//
//<pre>addNum(1)
//addNum(2)
//findMedian() -&gt; 1.5
//addNum(3) 
//findMedian() -&gt; 2</pre>
//
//<p><strong>进阶:</strong></p>
//
//<ol>
//	<li>如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？</li>
//	<li>如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？</li>
//</ol>
//<div><div>Related Topics</div><div><li>设计</li><li>双指针</li><li>数据流</li><li>排序</li><li>堆（优先队列）</li></div></div><br><div><li>👍 578</li><li>👎 0</li></div>

//leetcode submit region begin(Prohibit modification and deletion)
class MedianFinder {

    // 维护一个最大堆，和一个最小堆
    // 最大堆中存储较小的元素
    // 最小堆中存储较大的元素
    // 两个堆的数量差别不超过1
    Queue<Integer> minHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(a, b)
    );
    Queue<Integer> maxHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(b, a)
    );

    public MedianFinder() {
    }

    public void addNum(int num) {
        // 如果往较少的堆中插入数据
        // 此时，num有可能大于最小堆的堆顶，不满足最大堆存储较小元素的设定
        // 1. 先插入到数据较多的堆
        // 2. 将数据较多的堆的堆顶插入到数据较少的堆；
        // 3. 此时：
        // 3.1 相当于往数据较少的堆插入，大小偏差不超过1；
        // 3.2 保证最大堆堆顶是较少一半的最大，最小堆堆顶是较大一半的最小
        if (minHeap.size() > maxHeap.size()) {
            minHeap.add(num);
            maxHeap.add(minHeap.poll());
        } else {
            maxHeap.add(num);
            minHeap.add(maxHeap.poll());
        }
    }

    public double findMedian() {
        if (minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        } else if (minHeap.size() < maxHeap.size()) {
            return maxHeap.peek();
        } else {
            if(minHeap.isEmpty()){
                return 0;
            }
            // no chance NPE
            return (minHeap.peek() + maxHeap.peek()) / 2d;
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
//leetcode submit region end(Prohibit modification and deletion)
