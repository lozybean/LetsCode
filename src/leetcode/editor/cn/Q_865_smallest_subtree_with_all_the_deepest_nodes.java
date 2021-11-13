package leetcode.editor.cn.q_865;

import leetcode.editor.cn.def.*;

import java.util.*;

//<p>给定一个根为 <code>root</code> 的二叉树，每个节点的深度是 <strong>该节点到根的最短距离</strong> 。</p>
//
//<p>如果一个节点在 <strong>整个树 </strong>的任意节点之间具有最大的深度，则该节点是 <strong>最深的</strong> 。</p>
//
//<p>一个节点的 <strong>子树</strong> 是该节点加上它的所有后代的集合。</p>
//
//<p>返回能满足 <strong>以该节点为根的子树中包含所有最深的节点</strong> 这一条件的具有最大深度的节点。</p>
//
//<p> </p>
//
//<p><strong>注意：</strong>本题与力扣 1123 重复：<a href="https://leetcode-cn.com/problems/lowest-common-ancestor-of-deepest-leaves/" target="_blank">https://leetcode-cn.com/problems/lowest-common-ancestor-of-deepest-leaves/</a></p>
//
//<p> </p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><img alt="" height="475" src="https://s3-lc-upload.s3.amazonaws.com/uploads/2018/07/01/sketch1.png" width="560" /></p>
//
//<pre>
//<strong>输入：</strong>root = [3,5,1,6,2,0,8,null,null,7,4]
//<strong>输出：</strong>[2,7,4]
//<strong>解释：</strong>
//我们返回值为 2 的节点，在图中用黄色标记。
//在图中用蓝色标记的是树的最深的节点。
//注意，节点 5、3 和 2 包含树中最深的节点，但节点 2 的子树最小，因此我们返回它。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>root = [1]
//<strong>输出：</strong>[1]
//<strong>解释：</strong>根节点是树中最深的节点。</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>root = [0,1,3,null,2]
//<strong>输出：</strong>[2]
//<strong>解释：</strong>树中最深的节点为 2 ，有效子树为节点 2、1 和 0 的子树，但节点 2 的子树最小。</pre>
//
//<p> </p>
//
//<p><strong>提示：</strong></p>
//
//<ul>
//	<li>树中节点的数量介于 1 和 500 之间。</li>
//	<li><code>0 <= Node.val <= 500</code></li>
//	<li>每个节点的值都是独一无二的。</li>
//</ul>
//<div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>广度优先搜索</li><li>哈希表</li><li>二叉树</li></div></div><br><div><li>👍 137</li><li>👎 0</li></div>

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
    public static class Pair {
        TreeNode node;
        int depth;

        public Pair(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    private Pair getLca(TreeNode node, int depth) {
        if (node == null) return new Pair(null, depth);
        Pair leftLca = getLca(node.left, depth + 1);
        Pair rightLca = getLca(node.right, depth + 1);
        if (leftLca.depth > rightLca.depth) {
            return leftLca;
        } else if (leftLca.depth < rightLca.depth) {
            return rightLca;
        } else {
            return new Pair(node, leftLca.depth);
        }
    }

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        Pair rootLca = getLca(root, 0);
        return rootLca.node;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
