package org.example;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MonoidExpressionSimplifier {

    private static final Map<String, Monoid<?>> MONOID_MAP = new HashMap<>();

    static {
        MONOID_MAP.put("SetAddition", new SetAdditionMonoid<>());
        MONOID_MAP.put("StringConcatenation", new StringConcatenationMonoid());
        MONOID_MAP.put("UnsignedIntegerAddition", new UnsignedIntegerAdditionMonoid());
    }

    public static List<String> getSupportedMonoidTypes() {
        return MONOID_MAP.keySet().stream().toList();
    }

    public static <T> MonoidElement<T> simplify(String expression, String monoidType) {
        if (!MONOID_MAP.containsKey(monoidType)) {
            throw new IllegalArgumentException("Unsupported monoid type: " + monoidType);
        }

        Monoid<T> monoid = (Monoid<T>) MONOID_MAP.get(monoidType);
        MonoidElement<T> result = new MonoidElement<>(monoid.identity(), monoid);


        if (monoidType.equals("SetAddition")) {
            // Set parsing logic
            Pattern setPattern = Pattern.compile("\\{(.*?)\\}");
            Matcher setMatcher = setPattern.matcher(expression);

            while (setMatcher.find()) {
                String setString = setMatcher.group(1);
                String[] elements = setString.split(",");
                Set<String> set = new HashSet<>();
                for (String element : elements) {
                    set.add(element.trim());
                }
                result = result.combine(new MonoidElement<>((T) set, monoid));

            }

        } else if (monoidType.equals("StringConcatenation")) {
            Pattern stringPattern = Pattern.compile("\\{(.*?)\\}"); // Match strings in curly braces
            Matcher stringMatcher = stringPattern.matcher(expression);

            while (stringMatcher.find()) {
                result = result.combine(new MonoidElement<>((T) stringMatcher.group(1), monoid));
            }

        } else if (monoidType.equals("UnsignedIntegerAddition")) {
            // Unsigned Integer parsing logic. Handle multiple numbers and empty input.
            Pattern numberPattern = Pattern.compile("\\d+");
            Matcher numberMatcher = numberPattern.matcher(expression);

            if (!numberMatcher.find()) {
                return new MonoidElement<>((T) new UnsignedInteger(0), monoid); // Handle empty input
            }

            do {
                int value = Integer.parseInt(numberMatcher.group());
                result = result.combine(new MonoidElement<>((T) new UnsignedInteger(value), monoid));
            } while (numberMatcher.find());



        }


        return result;
    }



    public static void test() {

        String setExpression = "{apple, banana} {banana, cherry} {date}";
        MonoidElement<Set<String>> simplifiedSet = simplify(setExpression, "SetAddition");
        System.out.println("Simplified Set: " + simplifiedSet.getValue());

        String stringExpression = "{Hello} {World!}";
        MonoidElement<String> simplifiedString = simplify(stringExpression, "StringConcatenation");
        System.out.println("Simplified String: " + simplifiedString.getValue());
        
        String stringExpression2 = ""; // Test empty string
        MonoidElement<String> simplifiedString2 = simplify(stringExpression2, "StringConcatenation");
        System.out.println("Simplified String 2: " + simplifiedString2.getValue());  // Should print ""

        String uintExpression = "5 10 12";
        MonoidElement<UnsignedInteger> simplifiedUint = simplify(uintExpression, "UnsignedIntegerAddition");
        System.out.println("Simplified Unsigned Integer: " + simplifiedUint.getValue());

        String uintExpression2 = ""; // Test empty input
        MonoidElement<UnsignedInteger> simplifiedUint2 = simplify(uintExpression2, "UnsignedIntegerAddition");
        System.out.println("Simplified Unsigned Integer 2: " + simplifiedUint2.getValue()); // Should print 0


    }
}