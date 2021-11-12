package leetcode.editor.cn.q_653;

import leetcode.editor.cn.def.*;

import java.util.*;

//给定一个二叉搜索树 root 和一个目标结果 k，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。 
//
// 
//
// 示例 1： 
//
// 
//输入: root = [5,3,6,2,4,null,7], k = 9
//输出: true
// 
//
// 示例 2： 
//
// 
//输入: root = [5,3,6,2,4,null,7], k = 28
//输出: false
// 
//
// 示例 3： 
//
// 
//输入: root = [2,1,3], k = 4
//输出: true
// 
//
// 示例 4： 
//
// 
//输入: root = [2,1,3], k = 1
//输出: false
// 
//
// 示例 5： 
//
// 
//输入: root = [2,1,3], k = 3
//输出: true
// 
//
// 
//
// 提示: 
//
// 
// 二叉树的节点个数的范围是 [1, 10⁴]. 
// -10⁴ <= Node.val <= 10⁴ 
// root 为二叉搜索树 
// -10⁵ <= k <= 10⁵ 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉搜索树 哈希表 双指针 二叉树 👍 290 👎 0


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
    private int treeSize(TreeNode root) {
        if (root == null) {
            return 0;
        } else if (root.left == null && root.right == null) {
            return 1;
        } else {
            return treeSize(root.left) + treeSize(root.right) + 1;
        }
    }

    private int[] inorderArray(TreeNode root) {
        int size = treeSize(root);
        int[] res = new int[size];
        int index = 0;
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;

        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                // 入栈
                stack.push(node);
                node = node.left;
            } else {
                // 出栈
                node = stack.pop();
                res[index++] = node.val;
                node = node.right;
            }
        }
        return res;
    }

    // 解法1：中序遍历，转为int[]后，相当于排序后的两数之和，同 Q167 且不需去重
    // 时间复杂度：O(n) * 3 ，78.79% beat
    private boolean byInorderAndDoublePtr(TreeNode root, int k) {
        int[] nums = inorderArray(root);
        // 双指针遍历
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] == k) {
                return true;
            } else if (nums[left] + nums[right] > k) {
                right--;
            } else if (nums[left] + nums[right] < k) {
                left++;
            }
        }
        return false;
    }

    // 解法2：通过HashSet直接遍历找 diff ，同Q1
    // 没有用到BST性质
    private boolean byHashMap(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (set.contains(node.val)) {
                    return true;
                }
                set.add(k - node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return false;
    }

    public boolean findTarget(TreeNode root, int k) {
        return byInorderAndDoublePtr(root, k);
//        return byHashMap(root, k);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
