package calculator;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Calculator extends JFrame {
    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_HEIGHT = 600;
    static JLabel equationLabel;
    private static JLabel resultLabel;
    private static boolean operandAdded;
    private static boolean negated;
    private static int numberOfLeftParentheses;
    private static int numberOfRightParentheses;

    private static int indexOfLastOperator = 0;
    private static final List<String> numbers = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "0");
    private static final List<String> operators = List.of("+", "-", "×", "÷", "^", "√");


    public Calculator() {
        super("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        components();
        setLayout(null);
        setVisible(true);
    }

    private void components() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(40, 150, 220, 70);
        buttonPanel.setLayout(new BorderLayout());
        add(buttonPanel);

        Font font = new Font("Courier", Font.BOLD, 12);

        equationLabel = new JLabel();
        equationLabel.setName("EquationLabel");
        equationLabel.setBounds(10, 25, WINDOW_WIDTH - 35, 20);
        add(equationLabel);
        equationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        equationLabel.setVerticalAlignment(SwingConstants.CENTER);
        equationLabel.setFont(font);
        equationLabel.setForeground(Color.GREEN.darker());


        resultLabel = new JLabel();
        resultLabel.setName("ResultLabel");
        resultLabel.setBounds(30, 35, WINDOW_WIDTH - 35, 30);
        add(resultLabel);

        JButton btn0 = new JButton("0");
        btn0.setName("Zero");
        btn0.addActionListener(e -> addOperandToTextBox(btn0));
        JButton btn1 = new JButton("1");
        btn1.setName("One");
        btn1.addActionListener(e -> addOperandToTextBox(btn1));

        JButton btn2 = new JButton("2");
        btn2.setName("Two");
        btn2.addActionListener(e -> addOperandToTextBox(btn2));

        JButton btn3 = new JButton("3");
        btn3.setName("Three");
        btn3.addActionListener(e -> addOperandToTextBox(btn3));

        JButton btn4 = new JButton("4");
        btn4.setName("Four");
        btn4.addActionListener(e -> addOperandToTextBox(btn4));

        JButton btn5 = new JButton("5");
        btn5.setName("Five");
        btn5.addActionListener(e -> addOperandToTextBox(btn5));

        JButton btn6 = new JButton("6");
        btn6.setName("Six");
        btn6.addActionListener(e -> addOperandToTextBox(btn6));

        JButton btn7 = new JButton("7");
        btn7.setName("Seven");
        btn7.addActionListener(e -> addOperandToTextBox(btn7));

        JButton btn8 = new JButton("8");
        btn8.setName("Eight");
        btn8.addActionListener(e -> addOperandToTextBox(btn8));

        JButton btn9 = new JButton("9");
        btn9.setName("Nine");
        btn9.addActionListener(e -> addOperandToTextBox(btn9));

        JButton Add = new JButton("\u002B");
        Add.setName("Add");
        Add.addActionListener(e -> addOperatorToTextBox(Add));

        JButton subtract = new JButton("-");
        subtract.setName("Subtract");
        subtract.addActionListener(e -> addOperatorToTextBox(subtract));

        JButton multiply = new JButton("\u00D7");
        multiply.setName("Multiply");
        multiply.addActionListener(e -> addOperatorToTextBox(multiply));

        JButton divide = new JButton("\u00F7");
        divide.setName("Divide");
        divide.addActionListener(e -> addOperatorToTextBox(divide));

        JButton dot = new JButton(".");
        dot.setName("Dot");
        dot.addActionListener(e -> addOperandToTextBox(dot));

        JButton equals = new JButton("=");
        equals.setName("Equals");
        equals.addActionListener(e -> calculateExp());

        JButton clearTextField = new JButton("C");
        clearTextField.setName("Clear");
        clearTextField.addActionListener(e -> clearTextField());

        JButton clearText = new JButton("CE");
        clearText.setName("dummy");
        clearText.addActionListener(e -> clearTextField());

        JButton delete = new JButton("Del");
        delete.setName("Delete");
        delete.addActionListener(e -> deleteFromTextBox());

        JButton bracket = new JButton("( )");
        bracket.setName("Parentheses");
        bracket.addActionListener(e -> addParentheses());


        JButton squareRoot = new JButton("\u221A");
        squareRoot.setName("SquareRoot");
        squareRoot.addActionListener(e -> {
            equationLabel.setText(equationLabel.getText() + "\u221A" + "(");
            numberOfLeftParentheses++;//There was a need to keep count of the parentheses added to the equation because i didn't want to use loop to count
        });

        JButton powerTwo = new JButton("X²");
        powerTwo.setName("PowerTwo");
        powerTwo.addActionListener(e -> {
            equationLabel.setText(equationLabel.getText() + "^" + "(2)");
            numberOfLeftParentheses++;//
            numberOfRightParentheses++;
        });

        JButton powerY = new JButton("X^");
        powerY.setName("PowerY");
        powerY.addActionListener(e -> {
            equationLabel.setText(equationLabel.getText() + "^(");
            numberOfLeftParentheses++;
        });

        JButton plusMinus = new JButton("\u2213");
        plusMinus.setName("PlusMinus");
        plusMinus.addActionListener(e -> {
            if (!negated) {
                negated = true;//this boolean is needed to remove the negative sign if already added.
                equationLabel.setText("(-" + equationLabel.getText());
                numberOfLeftParentheses++;
            } else {
                negated = false;
                numberOfLeftParentheses--;
                equationLabel.setText(equationLabel.getText().substring(2));
            }
        });

        buttonPanel.add(bracket);
        buttonPanel.add(clearText);
        buttonPanel.add(clearTextField);
        buttonPanel.add(delete);

        buttonPanel.add(powerTwo);
        buttonPanel.add(powerY);
        buttonPanel.add(squareRoot);
        buttonPanel.add(divide);

        buttonPanel.add(btn7);
        buttonPanel.add(btn8);
        buttonPanel.add(btn9);
        buttonPanel.add(multiply);

        buttonPanel.add(btn4);
        buttonPanel.add(btn5);
        buttonPanel.add(btn6);
        buttonPanel.add(subtract);

        buttonPanel.add(btn1);
        buttonPanel.add(btn2);
        buttonPanel.add(btn3);
        buttonPanel.add(Add);

        buttonPanel.add(plusMinus);
        buttonPanel.add(btn0);
        buttonPanel.add(dot);
        buttonPanel.add(equals);


        buttonPanel.setLayout(new GridLayout(6, 4, 10, 15));
        buttonPanel.setSize(350, 400);
        buttonPanel.setVisible(true);


    }

    private void clearTextField() {
        equationLabel.setText("");
        indexOfLastOperator = 0; //resetting all the variables used for keeping count
        numberOfLeftParentheses = 0;
        numberOfRightParentheses = 0;
        negated = false;
    }

    private void addOperandToTextBox(JButton jButton) {
        equationLabel.setForeground(Color.GREEN.darker());//this is to change the colour back to green in case it is red because of error
        equationLabel.setText(equationLabel.getText() + jButton.getText());
        operandAdded = true;
    }

    private void addOperatorToTextBox(JButton jButton) {
        StringBuilder expression = new StringBuilder(equationLabel.getText());
        String buttonText = jButton.getText();
        if (operandAdded) { // this boolean prevents indexOutOfBounds in the case that the user enters 2 operators next to each other
            if (indexOfLastOperator > 0 && expression.charAt(indexOfLastOperator + 1) == '.') {
                indexOfLastOperator++;
                expression.insert(indexOfLastOperator, "0"); //handles expression like 0.9*.6+2
                equationLabel.setText(String.valueOf(expression));
            }
        }


        indexOfLastOperator = expression.length();

        if (expression.isEmpty()) { //if the first button pressed is an operator, do nothing
            equationLabel.setText("");
        } else {
            String lastCharInLabel = String.valueOf(expression.charAt(expression.length() - 1));
            String firstCharInLabel = String.valueOf(expression.charAt(0));

            if (lastCharInLabel.equals(".")) { //handles 0.2+9.
                indexOfLastOperator++;
                expression.append("0");
                expression.append(buttonText);
                equationLabel.setText(String.valueOf(expression));
            } else if (operators.contains(lastCharInLabel)) { //if the last char in the label is an operator, delete it and add the last one pressed
                expression = new StringBuilder(expression.substring(0, expression.length() - 1));
                expression.append(buttonText);
                equationLabel.setText(String.valueOf(expression));
                indexOfLastOperator--;
            } else if (firstCharInLabel.equals(".")) { //handles .9+2
                indexOfLastOperator++;
                expression.insert(0, "0");
                expression.append(buttonText);
                equationLabel.setText(String.valueOf(expression));

            } else { // add the operator if the expression is fine
                expression.append(buttonText);
                equationLabel.setText(String.valueOf(expression));

            }
            operandAdded = false;
        }
    }

    private void deleteFromTextBox() {
        equationLabel.setForeground(Color.GREEN.darker());//this is to change the colour back to green in case it is red because to error

        StringBuilder expression = new StringBuilder(equationLabel.getText());
        //if the textBox is empty, do nothing, otherwise delete the last character;
        if (!expression.isEmpty()) {
            expression.deleteCharAt(expression.length() - 1);
            equationLabel.setText(String.valueOf(expression));
        }
        //a bug maybe here, I need to add a logic here to reset indexOfLastOperator, if an operator is deleted
    }

    private void calculateExp() {
        StringBuilder expression = new StringBuilder(equationLabel.getText());
        String lastCharInLabel = String.valueOf(expression.charAt(expression.length() - 1));
        if (operators.contains(lastCharInLabel) || lastCharInLabel.equals("(")) {// handles 9+8.= or 9+
            equationLabel.setForeground(Color.RED.darker()); //set the label to red and signify that something is wrong
            return;
        }

        //handles error that will result from an expression like this 9+.2=
        if (indexOfLastOperator > 0 && expression.charAt(indexOfLastOperator + 1) == '.') {
            indexOfLastOperator++;
            expression.insert(indexOfLastOperator, "0");
        }
        String result = SolveExpression.evaluatePostfix(SolveExpression.infixToPostfix(String.valueOf(expression)));
        if (result.equals("∞")) {         //since I am using double, division by zero will return infinity.
            equationLabel.setForeground(Color.RED.darker());
        } else {
            resultLabel.setText(result);
        }
    }

    private static void addParentheses() {
        StringBuilder expression = new StringBuilder(equationLabel.getText());
        boolean parenthesesAdded = false;

        if (numberOfLeftParentheses == numberOfRightParentheses) {
            equationLabel.setText(equationLabel.getText() + "(");
            numberOfLeftParentheses++;
            parenthesesAdded = true;
        } else if (!expression.isEmpty()) { //prevents indexOutOfBounds
            String lastCharInLabel = String.valueOf(expression.charAt(expression.length() - 1));

            if (lastCharInLabel.equals("(") || operators.contains(lastCharInLabel)) {
                equationLabel.setText(equationLabel.getText() + "(");
                numberOfLeftParentheses++;
                parenthesesAdded = true;
            }
        }
        if (!parenthesesAdded) {
            equationLabel.setText(equationLabel.getText() + ")");
            numberOfRightParentheses++;
        }
    }


}
