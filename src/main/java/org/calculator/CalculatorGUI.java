package org.calculator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.stream.Stream;

public class CalculatorGUI extends JFrame implements ActionListener {

    private final JTextField textField;
    private final JLabel label;
    private final Calculator calculator;

    private double firstNum;
    private String operator;

    public CalculatorGUI(Calculator calculator) {
        this.calculator = calculator;
        this.textField = new JTextField();
        this.label = new JLabel();

        setTitle("Calculator");
        setSize(310, 510);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setupComponents();
        setupButtons();
        setVisible(true);
    }

    private void setupComponents() {
        textField.setBounds(20, 40, 270, 40);
        textField.setFont(new Font("Arial", Font.PLAIN, 20));
        textField.setEditable(false);
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        add(textField);

        label.setBounds(250, 0, 50, 50);
        label.setForeground(Color.WHITE);
        add(label);
    }

    private void setupButtons() {
        String[] buttonLabels = {"7", "8", "9", "/", "4", "5", "6", "x", "1", "2", "3", "-", "0", ".", "=", "+"};

        // Create number and operator buttons using stream
        Stream.of(buttonLabels).forEach(label -> {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.setFocusable(false);
            button.addActionListener(this);

            // Calculate button position based on its index
            int index = Arrays.asList(buttonLabels).indexOf(label);
            int x = 10 + (index % 4) * 70; // Adjust x position for each button
            int y = 230 + (index / 4) * 60; // Adjust y position after every 4 buttons

            button.setBounds(x, y, 60, 40);
            add(button);
        });

        // Clear button setup with red background and white text
        JButton clear = new JButton("C");
        clear.setBounds(10, 170, 60, 40);
        clear.setFont(new Font("Arial", Font.PLAIN, 20));
//        clear.setBackground(Color.RED);  // Set background color to red
        clear.setForeground(Color.RED); // Set text color to white
        clear.addActionListener(e -> textField.setText("")); // Clear the text field
        clear.setOpaque(true);
        add(clear);

//        isOpaque = true;
        //set Opaque(true);


    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        try {
            if (command.matches("[0-9]")) {
                textField.setText(textField.getText() + command);
            } else if (command.equals(".")) {
                textField.setText(textField.getText() + ".");
            } else if (command.equals("C")) {
                textField.setText("");
            } else if (command.equals("=")) {
                calculateResult();
            } else {
                operator = command;
                firstNum = Double.parseDouble(textField.getText());
                textField.setText("");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ArithmeticException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void calculateResult() {
        double secondNum = Double.parseDouble(textField.getText());
        double result = switch (operator) {
            case "+" -> calculator.add(firstNum, secondNum);
            case "-" -> calculator.subtract(firstNum, secondNum);
            case "x" -> calculator.multiply(firstNum, secondNum);
            case "/" -> calculator.divide(firstNum, secondNum);
            default -> 0;
        };
        textField.setText(String.valueOf(result));
    }
}
