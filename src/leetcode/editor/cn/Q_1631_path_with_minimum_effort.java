package leetcode.editor.cn.q_1631;

import java.util.*;

//<p>你准备参加一场远足活动。给你一个二维 <code>rows x columns</code> 的地图 <code>heights</code> ，其中 <code>heights[row][col]</code> 表示格子 <code>(row, col)</code> 的高度。一开始你在最左上角的格子 <code>(0, 0)</code> ，且你希望去最右下角的格子 <code>(rows-1, columns-1)</code> （注意下标从 <strong>0</strong> 开始编号）。你每次可以往 <strong>上</strong>，<strong>下</strong>，<strong>左</strong>，<strong>右</strong> 四个方向之一移动，你想要找到耗费 <strong>体力</strong> 最小的一条路径。</p>
//
//<p>一条路径耗费的 <strong>体力值</strong> 是路径上相邻格子之间 <strong>高度差绝对值</strong> 的 <strong>最大值</strong> 决定的。</p>
//
//<p>请你返回从左上角走到右下角的最小<strong> 体力消耗值</strong> 。</p>
//
//<p> </p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/10/25/ex1.png" style="width: 300px; height: 300px;" /></p>
//
//<pre>
//<b>输入：</b>heights = [[1,2,2],[3,8,2],[5,3,5]]
//<b>输出：</b>2
//<b>解释：</b>路径 [1,3,5,3,5] 连续格子的差值绝对值最大为 2 。
//这条路径比路径 [1,2,2,2,5] 更优，因为另一条路径差值最大值为 3 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/10/25/ex2.png" style="width: 300px; height: 300px;" /></p>
//
//<pre>
//<b>输入：</b>heights = [[1,2,3],[3,8,4],[5,3,5]]
//<b>输出：</b>1
//<b>解释：</b>路径 [1,2,3,4,5] 的相邻格子差值绝对值最大为 1 ，比路径 [1,3,5,3,5] 更优。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//<img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/10/25/ex3.png" style="width: 300px; height: 300px;" />
//<pre>
//<b>输入：</b>heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
//<b>输出：</b>0
//<b>解释：</b>上图所示路径不需要消耗任何体力。
//</pre>
//
//<p> </p>
//
//<p><strong>提示：</strong></p>
//
//<ul>
//	<li><code>rows == heights.length</code></li>
//	<li><code>columns == heights[i].length</code></li>
//	<li><code>1 <= rows, columns <= 100</code></li>
//	<li><code>1 <= heights[i][j] <= 10<sup>6</sup></code></li>
//</ul>
//<div><div>Related Topics</div><div><li>深度优先搜索</li><li>广度优先搜索</li><li>并查集</li><li>数组</li><li>二分查找</li><li>矩阵</li><li>堆（优先队列）</li></div></div><br><div><li>👍 253</li><li>👎 0</li></div>

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private List<int[]>[] convertToGraph(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;
        int[][] direction = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        List<int[]>[] graph = new LinkedList[rows * cols];
        // 按行遍历
        for (int i = 0; i < rows * cols; i++) {
            graph[i] = new LinkedList<>();
            int row = i / cols;
            int col = i % cols;
            for (int[] dir : direction) {
                int x = row + dir[0];
                int y = col + dir[1];
                if (x >= rows || x < 0 || y >= cols || y < 0) {
                    continue;
                }
                graph[i].add(new int[]{x, y});
            }
        }
        return graph;
    }

    // 158ms 11.18% beat
    // 思路：通过bfs遍历邻接矩阵，找最小消耗
    // 缺点：
    // 1. 邻接矩阵和具体heights无关，只和height矩阵大小有关，可以避免构建整个矩阵
    // 2. 使用优先队列，贪心优化 => 贪心优化后第一次碰到终点即可退出
    private int bfsDistTo(int[][] heights, List<int[]>[] graph) {
        int rows = heights.length;
        int cols = heights[0].length;

        int[][] distTo = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            Arrays.fill(distTo[i], Integer.MAX_VALUE);
        }
        distTo[0][0] = 0;

        // (x,y)
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});

        while (!queue.isEmpty()) {
            int[] position = queue.poll();
            int x = position[0];
            int y = position[1];

            for (int[] neighbor : graph[x * cols + y]) {
                int nextX = neighbor[0];
                int nextY = neighbor[1];
                int distToNext = Math.max(
                        distTo[x][y],
                        Math.abs(heights[x][y] - heights[nextX][nextY])
                );
                if (distTo[nextX][nextY] > distToNext) {
                    System.out.printf("x: %d, y: %d, effort: %d%n", nextX, nextY, distToNext);
                    distTo[nextX][nextY] = distToNext;
                    queue.offer(new int[]{nextX, nextY});
                }
            }
        }

        return distTo[rows - 1][cols - 1];
    }

    // 不需要返回整个邻接矩阵，只要返回相邻位置即可
    private List<int[]> adj(int[][] heights, int x, int y) {
        int rows = heights.length;
        int cols = heights[0].length;
        int[][] direction = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        List<int[]> graph = new LinkedList<>();
        // 按行遍历
        for (int[] dir : direction) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= rows || nx < 0 || ny >= cols || ny < 0) {
                continue;
            }
            graph.add(new int[]{nx, ny});
        }
        return graph;
    }

    // 58ms, 72.26% beat
    private int bfsDistTo2(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;
        int[][] distTo = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            Arrays.fill(distTo[i], Integer.MAX_VALUE);
        }
        distTo[0][0] = 0;
        // (x, y, effort)
        // 使用优先队列，优先走当前最小的路径
        // 实际提升效果明显
        Queue<int[]> queue = new PriorityQueue<>((x, y) -> (x[2] - y[2]));
        queue.offer(new int[]{0, 0, 0});
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curX = current[0];
            int curY = current[1];
            int curEffort = current[2];

            if (curX == rows - 1 && curY == cols - 1) {
                return curEffort;
            }
            // 这个还是没有必要
//            if (curEffort > distTo[curX][curY]) {
//                continue;
//            }

            for (int[] neighbors : adj(heights, curX, curY)) {
                int nextX = neighbors[0];
                int nextY = neighbors[1];
                int effort = Math.max(
                        distTo[curX][curY],
                        Math.abs(heights[curX][curY] - heights[nextX][nextY])
                );
//                System.out.printf("neighbor x: %d, y: %d, effort: %d, history: %d%n", nextX, nextY, effort, distTo[nextX][nextY]);
                if (distTo[nextX][nextY] > effort) {
                    System.out.printf("x: %d, y: %d, effort: %d%n", nextX, nextY, effort);
                    distTo[nextX][nextY] = effort;
                    queue.offer(new int[]{nextX, nextY, effort});
                }
            }
        }
        return -1;
//        return distTo[rows-1][cols-1];
    }

    public int minimumEffortPath(int[][] heights) {
//        List<int[]>[] graph = convertToGraph(heights);
//        bfsDistTo(heights, graph);
//        System.out.println("=================");
        return bfsDistTo2(heights);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.minimumEffortPath(new int[][]{
                {1, 2, 1, 1, 1},
                {1, 2, 1, 2, 1},
                {1, 2, 1, 2, 1},
                {1, 2, 1, 2, 1},
                {1, 1, 1, 2, 1}
        });
//        solution.minimumEffortPath(new int[][] {
//                {1,2,2},
//                {3,8,2},
//                {5,3,5}
//        });
    }
}
//leetcode submit region end(Prohibit modification and deletion)
