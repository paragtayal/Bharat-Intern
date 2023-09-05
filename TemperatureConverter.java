import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class TemperatureConverter {
    private JFrame frame;
    private JTextField inputField;
    private JLabel resultLabel;
    private JButton convertButton;
    private JButton clearButton;
    private JComboBox<String> unitComboBox;

    private final String[] temperatureUnits = {"Celsius (°C)", "Fahrenheit (°F)", "Kelvin (K)"};

    public TemperatureConverter() {
        frame = new JFrame("Temperature Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(4, 1));

        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 24));
        inputField.setHorizontalAlignment(JTextField.CENTER);

        resultLabel = new JLabel();
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        resultLabel.setHorizontalAlignment(JLabel.CENTER);

        convertButton = new JButton("Convert");
        convertButton.setFont(new Font("Arial", Font.PLAIN, 18));
        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                convertTemperature();
            }
        });

        clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Arial", Font.PLAIN, 18));
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inputField.setText("");
                resultLabel.setText("");
            }
        });

        unitComboBox = new JComboBox<>(temperatureUnits);
        unitComboBox.setFont(new Font("Arial", Font.PLAIN, 18));

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(unitComboBox);
        buttonPanel.add(convertButton);
        buttonPanel.add(clearButton);

        frame.add(inputField);
        frame.add(buttonPanel);
        frame.add(resultLabel);

        frame.setVisible(true);
    }

    private void convertTemperature() {
        try {
            double inputValue = Double.parseDouble(inputField.getText());
            String selectedUnit = (String) unitComboBox.getSelectedItem();
            double convertedValue;
            String resultText;

            if (selectedUnit.equals("Celsius (°C)")) {
                convertedValue = (inputValue - 32) * 5 / 9;
                resultText = formatTemperature(inputValue) + "°F is " + formatTemperature(convertedValue) + "°C";
            } else if (selectedUnit.equals("Fahrenheit (°F)")) {
                convertedValue = (inputValue * 9 / 5) + 32;
                resultText = formatTemperature(inputValue) + "°C is " + formatTemperature(convertedValue) + "°F";
            } else {
                resultText = "Invalid Conversion";
            }

            resultLabel.setText(resultText);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid Input");
        }
    }

    private String formatTemperature(double temperature) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(temperature);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TemperatureConverter());
    }
}
