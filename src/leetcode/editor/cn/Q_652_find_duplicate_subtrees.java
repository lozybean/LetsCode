package leetcode.editor.cn.q_652;

import leetcode.editor.cn.def.*;

import java.util.*;

//给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。 
//
// 两棵树重复是指它们具有相同的结构以及相同的结点值。 
//
// 示例 1： 
//
//         1
//       / \
//      2   3
//     /   / \
//    4   2   4
//       /
//      4
// 
//
// 下面是两个重复的子树： 
//
//       2
//     /
//    4
// 
//
// 和 
//
//     4
// 
//
// 因此，你需要以列表的形式返回上述重复子树的根结点。 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 325 👎 0


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
    Map<String, Integer> freqMap = new HashMap<>();
    List<TreeNode> ans = new ArrayList<>();

    // 23ms 36.27beat
    // 字符串串联太耗时了
    private String dfsByString(TreeNode node) {
        if (node == null) {
            return "#";
        }
        // node.val 必须在两边，前序或者后序一致；中序序列化不能唯一表示
        String serial = node.val + "," + dfsByString(node.left) + "," + dfsByString(node.right);
        int freq = freqMap.getOrDefault(serial, 0);
        if (freq == 1) {
            ans.add(node);
        }
        freqMap.put(serial, freq + 1);
        return serial;
    }

    // 10ms 99.26% beat
    private StringBuilder dfsByStringBuilder(TreeNode node) {
        if (node == null) {
            return new StringBuilder();
        }
        StringBuilder builder = new StringBuilder();
        builder.append(node.val);
        builder.append(",");
        builder.append(dfsByStringBuilder(node.left));
        builder.append(",");
        builder.append(dfsByStringBuilder(node.right));

        String serial = builder.toString();
        int freq = freqMap.getOrDefault(serial, 0);
        if (freq == 1) {
            ans.add(node);
        }
        freqMap.put(serial, freq + 1);
        return builder;

    }

    int uid = 1;
    Map<String, Integer> uidMap = new HashMap<>();
    Map<Integer, Integer> freqMap2 = new HashMap<>();

    // 唯一编码发射器
    private int getUid(String serial) {
//        if (!uidMap.containsKey(serial)) {
//            uidMap.put(serial, uid++);
//        }
//        return uidMap.get(serial);
        return uidMap.computeIfAbsent(serial, k -> uid++);
    }

    // 12ms 98.72 beat
    private int dfsByInt(TreeNode node) {
        if (node == null) {
            return 0;
        }
        String serial = node.val + "," + dfsByInt(node.left) + "," + dfsByInt(node.right);
        int id = getUid(serial);
        int freq = freqMap2.getOrDefault(id, 0);
        if (freq == 1) {
            ans.add(node);
        }
        freqMap2.put(id, freq + 1);
        return id;
    }

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
//        dfsByString(root);
        dfsByStringBuilder(root);
//        dfsByInt(root);
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
