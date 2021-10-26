package leetcode.editor.cn.def;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public String toString() {
        ListNode node = this;
        StringBuilder res = new StringBuilder();
        while (node != null) {
            res.append(node.val);

            if (node.next != null) {
                res.append("-->");
            }
            node = node.next;
        }
        return res.toString();
    }
}

