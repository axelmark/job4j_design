package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Weather {
    String desc = "Sunny";
    int wind = 10;
    boolean rain = false;
    String[] seasons = {"Winter", "Spring", "Summer", "Autumn"};

    @Override
    public String toString() {
        return "Weather{"
                + "desc='" + desc + '\''
                + ", wind=" + wind
                + ", rain=" + rain
                + ", seasons=" + Arrays.toString(seasons)
                + '}';
    }

    public static void main(String[] args) {
        Weather weather = new Weather();
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(weather);
        System.out.println(gson.toJson(weather));

        Weather weatherMod = gson.fromJson(json, Weather.class);
        System.out.println(weatherMod);
    }
}