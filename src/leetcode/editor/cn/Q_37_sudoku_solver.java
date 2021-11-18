package leetcode.editor.cn.q_37;

import leetcode.editor.cn.def.*;

import java.util.*;

//编写一个程序，通过填充空格来解决数独问题。 
//
// 数独的解法需 遵循如下规则： 
//
// 
// 数字 1-9 在每一行只能出现一次。 
// 数字 1-9 在每一列只能出现一次。 
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图） 
// 
//
// 数独部分空格内已填入了数字，空白格用 '.' 表示。 
//
// 
//
// 
// 
// 
// 示例： 
//
// 
//输入：board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".
//",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".
//","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6
//"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[
//".",".",".",".","8",".",".","7","9"]]
//输出：[["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8
//"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],[
//"4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9",
//"6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4",
//"5","2","8","6","1","7","9"]]
//解释：输入的数独如上图所示，唯一有效的解决方案如下所示：
//
//
// 
//
// 
//
// 提示： 
//
// 
// board.length == 9 
// board[i].length == 9 
// board[i][j] 是一位数字或者 '.' 
// 题目数据 保证 输入数独仅有一个解 
// 
// 
// 
// 
// Related Topics 数组 回溯 矩阵 👍 1023 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 来自：Q36
    public boolean isValidSudoku(char[][] board) {
        // row[i][j] 表示第 i 行，j数字出现次数
        // col[i][j] 表示第 i 列，j数字出现次数
        // box[i][j][k] 表示左上角为i,j 的九宫格中，j数字出现次数

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int c = board[i][j] - '0' - 1;
                    if (++row[i][c] > 1) {
                        return false;
                    }
                    if (++col[j][c] > 1) {
                        return false;
                    }
                    if (++box[i / 3][j / 3][c] > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    int[][] row = new int[9][9];
    int[][] col = new int[9][9];
    int[][][] box = new int[3][3][9];

    private boolean backtrack(char[][] board, int r, int c) {
        if (c == 9) {
            return backtrack(board, r + 1, 0);
        }
        if (r == 9) return true;
        for (int i = r; i < 9; i++) {
            for (int j = c; j < 9; j++) {
                if (board[i][j] != '.') {
                    return backtrack(board, i, j + 1);
                }

                for (char ch = '1'; ch <= '9'; ch++) {
                    int idx = ch - '1';
                    if (row[i][idx] >= 1 || col[j][idx] >= 1 || box[i / 3][j / 3][idx] >= 1) {
                        continue;
                    }

                    row[i][idx]++;
                    col[j][idx]++;
                    box[i / 3][j / 3][idx]++;
                    board[i][j] = ch;
                    if (backtrack(board, i, j + 1)) return true;
                    board[i][j] = '.';
                    row[i][idx]--;
                    col[j][idx]--;
                    box[i / 3][j / 3][idx]--;
                }
                return false;
            }
        }
        return false;
    }

    public void solveSudoku(char[][] board) {
        // 已经填好的
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int c = board[i][j] - '0' - 1;
                    ++row[i][c];
                    ++col[j][c];
                    ++box[i / 3][j / 3][c];
                }
            }
        }
        backtrack(board, 0, 0);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
