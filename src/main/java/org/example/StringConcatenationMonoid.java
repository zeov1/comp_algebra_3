package org.example;

public class StringConcatenationMonoid implements Monoid<String> {
    @Override
    public String combine(String a, String b) {
        return a + b; // Конкатенация строк
    }

    @Override
    public String identity() {
        return ""; // Пустая строка как единичный элемент
    }
}