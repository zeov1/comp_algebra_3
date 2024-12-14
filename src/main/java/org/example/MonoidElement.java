package org.example;

public class MonoidElement<T> {
    private T value;
    private Monoid<T> monoid;

    public MonoidElement(T value, Monoid<T> monoid) {
        this.value = value;
        this.monoid = monoid;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public MonoidElement<T> combine(MonoidElement<T> other) {
        T combinedValue = monoid.combine(this.value, other.value);
        return new MonoidElement<>(combinedValue, monoid);
    }

    public boolean isIdentity() {
        return value.equals(monoid.identity());
    }

    @Override
    public String toString() {
        return value.toString();
    }
}