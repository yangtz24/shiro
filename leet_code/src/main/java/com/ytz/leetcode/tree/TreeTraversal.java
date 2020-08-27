package com.ytz.leetcode.tree;

import com.ytz.leetcode.node.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName: TreeTraversal
 * @Description:
 *              TODO 给定一个 N 叉树，返回其节点值的前序遍历。
 *                  条件：
 *                     1、先序遍历：根->左子树->右子树
 *                     2、中序遍历：左子树->根->右子树
 *                     3、后序遍历：左子树->右子树->根
 *                     4、递归执行
 * @author: yangtz
 * @date: 2020/8/26
 * @Version: V1.0
 */
public class TreeTraversal {

    /**
     * 先序遍历结果
     */
    List<Integer> preOrders = new ArrayList<>();
    /**
     * 中序遍历结果
     */
    List<Integer> inOrders = new ArrayList<>();
    /**
     * 后序遍历结果
     */
    List<Integer> postOrders = new ArrayList<>();
    /**
     * 层级遍历结果
     */
    List<Integer> cengOrders = new ArrayList<>();

    /**
     * 先序遍历
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        handlePreorder(root);
        return inOrders;
    }

    /**
     * 先序遍历
     * @param root
     */
    public void handlePreorder(TreeNode root) {
        if (root == null) {
            return;
        }
        // 将根节点的数据添加到结果集中
        preOrders.add(root.getValue());
        // 遍历所有孩子
        for (int i = 0; i < root.getChildren().size(); i++) {
            // 递归遍历
            handlePreorder(root.getChildren().get(i));
        }
    }

    /**
     * 中序遍历
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        handleInorder(root);
        return inOrders;
    }

    /**
     * 中序遍历
     * @param root
     */
    public void handleInorder(TreeNode root) {
        if (root == null) {
            return;
        }

        handleInorder(root.getLeftTreeNode());
        inOrders.add(root.getValue());
        handleInorder(root.getRightTreeNode());
    }

    /**
     * 后序遍历
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        handlePostorder(root);
        return postOrders;
    }

    /**
     * 后序遍历
     * @param root
     */
    public void handlePostorder(TreeNode root) {
        if (root == null) {
            return;
        }

        handlePostorder(root.getLeftTreeNode());
        handlePostorder(root.getRightTreeNode());
        postOrders.add(root.getValue());
    }

    public List<Integer> cengTraversal(TreeNode root) {
        handleCengOrder(root);
        return cengOrders;
    }

    public void handleCengOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            cengOrders.add(node.getValue());
            if (node.getLeftTreeNode() != null) {
                queue.add(node.getLeftTreeNode());
            }
            if (node.getRightTreeNode() != null) {
                queue.add(node.getRightTreeNode());
            }
        }
    }

    public static void main(String[] args) {
        TreeTraversal treeTraversal = new TreeTraversal();
        //以数组形式生成一棵完全二叉树
        TreeNode[] node = new TreeNode[10];
        for(int i = 0; i < 10; i++)
        {
            node[i] = new TreeNode(i);
        }
        for(int i = 0; i < 10; i++)
        {
            if(i * 2 + 1 < 10) {
                node[i].setLeftTreeNode(node[i*2+1]);
            }
            if(i * 2 + 2 < 10) {
                node[i].setRightTreeNode(node[i*2+2]);
            }
        }

//        for (TreeNode treeNode : node) {
//            System.out.println(treeNode.getValue());
//        }
        List<Integer> list = treeTraversal.cengTraversal(node[0]);
        list.forEach(System.out::println);
    }
}
