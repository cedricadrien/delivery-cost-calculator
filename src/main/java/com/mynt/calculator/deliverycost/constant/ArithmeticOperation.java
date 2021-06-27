package com.mynt.calculator.deliverycost.constant;

import lombok.AllArgsConstructor;

import java.util.function.DoubleBinaryOperator;

@AllArgsConstructor
public enum ArithmeticOperation {

    ADD(Double::sum),
    SUB((a, b) -> a - b),
    MUL((a,b) -> a * b),
    DIV((a,b) -> a / b);

    private final DoubleBinaryOperator operation;

    public Double execute(double a, double b) {
        return operation.applyAsDouble(a, b);
    }
}
