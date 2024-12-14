package org.example;

public class IntegerAdditionMonoid implements Monoid<Integer> {
    @Override
    public Integer combine(Integer a, Integer b) {
        return a + b;
    }

    @Override
    public Integer identity() {
        return 0; // единичный элемент для сложения
    }
}