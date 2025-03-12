package com.rplbo.guided6;

import java.util.Stack;
public class IntToPosGuided {
    private int CekDerajatOp(char ch)
    {
        switch (ch)
        {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    public String infixToPostfix(String exp)
    {
        StringBuilder result = new StringBuilder(); // To store the postfix expression
        Stack<Character> stack = new Stack<>();  // Stack for operators

        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);

            // If the character is an operand (letters or digits), add it to result
            if (Character.isLetterOrDigit(ch)) {
                result.append(ch);
            }
            // If the character is '(', push it to the stack
            else if (ch == '(') {
                stack.push(ch);
            }
            // If the character is ')', pop from the stack until '(' is found
            else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                stack.pop();  // Pop the '(' from the stack
            }
            // If the character is an operator
            else {
                // Pop operators from the stack while they have higher or equal precedence
                while (!stack.isEmpty() && CekDerajatOp(ch) <= CekDerajatOp(stack.peek())) {
                    result.append(stack.pop());
                }
                // Push the current operator to the stack
                stack.push(ch);
            }
        }

        // Pop all remaining operators from the stack
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }
}
