package org.calculator;

public class SimpleCalculator implements Calculator {

    @Override
    public double add(double a, double b) {
        return a + b;
    }

    @Override
    public double subtract(double a, double b) {
        return a - b;
    }

    @Override
    public double multiply(double a, double b) {
        return a * b;
    }

    @Override
    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / b;
    }

    @Override
    public double square(double a) {
        return a * a;
    }

    @Override
    public double sqrt(double a) {
        return Math.sqrt(a);
    }

    @Override
    public double reciprocal(double a) {
        if (a == 0) {
            throw new ArithmeticException("Cannot take reciprocal of zero");
        }
        return 1 / a;
    }
}
