package leetcode.editor.cn.q_1104;

import leetcode.editor.cn.def.*;

import java.util.*;

//在一棵无限的二叉树上，每个节点都有两个子节点，树中的节点 逐行 依次按 “之” 字形进行标记。 
//
// 如下图所示，在奇数行（即，第一行、第三行、第五行……）中，按从左到右的顺序进行标记； 
//
// 而偶数行（即，第二行、第四行、第六行……）中，按从右到左的顺序进行标记。 
//
// 
//
// 给你树上某一个节点的标号 label，请你返回从根节点到该标号为 label 节点的路径，该路径是由途经的节点标号所组成的。 
//
// 
//
// 示例 1： 
//
// 输入：label = 14
//输出：[1,3,4,14]
// 
//
// 示例 2： 
//
// 输入：label = 26
//输出：[1,2,6,10,26]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= label <= 10^6 
// 
// Related Topics 树 数学 二叉树 
// 👍 126 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private final Map<Integer, Integer> powerMap = new HashMap<>();

    private Integer getPower(int n) {
        if (powerMap.containsKey(n)) {
            return powerMap.get(n);
        } else {
            if (n == 0) {
                powerMap.put(0, 1);
                return 1;
            }
            Integer r = getPower(n - 1) * 2;
            powerMap.put(n, r);
            return r;
        }
    }

    // 2n+1 奇数层, 从左往右第k个子树的label
    private Integer getOddLabel(int n, int k) {
        return getPower(2 * n) + k - 1;
    }

    // 2n+1 奇数层，label为从左往右第几个子树
    private Integer getOddK(int n, int label) {
        return label + 1 - getPower(2 * n);
    }

    // 2n 偶数层，从左往右第k个子树的label
    private Integer getEvenLabel(int n, int k) {
        return getPower(2 * n) - k;
    }

    // 2n 偶数层，label为从左往右第几个子树
    private Integer getEvenK(int n, int label) {
        return getPower(2 * n) - label;
    }

    public List<Integer> pathInZigZagTree(int label) {
        LinkedList<Integer> res = new LinkedList<>();
        int n = 0;
        while (getPower(n) <= label) {
            n += 1;
        }
        int currentLabel = label;
        int currentK;

        if (n % 2 == 0) {
            // 处于偶数层第k个
            currentK = getEvenK(n / 2, currentLabel);
        } else {
            currentK = getOddK(n / 2, currentLabel);
        }
        res.addFirst(currentLabel);
        n = n - 1;

        while (n > 0) {
            // 当前处于从左往右第currentK个，取决于下一层的k
            currentK = currentK / 2 + ((currentK % 2 == 0) ? 0 : 1);
            if (n % 2 == 0) {
                currentLabel = getEvenLabel(n / 2, currentK);
            } else {
                currentLabel = getOddLabel(n / 2, currentK);
            }
            res.addFirst(currentLabel);
            n = n - 1;
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
