package org.example;

import java.util.HashSet;
import java.util.Set;

import static org.example.MonoidExpressionSimplifier.simplify;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MonoidExpressionSimplifierTest {
    @org.junit.jupiter.api.Test
    void testSetSimplification() {
        String setExpression = "{apple, banana} {banana, cherry} {date}";
        MonoidElement<Set<String>> simplifiedSet = simplify(setExpression, "SetAddition");
        Set<String> expectedSet = new HashSet<>();
        expectedSet.add("banana");
        expectedSet.add("cherry");
        expectedSet.add("date");
        expectedSet.add("apple");
        assertEquals(expectedSet, simplifiedSet.getValue());
    }

    @org.junit.jupiter.api.Test
    void testStringSimplification1() {
        String stringExpression = "{Hello } {World!}";
        MonoidElement<String> simplifiedString = simplify(stringExpression, "StringConcatenation");
        assertEquals("Hello World!", simplifiedString.toString());
    }

    @org.junit.jupiter.api.Test
    void testStringSimplification2() {
        String stringExpression2 = ""; // Test empty string
        MonoidElement<String> simplifiedString2 = simplify(stringExpression2, "StringConcatenation");
        assertEquals("", simplifiedString2.toString());
    }

    @org.junit.jupiter.api.Test
    void testUintSimplification1() {
        String uintExpression = "5 10 12";
        MonoidElement<UnsignedInteger> simplifiedUint = simplify(uintExpression, "UnsignedIntegerAddition");
        assertEquals(new UnsignedInteger(27), simplifiedUint.getValue());
    }

    @org.junit.jupiter.api.Test
    void testUintSimplification2() {
        String uintExpression2 = ""; // Test empty input
        MonoidElement<UnsignedInteger> simplifiedUint2 = simplify(uintExpression2, "UnsignedIntegerAddition");
        assertEquals(new UnsignedInteger(0), simplifiedUint2.getValue());
    }
}