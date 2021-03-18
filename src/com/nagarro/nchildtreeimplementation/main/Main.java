/*
* Class name: Main
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
* Description: Main class of N-Child tree
*/
package com.nagarro.nchildtreeimplementation.main;

import java.util.Scanner;

import com.nagarro.nchildtreeimplementation.exception.InvalidStateException;
import com.nagarro.nchildtreeimplementation.service.NChildTree;
import com.nagarro.nchildtreeimplementation.utils.InputValidationUtil;

public class Main {
    private static final String VALUE_TO_REMOVE = "Value to remove";
    private static final String _2_REMOVE = "2- Remove";
    private static final String WRONG_CHOICE = "Wrong Choice!";
    private static final String _5_DISPLAY_TREE = "5- Display Tree";
    private static final String ENTER_VALUE_TO_BE_SEARCH = "Enter value to be search ";
    private static final String WELCOME = "Welcome!...";
    private static final String _4_SIZE = "4- Size";
    private static final String _9_TRAVERSE_THROUGH_DFS = "9- Traverse through DFS";
    private static final String _8_TRAVERSE_THROUGH_BFS = "8- Traverse through BFS";
    private static final String _7_ITERATOR_DEPTH_FIRST = "7- Iterator Depth First";
    private static final String _6_ITERATOR_BREADTH_FIRST = "6- Iterator Breadth First";
    private static final String _3_CONTAINS = "3- Contains";
    private static final String _1_INSERT = "1- Insert";
    private static Scanner scan = new Scanner(System.in);
    private static final String PRESS_Y_FOR_CONTINUE = "Press 'y' for continue...";
    private static final String SELECT_OPERATION_TO_BE_PERFORM = "Select Operation to be perform...";

    /**
     * @param args
     */
    public static void main(String[] args) {
        char ch;
        NChildTree tree = new NChildTree();
        System.out.println(WELCOME);
        do {
            System.out.println(_1_INSERT);
            System.out.println(_2_REMOVE);
            System.out.println(_3_CONTAINS);
            System.out.println(_4_SIZE);
            System.out.println(_5_DISPLAY_TREE);
            System.out.println(_6_ITERATOR_BREADTH_FIRST);
            System.out.println(_7_ITERATOR_DEPTH_FIRST);
            System.out.println(_8_TRAVERSE_THROUGH_BFS);
            System.out.println(_9_TRAVERSE_THROUGH_DFS);
            System.out.println(SELECT_OPERATION_TO_BE_PERFORM);
            String choiceForOperation = scan.next();
            int choice = new InputValidationUtil().inputValidation(choiceForOperation);
            choiceAction(choice, tree);
            System.out.println(PRESS_Y_FOR_CONTINUE);
            ch = scan.next().charAt(0);
        } while (ch == 'y' || ch == 'Y');
    }

    /**
     * provide different operations for user
     * 
     * @param choice
     * @param tree
     */
    private static void choiceAction(int choice, NChildTree tree) {
        switch (choice) {
        case 1:
            tree.insert(tree.getRoot(), -1);
            break;

        case 2:
            System.out.println(VALUE_TO_REMOVE);
            String valueToRemove = scan.next();
            int removeValue = new InputValidationUtil().inputValidation(valueToRemove);
            tree.remove(removeValue);
            break;

        case 3:
            System.out.println(ENTER_VALUE_TO_BE_SEARCH);
            String value = scan.next();
            int valueToBeSearch = new InputValidationUtil().inputValidation(value);
            System.out.println(tree.contains(valueToBeSearch));
            break;

        case 4:
            System.out.println(tree.size());
            break;

        case 5:
            tree.display(tree.getRoot());
            break;

        case 6:
            tree.customIteratorBFS(tree);
            break;

        case 7:
            tree.customIteratorDFS(tree);
            break;

        case 8:
            tree.traverseThroughBFS(tree.getRoot());
            break;

        case 9:
            tree.traverseThroughDFS(tree.getRoot());
            break;

        default:
            new InvalidStateException(WRONG_CHOICE);
            break;
        }
    }
}
