package org.example;

public class UnsignedIntegerAdditionMonoid implements Monoid<UnsignedInteger> {
    @Override
    public UnsignedInteger combine(UnsignedInteger a, UnsignedInteger b) {
        return a.add(b);
    }

    @Override
    public UnsignedInteger identity() {
        return new UnsignedInteger(0); // единичный элемент для сложения
    }
}