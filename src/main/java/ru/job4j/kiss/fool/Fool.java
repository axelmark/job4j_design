package ru.job4j.kiss.fool;

import java.util.Scanner;

public class Fool {
    private static Integer startAt = 1;

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");

        var input = new Scanner(System.in);
        while (startAt < 100) {
            if (startAt % 3 == 0 && startAt % 5 == 0) {
                System.out.println("FizzBuzz");
            } else if (startAt % 3 == 0) {
                System.out.println("Fizz");
            } else if (startAt % 5 == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(startAt);
            }
            startAt++;
            var answer = input.nextLine();
            if (isFizzBuzz()) {
                if (!"FizzBuzz".equals(answer)) {
                    System.out.println("Ошибка. Начинай снова.");
                    startAt = 0;
                }
            } else if (startAt % 3 == 0) {
                if (!"Fizz".equals(answer)) {
                    System.out.println("Ошибка. Начинай снова.");
                    startAt = 0;
                }
            } else if (startAt % 5 == 0) {
                if (!"Buzz".equals(answer)) {
                    System.out.println("Ошибка. Начинай снова.");
                    startAt = 0;
                }
            } else {
                if (!String.valueOf(startAt).equals(answer)) {
                    System.out.println("Ошибка. Начинай снова.");
                    startAt = 0;
                }
            }
            startAt++;
        }
    }

    public static boolean isFizzBuzz() {
        if (startAt % 3 == 0 && startAt % 5 == 0) {
            return true;
        }
        return false;
    }
}