/*
* Class name: InputValidationUtil
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
* Last updated Date: 17/Mar/2021
*
* Description: Validate the user eneterd input
*/
package com.nagarro.nchildtreeimplementation.utils;

import java.util.Scanner;

public class InputValidationUtil {

    private static final String INVALID_INPUT = "Invalid input !";
    private static final String WRONG_VALUE_RE_ENTER_VALUE = "Wrong value! Re-enter value ";
    private Scanner scan = new Scanner(System.in);

    /**
     * Validate the user input
     * 
     * @param inputValue
     * @return
     */
    public int inputValidation(String inputValue) {
        boolean flag = true;
        int value = 0;
        while (flag) {
            try {
                try {
                    value = Integer.parseInt(inputValue);
                } catch (Exception e) {
                    throw new IllegalStateException(INVALID_INPUT);
                }
                flag = false;
            } catch (IllegalStateException e) {
                System.out.println(WRONG_VALUE_RE_ENTER_VALUE);
                inputValue = scan.next();
                flag = true;
            }
        }
        return value;
    }

}
