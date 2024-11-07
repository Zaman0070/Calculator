package org.calculator;


public interface Calculator {
    double add(double a, double b);
    double subtract(double a, double b);
    double multiply(double a, double b);
    double divide(double a, double b) throws ArithmeticException;
    double square(double a);
    double sqrt(double a);
    double reciprocal(double a) throws ArithmeticException;
}
