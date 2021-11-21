package leetcode.editor.cn.q_1254;

//<p>有一个二维矩阵 <code>grid</code>&nbsp;，每个位置要么是陆地（记号为&nbsp;<code>0</code> ）要么是水域（记号为&nbsp;<code>1</code> ）。</p>
//
//<p>我们从一块陆地出发，每次可以往上下左右&nbsp;4 个方向相邻区域走，能走到的所有陆地区域，我们将其称为一座「<strong>岛屿</strong>」。</p>
//
//<p>如果一座岛屿&nbsp;<strong>完全</strong>&nbsp;由水域包围，即陆地边缘上下左右所有相邻区域都是水域，那么我们将其称为 「<strong>封闭岛屿</strong>」。</p>
//
//<p>请返回封闭岛屿的数目。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/11/07/sample_3_1610.png"></p>
//
//<pre><strong>输入：</strong>grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
//<strong>输出：</strong>2
//<strong>解释：</strong>
//灰色区域的岛屿是封闭岛屿，因为这座岛屿完全被水域包围（即被 1 区域包围）。</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<p><img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/11/07/sample_4_1610.png"></p>
//
//<pre><strong>输入：</strong>grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
//<strong>输出：</strong>1
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre><strong>输入：</strong>grid = [[1,1,1,1,1,1,1],
//&nbsp;            [1,0,0,0,0,0,1],
//&nbsp;            [1,0,1,1,1,0,1],
//&nbsp;            [1,0,1,0,1,0,1],
//&nbsp;            [1,0,1,1,1,0,1],
//&nbsp;            [1,0,0,0,0,0,1],
//             [1,1,1,1,1,1,1]]
//<strong>输出：</strong>2
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul>
//	<li><code>1 &lt;= grid.length, grid[0].length &lt;= 100</code></li>
//	<li><code>0 &lt;= grid[i][j] &lt;=1</code></li>
//</ul>
//<div><div>Related Topics</div><div><li>深度优先搜索</li><li>广度优先搜索</li><li>并查集</li><li>数组</li><li>矩阵</li></div></div><br><div><li>👍 99</li><li>👎 0</li></div>

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private void dfs(int[][] grid, int x, int y) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (x >= rows || x < 0 || y >= cols || y < 0) {
            return;
        }
        if (grid[x][y] == 1) return;
        grid[x][y] = 1;
        dfs(grid, x + 1, y);
        dfs(grid, x - 1, y);
        dfs(grid, x, y + 1);
        dfs(grid, x, y - 1);
    }

    // 去除边缘的岛屿
    private void fillEdgeIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            dfs(grid, i, 0);
            dfs(grid, i, cols - 1);
        }
        for (int i = 0; i < cols; i++) {
            dfs(grid, 0, i);
            dfs(grid, rows - 1, i);
        }
    }


    public int closedIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (rows < 3 || cols < 3) {
            return 0;
        }
        // 先填充边缘的岛屿后，与Q200同
        fillEdgeIsland(grid);
        // 不需要遍历边缘了
        int count = 0;
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (grid[i][j] == 0) {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
