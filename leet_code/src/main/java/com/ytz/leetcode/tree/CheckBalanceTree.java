package com.ytz.leetcode.tree;

import com.ytz.leetcode.node.TreeNode;

/**
 * @ClassName: CheckBalance
 * @Description: TODO 实现一个函数，检查二叉树是否平衡。在这个问题中，平衡树的定义如下：任意一个节点，其两棵子树的高度差不超过 1。
 * 条件：
 * 1、左右子树高度差小于 1
 * @author: yangtz
 * @date: 2020/8/25
 * @Version: V1.0
 */
public class CheckBalanceTree {

    public boolean isBalanced(TreeNode root) {
        // 树为空，递归终止条件
        if (root == null) {
            return true;
        }

        // 左子树高度
//        int leftHight = getTreeHegiht(root.getLeftTreeNode());
        // 右子树高度
//        int rightHight = getTreeHegiht(root.getRightTreeNode());
        // 左右子树高度差大于 1 不平衡
//        if (Math.abs(leftHight - rightHight) > 1) {
//            return false;
//        }

        // 平衡则继续递归检验
//        return isBalanced(root.getLeftTreeNode()) && isBalanced(root.getRightTreeNode());
        return Math.abs(getTreeHegiht(root.getLeftTreeNode()) - getTreeHegiht(root.getRightTreeNode())) <= 1 && isBalanced(root.getLeftTreeNode()) && isBalanced(root.getRightTreeNode());


    }

    /**
     * 获取树的高度
     *
     * @param root 树的根节点
     * @return
     */
    public int getTreeHegiht(TreeNode root) {
        // 树为空 则树的高度为0，递归终止条件
        if (root == null) {
            return 0;
        }
        // 当前树高度 = 左右子树最高的子树 + 1
        return Math.max(getTreeHegiht(root.getLeftTreeNode()), getTreeHegiht(root.getRightTreeNode())) + 1;
    }
}
