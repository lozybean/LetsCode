package leetcode.editor.cn.q_1020;

//<p>给出一个二维数组&nbsp;<code>A</code>，每个单元格为 0（代表海）或 1（代表陆地）。</p>
//
//<p>移动是指在陆地上从一个地方走到另一个地方（朝四个方向之一）或离开网格的边界。</p>
//
//<p>返回网格中<strong>无法</strong>在任意次数的移动中离开网格边界的陆地单元格的数量。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>[[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
//<strong>输出：</strong>3
//<strong>解释： </strong>
//有三个 1 被 0 包围。一个 1 没有被包围，因为它在边界上。</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>[[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
//<strong>输出：</strong>0
//<strong>解释：</strong>
//所有 1 都在边界上或可以到达边界。</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ol>
//	<li><code>1 &lt;= A.length &lt;= 500</code></li>
//	<li><code>1 &lt;= A[i].length &lt;= 500</code></li>
//	<li><code>0 &lt;= A[i][j] &lt;= 1</code></li>
//	<li>所有行的大小都相同</li>
//</ol>
//<div><div>Related Topics</div><div><li>深度优先搜索</li><li>广度优先搜索</li><li>并查集</li><li>数组</li><li>矩阵</li></div></div><br><div><li>👍 58</li><li>👎 0</li></div>

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

    public int numEnclaves(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (rows < 3 || cols < 3) {
            // 至少要三行或者三列才能关住
            return 0;
        }
        // 尝试从边界逃离
        for (int i = 0; i < rows; i++) {
            dfs(grid, i, 0);
            dfs(grid, i, cols - 1);
        }
        for (int i = 0; i < cols; i++) {
            dfs(grid, 0, i);
            dfs(grid, rows - 1, i);
        }

        // 统计未逃离的数量
        int count = 0;
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (grid[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
