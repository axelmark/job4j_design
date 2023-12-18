package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        Random rand = new Random();
        List<String> chatLogs = new ArrayList<>();
        List<String> randomElement = readPhrases();
        boolean answerable = true;

        while (!Objects.equals(userInput, "закончить")) {
            userInput = scanner.next();
            chatLogs.add(userInput);

            String randAnswer = randomElement.get(rand.nextInt(randomElement.size()));

            if (STOP.equals(userInput)) {
                answerable = false;
            }
            if (CONTINUE.equals(userInput)) {
                answerable = true;
            }
            if (OUT.equals(userInput)) {
                saveLog(chatLogs);
                return;
            }
            if (answerable) {
                System.out.println(randAnswer);
                chatLogs.add(randAnswer);

            }
        }
    }

    private List<String> readPhrases() {
        ArrayList<String> phrases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            br.lines().forEach(phrases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("data/consoleChat/consoleChat.txt", "data/consoleChat/botAnswer.txt");
        cc.run();
    }
}