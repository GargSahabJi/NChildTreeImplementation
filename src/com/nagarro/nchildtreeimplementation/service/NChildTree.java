/*
* Class name: NChildTree
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
* Description: tree class for creating and do operations
*/
package com.nagarro.nchildtreeimplementation.service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import com.nagarro.nchildtreeimplementation.exception.InvalidStateException;
import com.nagarro.nchildtreeimplementation.model.TreeNode;
import com.nagarro.nchildtreeimplementation.service.iteratorimpl.BFSIterator;
import com.nagarro.nchildtreeimplementation.service.iteratorimpl.DFSIterator;
import com.nagarro.nchildtreeimplementation.utils.InputValidationUtil;

public class NChildTree implements Iterable<Object> {
    private static final String NOT_FIND = "Not find";
    private static final String TREE_IS_EMPTY = "Tree is empty!";
    private static final String ENTER_THE_NUMBER_OF_CHILD_OF = "Enter the number of child of ";
    private static final String TH_CHILD_OF = "th Child of ";
    private static final String ENTER_THE_DATA_FOR = "Enter the data for ";
    private static final String ENTER_THE_DATA_OF_ROOT = "Enter the Data of root ";
    private static Scanner scan = new Scanner(System.in);
    private TreeNode root;
    private int sizeOfTree;

    public NChildTree() {
        sizeOfTree = 0;
        root = null;
    }

    /**
     * @return root of the tree
     */
    public TreeNode getRoot() {
        return root;
    }

    /**
     * Insert the node in the tree
     * 
     * @param parent
     * @param ithchild
     * @return
     */
    public TreeNode insert(TreeNode parent, int ithchild) {
        if (parent == null) {
            System.out.println(ENTER_THE_DATA_OF_ROOT);
        } else {
            System.out.println(ENTER_THE_DATA_FOR + ithchild + TH_CHILD_OF + parent.getData());
        }
        String valueForNode = scan.next();
        int value = new InputValidationUtil().inputValidation(valueForNode);
        sizeOfTree++;
        TreeNode node = new TreeNode();
        node.setData(value);
        if (parent == null) {
            root = node;
        }
        node.setParent(parent);
        System.out.println(ENTER_THE_NUMBER_OF_CHILD_OF + node.getData());
        String childNumber = scan.next();
        int numberOfChild = new InputValidationUtil().inputValidation(childNumber);
        for (int i = 0; i < numberOfChild; i++) {
            TreeNode child = insert(node, i);
            node.addChild(child);
        }
        return node;
    }

    /**
     * @param valueToBeSearch
     * @return value is present in the tree or not
     */
    public boolean contains(int valueToBeSearch) {
        boolean containValue = false;
        if (isEmpty()) {
            return containValue;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode temp = queue.remove();
            if (temp.getData() == valueToBeSearch) {
                containValue = true;
                break;
            }
            for (TreeNode child : temp.getChild()) {
                queue.add(child);
            }
        }
        return containValue;
    }

    /**
     * @return size of tree
     */
    public int size() {
        if (sizeOfTree == 0) {
            new InvalidStateException(TREE_IS_EMPTY);
        }
        return sizeOfTree;
    }

    /**
     * @return tree is Empty or not
     */
    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * display the information of tree
     * 
     * @param node
     */
    public void display(TreeNode node) {
        if (isEmpty()) {
            return;
        } else {
            String str = node.getData() + "=> ";
            for (TreeNode child : node.getChild()) {
                str += child.getData() + " ";
            }
            str += "End";
            System.out.println(str);
            for (TreeNode child : node.getChild()) {
                display(child);
            }
        }
    }

    /**
     * Remove the value in the tree
     * 
     * @param value
     */
    public void remove(int value) {
        if (isEmpty()) {
            new InvalidStateException("First Insert Value in tree");
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode temp = null;
        queue.add(root);
        while (!queue.isEmpty()) {
            temp = queue.remove();
            if (temp.getData() == value) {
                deleteNode(temp);
                break;
            }
            for (TreeNode child : temp.getChild()) {
                queue.add(child);
            }
        }
        if (temp == null)
            System.out.println(NOT_FIND);
    }

    /**
     * Delete a value that is not a root node
     * 
     * @param nodeToDelete
     */
    public void deleteNode(TreeNode nodeToDelete) {
        if (nodeToDelete.getParent() != null) {
            int index = nodeToDelete.getParent().getChild().indexOf(nodeToDelete);
            nodeToDelete.getParent().getChild().remove(nodeToDelete);
            for (TreeNode each : nodeToDelete.getChild()) {
                each.setParent(nodeToDelete.getParent());
            }
            nodeToDelete.getParent().getChild().addAll(index, nodeToDelete.getChild());
            sizeOfTree--;
        } else {
            deleteRootNode(nodeToDelete);
        }
        nodeToDelete.getChild().clear();
    }

    /**
     * Delete a root node
     * 
     * @param nodeToDelete
     */
    public void deleteRootNode(TreeNode nodeToDelete) {
        if (nodeToDelete.getParent() != null) {
            new InvalidStateException("deleteRootNode not called on root");
        }
        TreeNode newParent = null;
        if (!nodeToDelete.getChild().isEmpty()) {
            newParent = nodeToDelete.getChild().get(0);
            newParent.setParent(null);
            nodeToDelete.getChild().remove(0);
            for (TreeNode each : nodeToDelete.getChild()) {
                each.setParent(newParent);
            }
            newParent.getChild().addAll(nodeToDelete.getChild());
        }
        nodeToDelete.getChild().clear();
        sizeOfTree--;
        root = newParent;
    }

    /**
     * BFS traversal
     * 
     * @param root
     */
    public void traverseThroughBFS(TreeNode root) {
        if (root == null) {
            new InvalidStateException(TREE_IS_EMPTY);
            return;
        }
        Queue<TreeNode> queueOfTreeNodes = new LinkedList<>();
        queueOfTreeNodes.add(root);
        while (!queueOfTreeNodes.isEmpty()) {
            TreeNode temp = queueOfTreeNodes.remove();
            System.out.print(temp.getData() + " ");
            for (TreeNode child : temp.getChild()) {
                queueOfTreeNodes.add(child);
            }
        }
        System.out.println("");
    }

    /**
     * DFS traversal
     * 
     * @param root
     */
    public void traverseThroughDFS(TreeNode root) {
        if (root == null) {
            new InvalidStateException(TREE_IS_EMPTY);
            return;
        }
        Stack<TreeNode> stackOfTreeNodes = new Stack<>();
        stackOfTreeNodes.push(root);
        while (!stackOfTreeNodes.isEmpty()) {
            TreeNode temp = stackOfTreeNodes.pop();
            System.out.print(temp.getData() + " ");
            for (TreeNode child : temp.getChild()) {
                stackOfTreeNodes.push(child);
            }
        }
        System.out.println("");
    }

    /**
     * BFS Iterator
     * 
     * @param tree
     */
    public void customIteratorBFS(NChildTree tree) {
        if (tree.isEmpty()) {
            return;
        }
        Iterator<Object> itr = tree.bfsIterator();
        while (itr.hasNext()) {
            System.out.print(itr.next() + " ");
        }
        System.out.println("");
    }

    private Iterator<Object> bfsIterator() {
        return new BFSIterator(root, sizeOfTree);
    }

    /**
     * DFS Iterator
     * 
     * @param tree
     */
    public void customIteratorDFS(NChildTree tree) {
        if (tree.isEmpty()) {
            return;
        }
        Iterator<Object> itr = tree.dfsIterator();
        while (itr.hasNext()) {
            System.out.print(itr.next() + " ");
        }
        System.out.println("");
    }

    private Iterator<Object> dfsIterator() {
        return new DFSIterator(root, sizeOfTree);
    }

    @Override
    public Iterator<Object> iterator() {
        return new BFSIterator(root, sizeOfTree);
    }
}
