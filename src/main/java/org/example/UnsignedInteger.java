package org.example;

public class UnsignedInteger {
    private int val;

    UnsignedInteger(int val) {
        if (val < 0)
            throw new IllegalArgumentException("Negative value");
        else
            this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public UnsignedInteger add(UnsignedInteger other) {
        return new UnsignedInteger(this.val + other.val);
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}
