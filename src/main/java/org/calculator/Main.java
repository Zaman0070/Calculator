package org.calculator;// org/calculator/Main.java
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorGUI(new SimpleCalculator()));
    }
}
