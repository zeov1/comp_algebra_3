package org.example;

import java.util.HashSet;
import java.util.Set;

public class SetAdditionMonoid<T> implements Monoid<Set<T>> {
    @Override
    public Set<T> combine(Set<T> a, Set<T> b) {
        Set<T> result = new HashSet<>(a); // Создаем новое множество на основе первого
        result.addAll(b); // Объединяем с вторым множеством
        return result;
    }

    @Override
    public Set<T> identity() {
        return new HashSet<>(); // Пустое множество как единичный элемент
    }
}
