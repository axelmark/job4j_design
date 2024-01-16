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
            }
            if (startAt % 3 == 0) {
                System.out.println("Fizz");
            }
            if (startAt % 5 == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(startAt);
            }
            startAt++;
            var answer = input.nextLine();
            if (startAt % 3 == 0 && startAt % 5 == 0 && !"FizzBuzz".equals(answer)
                    || startAt % 3 == 0 && !"Fizz".equals(answer) || startAt % 5 == 0 && !"Buzz".equals(answer)) {
                showMessageAndStartAt0();
            } else {
                if (!String.valueOf(startAt).equals(answer)) {
                    showMessageAndStartAt0();
                }
            }
            startAt++;
        }
    }

    private static void showMessageAndStartAt0() {
        System.out.println("Ошибка. Начинай снова.");
        startAt = 0;
    }
}