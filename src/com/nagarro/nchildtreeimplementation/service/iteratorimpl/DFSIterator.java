/*
* Class name: DFSIterator
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
* Description: DFS Iterator class for tree
*/
package com.nagarro.nchildtreeimplementation.service.iteratorimpl;

import java.util.Iterator;
import java.util.Stack;

import com.nagarro.nchildtreeimplementation.model.TreeNode;

public class DFSIterator implements Iterator<Object> {
    private int size;
    private int current;
    private Stack<TreeNode> stack;

    public DFSIterator(TreeNode root, int size) {
        current = 0;
        this.size = size;
        this.stack = new Stack<>();
        stack.push(root);
    }

    @Override
    public boolean hasNext() {
        return current < size;
    }

    @Override
    public Object next() {
        if (!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            current++;
            for (TreeNode child : temp.getChild()) {
                stack.push(child);
            }
            return temp.getData();
        }
        return null;
    }

}
