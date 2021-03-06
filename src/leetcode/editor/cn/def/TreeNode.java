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

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    public static TreeNode constructByIntegerList(List<Integer> nums) {
        if (nums.isEmpty() || nums.get(0) == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(nums.get(0));
        queue.add(root);
        int idx = 0;
        while (!queue.isEmpty() && idx < nums.size()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                idx++;
                if(idx >= nums.size()) {
                    break;
                }
                Integer leftVal = nums.get(idx);
                if (leftVal != null) {
                    node.left = new TreeNode(leftVal);
                    queue.add(node.left);
                }
                idx++;
                if(idx >= nums.size()) {
                    break;
                }
                Integer rightVal = nums.get(idx);
                if (rightVal != null) {
                    node.right = new TreeNode(rightVal);
                    queue.add(node.right);
                }
            }
        }
        return root;
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
                // ?????????????????????
                stack.push(node);
                node = node.left;
            } else {
                // node ???????????????
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
                // ???????????????????????? ??? ????????? ???????????????
                pre = node.left;
                while (pre.right != null && pre.right != node) {
                    pre = pre.right;
                }
                if (pre.right == null) {
                    // ???????????????????????????
                    pre.right = node;
                    ans.add(node.val);
                    node = node.left;
                } else {
                    // ???????????????????????????????????????
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
                // ??????
                stack.push(node);
                node = node.left;
            } else {
                // ??????
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
                // ???node??????????????????????????????????????????
                pre = node.left;
                while (pre.right != null && pre.right != node) {
                    pre = pre.right;
                }

                if (pre.right == null) {
                    // ????????????????????????
                    pre.right = node;
                    node = node.left;
                } else {
                    // ?????????????????????????????????????????????????????????
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
                    // ?????????????????????????????????????????????
                    ans.add(node.val);
                    pre = node;
                    node = null;
                } else {
                    // ??????????????????????????????????????????
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
                // ????????????????????????????????????????????????????????????
                pre = node.left;
                while (pre.right != null && pre.right != node) {
                    pre = pre.right;
                }

                if (pre.right == null) {
                    // ?????????????????????
                    pre.right = node;
                    node = node.left;
                } else {
                    // ???????????????????????????????????????????????????
                    pre.right = null;
                    // ????????????????????? ????????? ??? ???????????????????????????????????????
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
