package leetcode.editor.cn.q_449;

import leetcode.editor.cn.def.*;

import java.util.*;

//<p>序列化是将数据结构或对象转换为一系列位的过程，以便它可以存储在文件或内存缓冲区中，或通过网络连接链路传输，以便稍后在同一个或另一个计算机环境中重建。</p>
//
//<p>设计一个算法来序列化和反序列化<strong> 二叉搜索树</strong> 。 对序列化/反序列化算法的工作方式没有限制。 您只需确保二叉搜索树可以序列化为字符串，并且可以将该字符串反序列化为最初的二叉搜索树。</p>
//
//<p><strong>编码的字符串应尽可能紧凑。</strong></p>
//
//<p> </p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>root = [2,1,3]
//<strong>输出：</strong>[2,1,3]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>root = []
//<strong>输出：</strong>[]
//</pre>
//
//<p> </p>
//
//<p><strong>提示：</strong></p>
//
//<ul>
//	<li>树中节点数范围是 <code>[0, 10<sup>4</sup>]</code></li>
//	<li><code>0 <= Node.val <= 10<sup>4</sup></code></li>
//	<li>题目数据 <strong>保证</strong> 输入的树是一棵二叉搜索树。</li>
//</ul>
//
//<p> </p>
//
//<p><strong>注意</strong>：不要使用类成员/全局/静态变量来存储状态。 你的序列化和反序列化算法应该是无状态的。</p>
//<div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>广度优先搜索</li><li>设计</li><li>二叉搜索树</li><li>字符串</li><li>二叉树</li></div></div><br><div><li>👍 223</li><li>👎 0</li></div>

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
class Codec {

    private StringBuilder serializeHelper(TreeNode node) {
        StringBuilder builder = new StringBuilder();
        if (node == null) {
            return builder;
        }
        builder.append(node.val);
        if (node.left != null) {
            builder.append(",");
            builder.append(serializeHelper(node.left));
        }
        if (node.right != null) {
            builder.append(",");
            builder.append(serializeHelper(node.right));
        }
        return builder;
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return serializeHelper(root).toString();
    }

    private TreeNode deserializeHelper(Queue<String> queue, int lower, int upper) {
        if (queue.isEmpty()) {
            return null;
        }
        String s = queue.peek();
        int val = Integer.parseInt(s);
        if (val < lower || val > upper) {
            return null;
        }
        queue.poll();
        TreeNode root = new TreeNode(val);
        root.left = deserializeHelper(queue, lower, val);
        root.right = deserializeHelper(queue, val, upper);
        return root;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserializeHelper(queue, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// String tree = ser.serialize(root);
// TreeNode ans = deser.deserialize(tree);
// return ans;
//leetcode submit region end(Prohibit modification and deletion)
