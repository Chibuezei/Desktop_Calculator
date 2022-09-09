package calculator;

import javax.swing.*;
import java.awt.*;

public class Calculator extends JFrame {
    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_HEIGHT = 500;
    private static JLabel equationLabel;
    private static JLabel resultLabel;


    public Calculator() {
        super("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        components();
        setLayout(null);
        setVisible(true);
    }

    private void components() {
        JPanel buttonPanel = new JPanel(); //JPanel is a smaller container, it cannot represent a window of a program.
        buttonPanel.setBounds(40, 150, 220, 70);
        buttonPanel.setLayout(new BorderLayout());
        add(buttonPanel);

        equationLabel = new JLabel();
        equationLabel.setName("EquationLabel");
        equationLabel.setBounds(10, 25, WINDOW_WIDTH - 35, 20);
        add(equationLabel);
        equationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        equationLabel.setVerticalAlignment(SwingConstants.CENTER);

        resultLabel = new JLabel();
        resultLabel.setName("ResultLabel");
        resultLabel.setBounds(30, 35, WINDOW_WIDTH - 35, 30);
        add(resultLabel);

        JButton btn0 = new JButton("0");
        btn0.setName("Zero");
        btn0.addActionListener(e -> addToTextBox(btn0));
        JButton btn1 = new JButton("1");
        btn1.setName("One");
        btn1.addActionListener(e -> addToTextBox(btn1));

        JButton btn2 = new JButton("2");
        btn2.setName("Two");
        btn2.addActionListener(e -> addToTextBox(btn2));

        JButton btn3 = new JButton("3");
        btn3.setName("Three");
        btn3.addActionListener(e -> addToTextBox(btn3));

        JButton btn4 = new JButton("4");
        btn4.setName("Four");
        btn4.addActionListener(e -> addToTextBox(btn4));

        JButton btn5 = new JButton("5");
        btn5.setName("Five");
        btn5.addActionListener(e -> addToTextBox(btn5));

        JButton btn6 = new JButton("6");
        btn6.setName("Six");
        btn6.addActionListener(e -> addToTextBox(btn6));

        JButton btn7 = new JButton("7");
        btn7.setName("Seven");
        btn7.addActionListener(e -> addToTextBox(btn7));

        JButton btn8 = new JButton("8");
        btn8.setName("Eight");
        btn8.addActionListener(e -> addToTextBox(btn8));

        JButton btn9 = new JButton("9");
        btn9.setName("Nine");
        btn9.addActionListener(e -> addToTextBox(btn9));

        JButton Add = new JButton("\u002B");
        Add.setName("Add");
        Add.addActionListener(e -> addToTextBox(Add));

        JButton subtract = new JButton("-");
        subtract.setName("Subtract");
        subtract.addActionListener(e -> addToTextBox(subtract));

        JButton multiply = new JButton("\u00D7");
        multiply.setName("Multiply");
        multiply.addActionListener(e -> addToTextBox(multiply));

        JButton divide = new JButton("\u00F7");
        divide.setName("Divide");
        divide.addActionListener(e -> addToTextBox(divide));

        JButton dot = new JButton(".");
        dot.setName("Dot");
        dot.addActionListener(e -> addToTextBox(dot));

        JButton equals = new JButton("=");
        equals.setName("Equals");
        equals.addActionListener(e -> calculateExp());

        JButton clearTextField = new JButton("C");
        clearTextField.setName("Clear");
        clearTextField.addActionListener(e -> equationLabel.setText(""));

        JButton delete = new JButton("Del");
        delete.setName("Delete");
        delete.addActionListener(e -> deleteFromTextBox());


        buttonPanel.add(btn7);
        buttonPanel.add(btn8);
        buttonPanel.add(btn9);
        buttonPanel.add(divide);
        buttonPanel.add(btn4);
        buttonPanel.add(btn5);
        buttonPanel.add(btn6);
        buttonPanel.add(multiply);
        buttonPanel.add(btn1);
        buttonPanel.add(btn2);
        buttonPanel.add(btn3);
        buttonPanel.add(Add);
        buttonPanel.add(subtract);
        buttonPanel.add(equals);
        buttonPanel.add(btn0);
        buttonPanel.add(clearTextField);
        buttonPanel.add(delete);
        buttonPanel.add(dot);


        buttonPanel.setLayout(new GridLayout(5, 4, 20, 25));
        buttonPanel.setSize(300, 300);
        buttonPanel.setVisible(true);


    }

    private void addToTextBox(JButton jButton) {
        String expression = equationLabel.getText();
        String buttonText = jButton.getText();
        equationLabel.setText(expression + buttonText);
    }
    private void deleteFromTextBox() {
        StringBuilder expression = new StringBuilder(equationLabel.getText());
        expression.deleteCharAt(expression.length()-1);
        equationLabel.setText(String.valueOf(expression));
    }
    private void calculateExp() {
        String expression = equationLabel.getText();
        String result = SolveExpression.evaluatePostfix(SolveExpression.infixToPostfix(expression));
        resultLabel.setText(result);


    }


}
