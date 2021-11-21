package leetcode.editor.cn.q_463;

//<p>给定一个 <code>row x col</code> 的二维网格地图 <code>grid</code> ，其中：<code>grid[i][j] = 1</code> 表示陆地， <code>grid[i][j] = 0</code> 表示水域。</p>
//
//<p>网格中的格子 <strong>水平和垂直</strong> 方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。</p>
//
//<p>岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。</p>
//
//<p> </p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/12/island.png" /></p>
//
//<pre>
//<strong>输入：</strong>grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
//<strong>输出：</strong>16
//<strong>解释：</strong>它的周长是上面图片中的 16 个黄色的边</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>grid = [[1]]
//<strong>输出：</strong>4
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>grid = [[1,0]]
//<strong>输出：</strong>4
//</pre>
//
//<p> </p>
//
//<p><strong>提示：</strong></p>
//
//<ul>
//	<li><code>row == grid.length</code></li>
//	<li><code>col == grid[i].length</code></li>
//	<li><code>1 <= row, col <= 100</code></li>
//	<li><code>grid[i][j]</code> 为 <code>0</code> 或 <code>1</code></li>
//</ul>
//<div><div>Related Topics</div><div><li>深度优先搜索</li><li>广度优先搜索</li><li>数组</li><li>矩阵</li></div></div><br><div><li>👍 466</li><li>👎 0</li></div>

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // (x,y)是否是1，是返回1，否返回0
    private int adj(int[][] grid,
                    int x, int y) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (x < 0 || x >= rows || y < 0 || y >= cols) {
            return 0;
        }
        if (grid[x][y] == 1) return 1;
        else return 0;
    }

    // 一个格子对周长的贡献为：
    // 4 - 相邻为1
    private int getLen(int[][] grid, int x, int y) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (x < 0 || x >= rows || y < 0 || y >= cols) {
            return 0;
        }
        if (grid[x][y] == 0) return 0;
        return 4
                - adj(grid, x + 1, y)
                - adj(grid, x - 1, y)
                - adj(grid, x, y + 1)
                - adj(grid, x, y - 1);
    }

    public int islandPerimeter(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int perimeter = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0) continue;
                perimeter += getLen(grid, i, j);
            }
        }
        return perimeter;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
