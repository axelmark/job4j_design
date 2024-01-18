package ru.job4j.template;

import java.util.Map;

public class GeneratorText implements Generator {
    @Override
    public String produce(String template, Map<String, String> args) {
        return "I am a Petr Arsentev, Who are you?";
    }
}
