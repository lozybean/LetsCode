package leetcode.editor.cn.q_743;

import java.util.*;

//<p>有 <code>n</code> 个网络节点，标记为&nbsp;<code>1</code>&nbsp;到 <code>n</code>。</p>
//
//<p>给你一个列表&nbsp;<code>times</code>，表示信号经过 <strong>有向</strong> 边的传递时间。&nbsp;<code>times[i] = (u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>)</code>，其中&nbsp;<code>u<sub>i</sub></code>&nbsp;是源节点，<code>v<sub>i</sub></code>&nbsp;是目标节点， <code>w<sub>i</sub></code>&nbsp;是一个信号从源节点传递到目标节点的时间。</p>
//
//<p>现在，从某个节点&nbsp;<code>K</code>&nbsp;发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回&nbsp;<code>-1</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode.com/uploads/2019/05/23/931_example_1.png" style="height: 220px; width: 200px;" /></p>
//
//<pre>
//<strong>输入：</strong>times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
//<strong>输出：</strong>2
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>times = [[1,2,1]], n = 2, k = 1
//<strong>输出：</strong>1
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>times = [[1,2,1]], n = 2, k = 2
//<strong>输出：</strong>-1
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul>
//	<li><code>1 &lt;= k &lt;= n &lt;= 100</code></li>
//	<li><code>1 &lt;= times.length &lt;= 6000</code></li>
//	<li><code>times[i].length == 3</code></li>
//	<li><code>1 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n</code></li>
//	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
//	<li><code>0 &lt;= w<sub>i</sub> &lt;= 100</code></li>
//	<li>所有 <code>(u<sub>i</sub>, v<sub>i</sub>)</code> 对都 <strong>互不相同</strong>（即，不含重复边）</li>
//</ul>
//<div><div>Related Topics</div><div><li>深度优先搜索</li><li>广度优先搜索</li><li>图</li><li>最短路</li><li>堆（优先队列）</li></div></div><br><div><li>👍 446</li><li>👎 0</li></div>

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private List<int[]>[] convertToGraph(int[][] times, int n) {
        List<int[]>[] graph = new LinkedList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] time : times) {
            int from = time[0];
            int to = time[1];
            int cost = time[2];
            graph[from].add(new int[]{to, cost});
        }
        return graph;
    }

    private int[] distTo(int start, List<int[]>[] graph) {
        int[] distTo = new int[graph.length];
        Arrays.fill(distTo, Integer.MAX_VALUE);
        distTo[start] = 0;
////        Queue<int[]> queue = new LinkedList<>();
//        Queue<int[]> queue = new PriorityQueue<>((a,b)->{return a[1] - b[1];});
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
//        queue.offer(new int[]{start, 0});
        while (!queue.isEmpty()) {
            int nodeId = queue.poll();

//            int nodeId = node[0];
//            int dist = node[1];

//            if (dist > distTo[nodeId]) {
//                continue;
//            }

            for (int[] neighbor : graph[nodeId]) {
                int nextId = neighbor[0];
                int distToNext = distTo[nodeId] + neighbor[1];
                if (distTo[nextId] > distToNext) {
                    distTo[nextId] = distToNext;
//                    queue.offer(new int[]{nextId, distToNext});
                    queue.offer(nextId);
                }
            }
        }
        return distTo;
    }

    private int delayTime(int[][] times, int n, int k) {
        List<int[]>[] graph = convertToGraph(times, n);
        int[] distTo = distTo(k, graph);

        int delay = 0;
        // 从1开始
        for(int i=1;i<distTo.length;i++){
            if(distTo[i] == Integer.MAX_VALUE){
                return -1;
            } else if (distTo[i] > delay) {
                delay = distTo[i];
            }

        }
        return delay;
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        return delayTime(times, n,k);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
