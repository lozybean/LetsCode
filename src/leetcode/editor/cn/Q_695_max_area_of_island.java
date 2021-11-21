package leetcode.editor.cn.q_695;

//<p>给你一个大小为 <code>m x n</code> 的二进制矩阵 <code>grid</code> 。</p>
//
//<p><strong>岛屿</strong>&nbsp;是由一些相邻的&nbsp;<code>1</code>&nbsp;(代表土地) 构成的组合，这里的「相邻」要求两个 <code>1</code> 必须在 <strong>水平或者竖直的四个方向上 </strong>相邻。你可以假设&nbsp;<code>grid</code> 的四个边缘都被 <code>0</code>（代表水）包围着。</p>
//
//<p>岛屿的面积是岛上值为 <code>1</code> 的单元格的数目。</p>
//
//<p>计算并返回 <code>grid</code> 中最大的岛屿面积。如果没有岛屿，则返回面积为 <code>0</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//<img alt="" src="https://assets.leetcode.com/uploads/2021/05/01/maxarea1-grid.jpg" style="width: 500px; height: 310px;" />
//<pre>
//<strong>输入：</strong>grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
//<strong>输出：</strong>6
//<strong>解释：</strong>答案不应该是 <code>11</code> ，因为岛屿只能包含水平或垂直这四个方向上的 <code>1</code> 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>grid = [[0,0,0,0,0,0,0,0]]
//<strong>输出：</strong>0
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul>
//	<li><code>m == grid.length</code></li>
//	<li><code>n == grid[i].length</code></li>
//	<li><code>1 &lt;= m, n &lt;= 50</code></li>
//	<li><code>grid[i][j]</code> 为 <code>0</code> 或 <code>1</code></li>
//</ul>
//<div><div>Related Topics</div><div><li>深度优先搜索</li><li>广度优先搜索</li><li>并查集</li><li>数组</li><li>矩阵</li></div></div><br><div><li>👍 599</li><li>👎 0</li></div>

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private int dfs(int[][] grid, int x, int y) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (x < 0 || x >= rows || y < 0 || y >= cols) return 0;
        if (grid[x][y] == 0) return 0;
        grid[x][y] = 0;
        return 1
                + dfs(grid, x + 1, y)
                + dfs(grid, x - 1, y)
                + dfs(grid, x, y + 1)
                + dfs(grid, x, y - 1);
    }

    public int maxAreaOfIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int maxArea = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    int area = dfs(grid, i, j);
                    if (maxArea < area) {
                        maxArea = area;
                    }
                }
            }
        }
        return maxArea;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
