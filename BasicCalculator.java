import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BasicCalculator {
    private JFrame frame;
    private JTextField textField;
    private double firstNumber = 0;
    private String operator = "";
    private boolean isOperatorClicked = false;

    public BasicCalculator() {
        frame = new JFrame("Basic Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLayout(new BorderLayout());

        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        frame.add(textField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 4));
        frame.add(buttonPanel, BorderLayout.CENTER);

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 24));
            buttonPanel.add(button);
            button.addActionListener(new ButtonClickListener());
        }

        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (Character.isDigit(command.charAt(0)) || command.equals(".")) {
                if (isOperatorClicked) {
                    textField.setText("");
                    isOperatorClicked = false;
                }
                textField.setText(textField.getText() + command);
            } else if ("+-*/".contains(command)) {
                if (!textField.getText().isEmpty()) {
                    firstNumber = Double.parseDouble(textField.getText());
                    operator = command;
                    isOperatorClicked = true;
                }
            } else if (command.equals("=")) {
                if (!operator.isEmpty() && !isOperatorClicked) {
                    double secondNumber = Double.parseDouble(textField.getText());
                    double result = calculate(firstNumber, secondNumber, operator);
                    textField.setText(String.valueOf(result));
                    operator = "";
                }
            }
        }

        private double calculate(double num1, double num2, String operator) {
            switch (operator) {
                case "+":
                    return num1 + num2;
                case "-":
                    return num1 - num2;
                case "*":
                    return num1 * num2;
                case "/":
                    if (num2 != 0) {
                        return num1 / num2;
                    } else {
                        JOptionPane.showMessageDialog(frame, "Cannot divide by zero", "Error", JOptionPane.ERROR_MESSAGE);
                        return 0;
                    }
                default:
                    return 0;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BasicCalculator());
    }
}