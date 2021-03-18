/*
* Class name: BFSIterator
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
* Description: BFS Iterator class for tree
*/
package com.nagarro.nchildtreeimplementation.service.iteratorimpl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import com.nagarro.nchildtreeimplementation.model.TreeNode;

public class BFSIterator implements Iterator<Object> {
    private int size;
    private int current;
    private Queue<TreeNode> queue;

    public BFSIterator(TreeNode root, int size) {
        current = 0;
        this.size = size;
        this.queue = new LinkedList<>();
        queue.add(root);
    }

    @Override
    public boolean hasNext() {
        return current < size;
    }

    @Override
    public Object next() {
        if (!queue.isEmpty()) {
            TreeNode temp = queue.remove();
            current++;
            for (TreeNode child : temp.getChild()) {
                queue.add(child);
            }
            return temp.getData();
        }
        return null;
    }

}
