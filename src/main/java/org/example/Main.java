package org.example;

import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.function.Consumer;

public class Main {
    private static final Scanner in = new Scanner(System.in);

    public static void usingSetAdditionMonoid() {
        Monoid<Set<String>> setMonoid = new SetAdditionMonoid<>();

        Set<String> set1 = new HashSet<>();
        set1.add("apple");
        set1.add("banana");

        Set<String> set2 = new HashSet<>();
        set2.add("banana");
        set2.add("cherry");

        Set<String> set3 = new HashSet<>();
        set3.add("date");

        Set<String> combinedSet = setMonoid.combine(set1, set2);
        combinedSet = setMonoid.combine(combinedSet, set3);

        System.out.println("Combined Set: " + combinedSet); // Вывод: Combined Set: [apple, banana, cherry, date]

        // Проверка единичного элемента
        Set<String> emptySet = setMonoid.identity();
        System.out.println("Is emptySet identity? " + emptySet.isEmpty()); // Вывод: Is emptySet identity? true
    }


    public static void usingStringConcatenationMonoid() {
        Monoid<String> stringMonoid = new StringConcatenationMonoid();

        MonoidElement<String> element1 = new MonoidElement<>("Hello, ", stringMonoid);
        MonoidElement<String> element2 = new MonoidElement<>("World!", stringMonoid);
        MonoidElement<String> element3 = new MonoidElement<>(" How are you?", stringMonoid);

        MonoidElement<String> combinedElement = element1.combine(element2).combine(element3);
        System.out.println("Combined String: " + combinedElement); // Вывод: Combined String: Hello, World! How are you?

        // Проверка единичного элемента
        MonoidElement<String> emptyElement = new MonoidElement<>("", stringMonoid);
        System.out.println("Is emptyElement identity? " + emptyElement.isIdentity()); // Вывод: Is emptyElement identity? true
    }

    public static void usingIntegerAdditionMonoid() {
        Monoid<UnsignedInteger> uiaMonoid = new UnsignedIntegerAdditionMonoid();

        MonoidElement<UnsignedInteger> element1 = new MonoidElement<>(new UnsignedInteger(5), uiaMonoid);
        MonoidElement<UnsignedInteger> element2 = new MonoidElement<>(new UnsignedInteger(10), uiaMonoid);
        MonoidElement<UnsignedInteger> element3 = new MonoidElement<>(new UnsignedInteger(12), uiaMonoid);

        MonoidElement<UnsignedInteger> combinedElement = element1.combine(element2);
        System.out.println("Combined Element: " + combinedElement);

        System.out.println("Is element1 identity? " + element1.isIdentity()); // Вывод: Is element1 identity? false
        System.out.println("Is identity element? " + new MonoidElement<>(new UnsignedInteger(0), uiaMonoid).isIdentity()); // Вывод: Is identity element? true
    }

    public static void main(String[] args) {
//        usingIntegerAdditionMonoid();
//        usingStringConcatenationMonoid();
//        usingSetAdditionMonoid();

        MonoidExpressionSimplifier.test();
        while (true) {
            System.out.print("Enter monoid type. Supported types are: ");

            MonoidExpressionSimplifier.getSupportedMonoidTypes().forEach(s -> {
                System.out.print(s);
                System.out.print(" ");
            });
            System.out.println();

            var monoidType = in.nextLine();
            System.out.println("Enter expression, e.g. 1 2 3");
            System.out.println(
                    MonoidExpressionSimplifier.simplify(
                            in.nextLine(),
                            monoidType
                    )
            );


        }
    }
}
