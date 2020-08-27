package com.ytz.leetcode.tree;

import com.ytz.leetcode.node.TreeNode;

/**
 * @ClassName: FindNextTreeNode
 * @Description:
 *           TODO 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。如果指定节点没有对应的“下一个”节点，则返回null。
 *              条件：
 *              1、如果p 大于等于 root，则在右子树上
 *              2、如果p 小于 root，则在左子树或者自己本身
 *                   递归调用 leftTreeNode，如果是空的，说明当前节点就是答案
 *                   如果不是空的，则说明在 leftTreeNode 已经找到合适的答案，直接返回
 * @author: yangtz
 * @date: 2020/8/25
 * @Version: V1.0
 */
public class FindNextTreeNode {

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }

        // 如果当前节点的值大于等于root节点的值，则继续查找右子树，否则， 查找左子树
        if (p.getValue() >= root.getValue()) {
            return inorderSuccessor(root.getRightTreeNode(), p);
        } else {
            TreeNode leftTreeNode = inorderSuccessor(root.getLeftTreeNode(), p);
            // 遍历查找左子树，如果返回不等于空，则直接返回结果，否则，直接返回root本身
            if (leftTreeNode != null) {
                return leftTreeNode;
            } else {
                return root;
            }
        }
    }
}
