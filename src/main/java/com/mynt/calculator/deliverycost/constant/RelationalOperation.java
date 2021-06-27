package com.mynt.calculator.deliverycost.constant;

import lombok.AllArgsConstructor;

import java.util.function.BiPredicate;

@AllArgsConstructor
public enum RelationalOperation {

    LT((a,b) -> a < b),
    GT((a,b) -> a > b),
    GE((a,b) -> a >= b),
    LE((a,b) -> a <= b);

    private final BiPredicate<Double, Double> condition;

    public Boolean evaluate(double a, double b) {
        return condition.test(a, b);
    }
}