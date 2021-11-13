package leetcode.editor.cn.q_236;

import leetcode.editor.cn.def.*;

import java.util.*;

//<p>给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。</p>
//
//<p><a href="https://baike.baidu.com/item/%E6%9C%80%E8%BF%91%E5%85%AC%E5%85%B1%E7%A5%96%E5%85%88/8918834?fr=aladdin" target="_blank">百度百科</a>中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（<strong>一个节点也可以是它自己的祖先</strong>）。”</p>
//
//<p> </p>
//
//<p><strong>示例 1：</strong></p>
//<img alt="" src="https://assets.leetcode.com/uploads/2018/12/14/binarytree.png" style="width: 200px; height: 190px;" />
//<pre>
//<strong>输入：</strong>root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//<strong>输出：</strong>3
//<strong>解释：</strong>节点 <code>5 </code>和节点 <code>1 </code>的最近公共祖先是节点 <code>3 。</code>
//</pre>
//
//<p><strong>示例 2：</strong></p>
//<img alt="" src="https://assets.leetcode.com/uploads/2018/12/14/binarytree.png" style="width: 200px; height: 190px;" />
//<pre>
//<strong>输入：</strong>root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//<strong>输出：</strong>5
//<strong>解释：</strong>节点 <code>5 </code>和节点 <code>4 </code>的最近公共祖先是节点 <code>5 。</code>因为根据定义最近公共祖先节点可以为节点本身。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>root = [1,2], p = 1, q = 2
//<strong>输出：</strong>1
//</pre>
//
//<p> </p>
//
//<p><strong>提示：</strong></p>
//
//<ul>
//	<li>树中节点数目在范围 <code>[2, 10<sup>5</sup>]</code> 内。</li>
//	<li><code>-10<sup>9</sup> <= Node.val <= 10<sup>9</sup></code></li>
//	<li>所有 <code>Node.val</code> <code>互不相同</code> 。</li>
//	<li><code>p != q</code></li>
//	<li><code>p</code> 和 <code>q</code> 均存在于给定的二叉树中。</li>
//</ul>
//<div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍 1379</li><li>👎 0</li></div>

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private void dfs(TreeNode parentNode, TreeNode cur,
                     Map<TreeNode, TreeNode> parentMap) {
        if (cur == null) return;
        parentMap.put(cur, parentNode);
        dfs(cur, cur.left, parentMap);
        dfs(cur, cur.right, parentMap);
    }

    // 如果直接给出parent（Q1650）
    // 1. 按照类似方法直接向上找
    // 2. 退化为链表的交汇点问题
    private TreeNode byStoreParent(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        dfs(null, root, parentMap);

        Set<TreeNode> pAncestors = new HashSet<>();
        while (p != null) {
            pAncestors.add(p);
            p = parentMap.get(p);
        }

        while (!pAncestors.contains(q)) {
            q = parentMap.get(q);
        }
        return q;
    }

    // 当有其中有可能不存在时，需要改成 postorder (Q1644)
    private TreeNode byRecursive(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {
            return root;
        }

        TreeNode left = byRecursive(root.left, p, q);
        TreeNode right = byRecursive(root.right, p, q);
        if (left != null && right != null) {
            return root;
        } else if (left != null) {
            return left;
        } else return right;
    }

    // Q1677: 多个node
    private TreeNode lcaWithMultipleChild(TreeNode root, Set<TreeNode> set) {
        if (root == null) return null;
        if (set.contains(root)) return root;
        TreeNode left = lcaWithMultipleChild(root.left, set);
        TreeNode right = lcaWithMultipleChild(root.right, set);
        if (left != null && right != null) {
            return root;
        } else if (left != null) {
            return left;
        } else return right;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        return byStoreParent(root, p, q);
        return byRecursive(root, p, q);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
