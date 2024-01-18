package ru.job4j.template;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

class GeneratorTextTest {
    @Test
    public void produceTest() {
        HashMap<String, String> myMap = new HashMap<String, String>();
        myMap.put("Petr Arsentev", "you");
        GeneratorText generatorText = new GeneratorText();
        var res = generatorText.produce("I am a ${name}, Who are ${subject}?", myMap);
        assertThat(res).isEqualTo("I am a Petr Arsentev, Who are you?");
    }
}