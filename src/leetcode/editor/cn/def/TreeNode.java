package leetcode.editor.cn.def;

import java.util.*;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static void preorderByRecursive(List<Integer> ans, TreeNode node) {
        if (node == null) {
            return;
        }
        ans.add(node.val);
        preorderByRecursive(ans, node.left);
        preorderByRecursive(ans, node.right);
    }

    public static List<Integer> preorderByStack(TreeNode node) {
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                ans.add(node.val);
                // 递归开始，压栈
                stack.push(node);
                node = node.left;
            } else {
                // node 为空，出栈
                node = stack.pop();
                node = node.right;
            }
        }
        return ans;
    }

    public static List<Integer> preorderByMorris(TreeNode node) {
        List<Integer> ans = new ArrayList<>();
        TreeNode pre;
        while (node != null) {
            if (node.left != null) {
                // 左子树的最右子数 的 右子树 连到根节点
                pre = node.left;
                while (pre.right != null && pre.right != node) {
                    pre = pre.right;
                }
                if (pre.right == null) {
                    // 没有连上，则先连上
                    pre.right = node;
                    ans.add(node.val);
                    node = node.left;
                } else {
                    // 已经连上，走完了一圈，解开
                    pre.right = null;
                    node = node.right;
                }
            } else {
                ans.add(node.val);
                node = node.right;
            }
        }
        return ans;
    }

    public static void inorderByRecursive(List<Integer> ans, TreeNode node) {
        if (node == null) {
            return;
        }
        inorderByRecursive(ans, node.left);
        ans.add(node.val);
        inorderByRecursive(ans, node.right);
    }

    public static List<Integer> inorderByStack(TreeNode node) {
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                // 压栈
                stack.push(node);
                node = node.left;
            } else {
                // 出栈
                node = stack.pop();
                ans.add(node.val);
                node = node.right;
            }
        }
        return ans;
    }

    public static List<Integer> inorderByMorris(TreeNode node) {
        List<Integer> ans = new ArrayList<>();
        TreeNode pre;
        while (node != null) {
            if (node.left != null) {
                // 将node连接到左子树最右子树的右子树
                pre = node.left;
                while (pre.right != null && pre.right != node) {
                    pre = pre.right;
                }

                if (pre.right == null) {
                    // 未连接上，则连接
                    pre.right = node;
                    node = node.left;
                } else {
                    // 已连接上，则断开，并意味着走完了左子树
                    pre.right = null;
                    ans.add(node.val);
                    node = node.right;
                }
            } else {
                ans.add(node.val);
                node = node.right;
            }
        }
        return ans;
    }

    public static void postorderByRecursive(List<Integer> ans, TreeNode node) {
        if (node == null) {
            return;
        }
        postorderByRecursive(ans, node.left);
        postorderByRecursive(ans, node.right);
        ans.add(node.val);
    }

    public static List<Integer> postorderByStack(TreeNode node) {
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode pre = null;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                if (node.right == null || node.right == pre) {
                    // 右子树为空，或者重新走到根节点
                    ans.add(node.val);
                    pre = node;
                    node = null;
                } else {
                    // 如果有右子树，根节点重新入栈
                    stack.push(node);
                    node = node.right;
                }
            }
        }
        return ans;
    }

    private static List<Integer> leftChildToPrePath(TreeNode node) {
        List<Integer> path = new ArrayList<>();
        while (node != null) {
            path.add(node.val);
            node = node.right;
        }
        Collections.reverse(path);
        return path;
    }

    public static List<Integer> postorderByMorris(TreeNode node) {
        List<Integer> ans = new ArrayList<>();
        TreeNode dump = new TreeNode();
        dump.left = node;
        TreeNode pre;
        node = dump;
        while (node != null) {
            if (node.left != null) {
                // 将根节点连接到左子树的最右子树的右子树上
                pre = node.left;
                while (pre.right != null && pre.right != node) {
                    pre = pre.right;
                }

                if (pre.right == null) {
                    // 没连上，则连接
                    pre.right = node;
                    node = node.left;
                } else {
                    // 已经连上了，则断开，且走完了左子树
                    pre.right = null;
                    // 此时，反向输出 左孩子 到 前驱节点的一直往右走的路径
                    ans.addAll(leftChildToPrePath(node.left));
                    node = node.right;
                }
            } else {
                node = node.right;
            }
        }
        return ans;
    }

}
