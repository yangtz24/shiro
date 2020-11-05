package com.ytz.leetcode.tree;

import com.ytz.leetcode.node.TreeNode;

import java.util.*;

/**
 * @ClassName: LevelOrderTree
 * @Description: TODO 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
 * 从上至下 打印（即按层打印），又称为二叉树的 广度优先搜索（BFS）。
 * 例如：
 * 【3,9,2,null,null,1,7】---->[3,9,2,1,7]
 * 根据队列的先进先出规则实现
 * @author: yangtz
 * @date: 2020/8/27
 * @Version: V1.0
 */
public class LevelOrderTree {

    /**
     * BFS广度优先搜索1
     *
     * @param root
     * @return
     */
    public List<Integer> levelOrder1(TreeNode root) {
        // 如果为空，则返回空列表
        if (root == null) {
            return new ArrayList<>();
        }
        // 结果
        List<Integer> results = new ArrayList<>();
        // 定义队列，将当前根节点加入队列
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // 当队列为空时，跳出，遍历完毕
        while (!queue.isEmpty()) {
            // 获取当前节点
            TreeNode node = queue.poll();
            // 将当前节点的值加入结果中
            results.add(node.getValue());
            // 如果当前节点有左孩子，则加入队列
            if (node.getLeftTreeNode() != null) {
                queue.add(node.getLeftTreeNode());
            }
            // 如果当前节点有右孩子，则加入队列
            if (node.getRightTreeNode() != null) {
                queue.add(node.getRightTreeNode());
            }
        }
        return results;
    }

    /**
     * BFS广度优先搜索，每一层打印到一行
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        List<List<Integer>> results = new ArrayList<>();
        while (!queue.isEmpty()) {
            List<Integer> res = new ArrayList<>();
            int length = queue.size();
            for (int i = 0; i < length; i++) {
                TreeNode node = queue.poll();
                res.add(node.getValue());
                if (node.getLeftTreeNode() != null) {
                    queue.add(node.getLeftTreeNode());
                }
                if (node.getRightTreeNode() != null) {
                    queue.add(node.getRightTreeNode());
                }
            }
            results.add(res);
        }
        return results;
    }

}
