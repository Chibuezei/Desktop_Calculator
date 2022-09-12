package calculator;

import java.text.DecimalFormat;
import java.util.ArrayDeque;
import java.util.Deque;

public class SolveExpression {
    static int precedence(char ch) {
        return switch (ch) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            case '^', '√' -> 3;
            default -> -1;
        };
    }

    static String infixToPostfix(String exp) {
        exp = converter(exp);
        // initializing empty String for result
        StringBuilder result = new StringBuilder();

        // initializing empty stack
        Deque<Character> stack
                = new ArrayDeque<>();

        for (int i = 0; i < exp.length(); ++i) {
            char c = exp.charAt(i);

            // If the scanned character is an
            // operand, add it to output.
            if (Character.isLetterOrDigit(c) || c == '.')
                result.append(c);

                // If the scanned character is an '(',
                // push it to the stack.
            else if (c == '(')
                stack.push(c);

                // If the scanned character is an ')',
                // pop and output from the stack
                // until an '(' is encountered.
            else if (c == ')') {
                while (!stack.isEmpty()
                        && stack.peek() != '(') {
                    result.append(stack.peek());
                    stack.pop();
                }

                stack.pop();
            } else // an operator is encountered
            {
                result.append(' ');
                while (!stack.isEmpty()
                        && precedence(c) <= precedence(stack.peek())) {

                    result.append(stack.peek());
                    stack.pop();
                }
                stack.push(c);
            }
        }

        // pop all the operators from the stack
        while (!stack.isEmpty()) {
            if (stack.peek() == '(')
                return "Invalid Expression";
            result.append(stack.peek());
            stack.pop();
        }
        return result.toString();
    }

    static String evaluatePostfix(String exp) {
        //create a stack
        Deque<Double> stack
                = new ArrayDeque<>();

        // Scan all characters one by one
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            if (c == ' ')
                continue;

                // If the scanned character is an operand
                // (number here),extract the number
                // Push it to the stack.
            else if (Character.isDigit(c)) {
                double n = 0.0;
                boolean hasDecimal = false;
                int decimalCount = 1;
                //extract the characters and store it in num
                //also check if there is a decimal sign next to the number
                while (Character.isDigit(c) || c == '.') {
                    //if there is a decimal sign next to the number, update the boolean and move to the next char
                    if (c == '.') {
                        hasDecimal = true;
                        i++;
                        c = exp.charAt(i);
                        continue;
                    }
                    //this applies to all characters following a decimal number
                    //divide the decimal character with the product of 10 and the character's position after the decimal point
                    //add to n
                    if (hasDecimal) {
                        double y = (c - '0') / (Math.pow(10.0, decimalCount));
                        n = n + y;
                        decimalCount++;
                        //if not decimal, add the product n and 10 to the character.
                    } else {
                        n = n * 10 + (c - '0');
                    }
                    i++;
                    c = exp.charAt(i);

                }
                i--;

                //push the number in stack
                stack.push(n);
            }

            // If the scanned character is an operator, pop two
            // elements from stack apply the operator
            else {
                double val1 = stack.pop();
                double val2 = c == '√' ? 1 : stack.pop();//this should have been stack.pop(), but modified to carter for √(4) where stack length is 1;

                switch (c) {
                    case '+' -> stack.push(val2 + val1);
                    case '-' -> stack.push(val2 - val1);
                    case '/' -> stack.push(val2 / val1);
                    case '*' -> stack.push(val2 * val1);
                    case '√' -> stack.push(Math.sqrt(val1));
                    case '^' -> stack.push(Math.pow(val2, val1));
                }
            }
        }
        DecimalFormat df = new DecimalFormat("###.###");

        return df.format(stack.pop());

    }

    private static String converter(String expression) {
        for (int i = 1; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '×') {
                expression = expression.substring(0, i) + "*" + expression.substring(i + 1);
            }
            if (c == '÷') {
                expression = expression.substring(0, i) + "/" + expression.substring(i + 1);
            }
            if (c == '-' && expression.charAt(i - 1) == '(') {//this was added to handle negative expressions like (-2-2)
                expression = expression.substring(0, i) + "0-" + expression.substring(i + 1);
            }
        }
        return expression;
    }
}
