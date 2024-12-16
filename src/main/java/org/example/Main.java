package org.example;

import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
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
