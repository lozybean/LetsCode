package leetcode.editor.cn.q_1514;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

//<p>给你一个由 <code>n</code> 个节点（下标从 0 开始）组成的无向加权图，该图由一个描述边的列表组成，其中 <code>edges[i] = [a, b]</code> 表示连接节点 a 和 b 的一条无向边，且该边遍历成功的概率为 <code>succProb[i]</code> 。</p>
//
//<p>指定两个节点分别作为起点 <code>start</code> 和终点 <code>end</code> ，请你找出从起点到终点成功概率最大的路径，并返回其成功概率。</p>
//
//<p>如果不存在从 <code>start</code> 到 <code>end</code> 的路径，请 <strong>返回 0</strong> 。只要答案与标准答案的误差不超过 <strong>1e-5 </strong>，就会被视作正确答案。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><strong><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/07/12/1558_ex1.png" style="height: 186px; width: 187px;"></strong></p>
//
//<pre><strong>输入：</strong>n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
//<strong>输出：</strong>0.25000
//<strong>解释：</strong>从起点到终点有两条路径，其中一条的成功概率为 0.2 ，而另一条为 0.5 * 0.5 = 0.25
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<p><strong><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/07/12/1558_ex2.png" style="height: 186px; width: 189px;"></strong></p>
//
//<pre><strong>输入：</strong>n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
//<strong>输出：</strong>0.30000
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<p><strong><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/07/12/1558_ex3.png" style="height: 191px; width: 215px;"></strong></p>
//
//<pre><strong>输入：</strong>n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
//<strong>输出：</strong>0.00000
//<strong>解释：</strong>节点 0 和 节点 2 之间不存在路径
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul>
//	<li><code>2 &lt;= n &lt;= 10^4</code></li>
//	<li><code>0 &lt;= start, end &lt; n</code></li>
//	<li><code>start != end</code></li>
//	<li><code>0 &lt;= a, b &lt; n</code></li>
//	<li><code>a != b</code></li>
//	<li><code>0 &lt;= succProb.length == edges.length &lt;= 2*10^4</code></li>
//	<li><code>0 &lt;= succProb[i] &lt;= 1</code></li>
//	<li>每两个节点之间最多有一条边</li>
//</ul>
//<div><div>Related Topics</div><div><li>图</li><li>最短路</li><li>堆（优先队列）</li></div></div><br><div><li>👍 77</li><li>👎 0</li></div>

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private static class Path {
        private final int to;
        private final double prob;

        public Path(int to, double prob) {
            this.to = to;
            this.prob = prob;
        }
    }

    private static class Edge {
        private final int current;
        private final double probFromStart;

        public Edge(int current, double prob) {
            this.current = current;
            this.probFromStart = prob;
        }
    }

    // 从某个节点到当前的概率
    private List<Path>[] buildGraph(int n, int[][] edges, double[] prob) {
        List<Path>[] graph = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int i = 0; i < prob.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            double p = prob[i];

            graph[from].add(new Path(to, p));

            graph[to].add(new Path(from, p));

        }
        return graph;
    }

    private double bfs(int n, int[][] edges, double[] succProb, int start, int end) {
        List<Path>[] graph = buildGraph(n, edges, succProb);
        // 默认概率都为0
        double[] probFromStart = new double[n];
        // 记录start到当前节点的最大概率
        Queue<Edge> queue = new PriorityQueue<>((a, b) -> (Double.compare(b.probFromStart, a.probFromStart)));
        if (graph[start].isEmpty()) {
            if (start == end) return 1;
            else return 0;
        }
        probFromStart[start] = 1;
        queue.add(new Edge(start, 1));


        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            int current = edge.current;
            double currentProbFromStart = edge.probFromStart;
            // 只有使用优先队列的情况下才可以直接返回
            if (current == end) {
                return currentProbFromStart;
            }
            for (Path path : graph[current]) {
                int next = path.to;
                double probToNext = path.prob;

                // from start to next
                double maxProb = Math.max(
                        probFromStart[next],
                        probFromStart[current] * probToNext
                );
                if (maxProb > probFromStart[next]) {
//                    System.out.printf("current: %d, prob: %f%n", next, maxProb);
                    probFromStart[next] = maxProb;
                    queue.add(new Edge(next, maxProb));
                }
            }
        }
        return 0;
    }

    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        // 排除没有边的情况，后续函数中，默认边至少有一个
        if (succProb.length == 0) {
            if (start == end) return 1;
            else return 0;
        }
        return bfs(n, edges, succProb, start, end);
    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        solution.maxProbability(3,
//                new int[][]{
//                        {0, 1},
//                        {1, 2},
//                        {0, 2}
//                },
//                new double[]{0.5, 0.5, 0.2},
//                0, 2
//        );
//    }
}
//leetcode submit region end(Prohibit modification and deletion)
