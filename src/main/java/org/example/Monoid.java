package org.example;

public interface Monoid<T> {
    T combine(T a, T b); // бинарная операция
    T identity(); // единичный элемент
}