package com.ytz.leetcode.tree;

import com.ytz.leetcode.node.TreeNode;

/**
 * @ClassName: CheckValidBSTree
 * @Description:
 *              TODO  实现一个函数，检查一棵二叉树是否为二叉搜索树。
 *                  条件：
 *                  1、所有左子树节点的值均小于根节点
 *                  2、所有右子树节点的值均大于根节点
 *                  3、中序遍历节点的值呈递增趋势
 * @author: yangtz
 * @date: 2020/8/25
 * @Version: V1.0
 */
public class CheckValidBSTree {

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        // 获取左子树
        TreeNode leftTreeNode = root.getLeftTreeNode();
        // 获取右子树
        TreeNode rightTreeNode = root.getRightTreeNode();

        // 找到左子树最大的节点
        while (leftTreeNode != null && leftTreeNode.getRightTreeNode() != null) {
            leftTreeNode = leftTreeNode.getRightTreeNode();
        }
        // 找到右子树最小的节点
        while (rightTreeNode != null && rightTreeNode.getLeftTreeNode() != null) {
            rightTreeNode = rightTreeNode.getLeftTreeNode();
        }
        // 左子树最大节点 < 根节点   右子树最小节点 > 根节点
        boolean result = (leftTreeNode == null || leftTreeNode.getValue() < root.getValue()) && (rightTreeNode == null || rightTreeNode.getValue() > root.getValue());
        // 递归判断左右子树
        return result && isValidBST(root.getLeftTreeNode()) && isValidBST(root.getRightTreeNode());

    }
}
