/*
* Class name: TreeNode
*
* Version info: jdk 1.8
*
* Copyright notice:
* 
* Author info: Arpit Garg
*
* Creation date: 17/Mar/2021
*
* Last updated By: Arpit Garg
*
* Last updated Date: 18/Mar/2021
*
* Description: Model class of N child tree, store the data, parent and child information
*/
package com.nagarro.nchildtreeimplementation.model;

import java.util.ArrayList;

public class TreeNode {
    private int data;
    private ArrayList<TreeNode> children;
    private TreeNode parent;

    public TreeNode() {
        this.children = new ArrayList<>();
    }

    /**
     * @param data
     */
    public void setData(int data) {
        this.data = data;
    }

    /**
     * @return data of node
     */
    public int getData() {
        return data;
    }

    /**
     * @param child
     */
    public void addChild(TreeNode child) {
        children.add(child);
    }

    /**
     * @return list of child
     */
    public ArrayList<TreeNode> getChild() {
        return this.children;
    }

    /**
     * @param parent
     */
    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    /**
     * @return parent of a node
     */
    public TreeNode getParent() {
        return parent;
    }
}
