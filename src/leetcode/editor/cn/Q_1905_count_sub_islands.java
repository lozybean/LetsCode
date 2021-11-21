package leetcode.editor.cn.q_1905;

//<p>给你两个 <code>m x n</code> 的二进制矩阵 <code>grid1</code> 和 <code>grid2</code> ，它们只包含 <code>0</code> （表示水域）和 <code>1</code> （表示陆地）。一个 <strong>岛屿</strong> 是由 <strong>四个方向</strong> （水平或者竖直）上相邻的 <code>1</code> 组成的区域。任何矩阵以外的区域都视为水域。</p>
//
//<p>如果 <code>grid2</code> 的一个岛屿，被 <code>grid1</code> 的一个岛屿 <strong>完全</strong> 包含，也就是说 <code>grid2</code> 中该岛屿的每一个格子都被 <code>grid1</code> 中同一个岛屿完全包含，那么我们称 <code>grid2</code> 中的这个岛屿为 <strong>子岛屿</strong> 。</p>
//
//<p>请你返回 <code>grid2</code> 中 <strong>子岛屿</strong> 的 <strong>数目</strong> 。</p>
//
//<p> </p>
//
//<p><strong>示例 1：</strong></p>
//<img alt="" src="https://assets.leetcode.com/uploads/2021/06/10/test1.png" style="width: 493px; height: 205px;">
//<pre><b>输入：</b>grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
//<b>输出：</b>3
//<strong>解释：</strong>如上图所示，左边为 grid1 ，右边为 grid2 。
//grid2 中标红的 1 区域是子岛屿，总共有 3 个子岛屿。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//<img alt="" src="https://assets.leetcode.com/uploads/2021/06/03/testcasex2.png" style="width: 491px; height: 201px;">
//<pre><b>输入：</b>grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
//<b>输出：</b>2 
//<strong>解释：</strong>如上图所示，左边为 grid1 ，右边为 grid2 。
//grid2 中标红的 1 区域是子岛屿，总共有 2 个子岛屿。
//</pre>
//
//<p> </p>
//
//<p><strong>提示：</strong></p>
//
//<ul>
//	<li><code>m == grid1.length == grid2.length</code></li>
//	<li><code>n == grid1[i].length == grid2[i].length</code></li>
//	<li><code>1 &lt;= m, n &lt;= 500</code></li>
//	<li><code>grid1[i][j]</code> 和 <code>grid2[i][j]</code> 都要么是 <code>0</code> 要么是 <code>1</code> 。</li>
//</ul>
//<div><div>Related Topics</div><div><li>深度优先搜索</li><li>广度优先搜索</li><li>并查集</li><li>数组</li><li>矩阵</li></div></div><br><div><li>👍 23</li><li>👎 0</li></div>

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private void dfs(int[][] grid, int x, int y) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (x < 0 || x >= rows || y < 0 || y >= cols) return;
        if (grid[x][y] == 0) return;
        grid[x][y] = 0;
        dfs(grid, x + 1, y);
        dfs(grid, x - 1, y);
        dfs(grid, x, y + 1);
        dfs(grid, x, y - 1);
    }

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int rows = grid1.length;
        int cols = grid1[0].length;

        // 移除非子岛屿
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid2[i][j] == 1 && grid1[i][j] == 0) {
                    dfs(grid2, i, j);
                }
            }
        }

        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid2[i][j] == 1) {
                    count++;
                    dfs(grid2, i, j);
                }
            }
        }
        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
