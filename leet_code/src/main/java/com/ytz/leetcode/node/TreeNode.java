package com.ytz.leetcode.node;

import lombok.*;

/**
 * @ClassName: TreeNode
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/8/25
 * @Version: V1.0
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TreeNode {

    // 节点的值
    private int value;
    // 当前节点
    private TreeNode node;
    // 左子树
    private TreeNode leftTreeNode;
    // 右子树
    private TreeNode rightTreeNode;

    public TreeNode(int val) {
        this.value = val;
    }

}
