package leetcode.editor.cn.q_99;

import leetcode.editor.cn.def.*;

import java.util.*;

//给你二叉搜索树的根节点 root ，该树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。 
//
// 进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用常数空间的解决方案吗？ 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,3,null,null,2]
//输出：[3,1,null,null,2]
//解释：3 不能是 1 左孩子，因为 3 > 1 。交换 1 和 3 使二叉搜索树有效。
// 
//
// 示例 2： 
//
// 
//输入：root = [3,1,4,null,null,2]
//输出：[2,1,4,null,null,3]
//解释：2 不能在 3 的右子树中，因为 2 < 3 。交换 2 和 3 使二叉搜索树有效。 
//
// 
//
// 提示： 
//
// 
// 树上节点的数目在范围 [2, 1000] 内 
// -2³¹ <= Node.val <= 2³¹ - 1 
// 
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 573 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    private TreeNode x = null;
    private TreeNode y = null;
    private TreeNode pre = null;


    private void swap(TreeNode x, TreeNode y) {
        int tmp = x.val;
        x.val = y.val;
        y.val = tmp;
    }

    // 2ms 99.98% beat
    // 38.8MB 50.28% beat
    // 消耗 O(H) 栈空间，非O(1)空间消耗
    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        if (pre != null && node.val < pre.val) {
            y = node;
            if (x == null) {
                x = pre;
            } else {
                return;
            }
        }
        pre = node;
        dfs(node.right);

    }

    // 3ms 48.61% beat
    // 38.9MB 11.30% beat
    // 理论空间消耗 O(1)，但是比dfs版本消耗更大了
    // Morris 遍历过程中，找到了x,y不能提前return，否则会有前驱节点右子树未断开导致的循环问题，return前解开也不行，目前不知道怎么回事

    /**
     * Morris 遍历
     * <p>
     * 1. 如果 x 无左孩子，则访问 x 的右孩子，即 x=x.right。
     * 2. 如果 x 有左孩子，则找到 x 左子树上最右的节点（即左子树中序遍历的最后一个节点，x 在中序遍历中的前驱节点），我们记为 predecessor。根据 predecessor 的右孩子是否为空，进行如下操作。
     * - 2.1 如果 predecessor 的右孩子为空，则将其右孩子指向 x，然后访问 x 的左孩子，即 x=x.left。
     * - 2.2 如果 predecessor 的右孩子不为空，则此时其右孩子指向 x，说明我们已经遍历完 x 的左子树，我们将 predecessor 的右孩子置空，然后访问 x 的右孩子，即 x=x.right。
     * 3. 重复上述操作，直至访问完整棵树。
     */
    private void morris(TreeNode node) {
        TreeNode predecessor;
        while (node != null) {
            if (node.left != null) {
                predecessor = node.left;
                while (predecessor.right != null && predecessor.right != node) {
                    predecessor = predecessor.right;
                }
                if (predecessor.right == null) {
                    predecessor.right = node;
                    node = node.left;
                } else {
                    // 以下为处理逻辑
                    if (pre != null && node.val < pre.val) {
                        y = node;
                        if (x == null) {
                            x = pre;
                        }
                        // 这里就算解开predecessor.right=null后return，也会导致循环问题
                    }
                    pre = node;
                    // 以上为处理逻辑

                    predecessor.right = null;
                    node = node.right;
                }
            } else {
                // 以下为处理逻辑
                if (pre != null && node.val < pre.val) {
                    y = node;
                    if (x == null) {
                        x = pre;
                    }
                }
                pre = node;
                // 以上为处理逻辑

                node = node.right;
            }
        }
    }

    public void recoverTree(TreeNode root) {
        morris(root);
        swap(x, y);
    }
}

//leetcode submit region end(Prohibit modification and deletion)
