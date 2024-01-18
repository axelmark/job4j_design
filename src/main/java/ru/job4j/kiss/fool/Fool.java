package ru.job4j.kiss.fool;

import java.util.Scanner;

public class Fool {
    private static Integer startAt = 1;

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");

        var input = new Scanner(System.in);
        while (startAt < 100) {
            if (isFizzBuzz()) {
                System.out.println("FizzBuzz");
            } else if (isFizz()) {
                System.out.println("Fizz");
            } else if (isBuzz()) {
                System.out.println("Buzz");
            } else {
                System.out.println(startAt);
            }
            startAt++;
            var answer = input.nextLine();
            if (isFizzBuzz()) {
                if (!"FizzBuzz".equals(answer)) {
                    printMsgAndStartAt0();
                }
            } else if (isFizz()) {
                if (!"Fizz".equals(answer)) {
                    printMsgAndStartAt0();
                }
            } else if (isBuzz()) {
                if (!"Buzz".equals(answer)) {
                    printMsgAndStartAt0();
                }
            } else {
                if (!String.valueOf(startAt).equals(answer)) {
                    printMsgAndStartAt0();
                }
            }
            startAt++;
        }
    }

    public static boolean isFizzBuzz() {
        return startAt % 3 == 0 && startAt % 5 == 0;
    }

    public static boolean isFizz() {
        return startAt % 3 == 0;
    }

    public static boolean isBuzz() {
        return startAt % 5 == 0;
    }

    public static void printMsgAndStartAt0() {
        System.out.println("Ошибка. Начинай снова.");
        startAt = 0;
    }
}