package leetcode.editor.cn.q_51;

import leetcode.editor.cn.def.*;

import java.util.*;

//n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。 
//
// 
// 
// 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 4
//输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
//解释：如上图所示，4 皇后问题存在两个不同的解法。
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：[["Q"]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 9 
// 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。 
// 
// 
// 
// Related Topics 数组 回溯 👍 1084 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private List<List<String>> res = new LinkedList<>();
    boolean[] colQueue;

    private boolean isValid(char[][] board, int row, int col) {
        if (colQueue[col]) return false;
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }
        return true;
    }

    private void backtrack(char[][] board, int row) {
        if (row == board.length) {
            List<String> temp = new LinkedList<>();
            for (char[] r : board) {
                temp.add(String.valueOf(r));
            }
            res.add(temp);
            return;
        }
        for (int col = 0; col < board.length; col++) {
            if (!isValid(board, row, col)) {
                continue;
            }
            colQueue[col] = true;
            board[row][col] = 'Q';
            backtrack(board, row + 1);
            board[row][col] = '.';
            colQueue[col] = false;
        }
    }

    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        colQueue = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        backtrack(board, 0);
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
