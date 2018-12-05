package com.example.android.jokelibrary;

import java.util.ArrayList;
import java.util.Random;

@SuppressWarnings("WeakerAccess")
public class Jokes {

    public static String tellJoke() {
        ArrayList<String> jokes = new ArrayList<>();
        jokes.add("Joke_1");
        jokes.add("Joke_2");
        jokes.add("Joke_3");
        jokes.add("Joke_4");
        jokes.add("Joke_5");
        jokes.add("Joke_6");
        jokes.add("Joke_7");
        jokes.add("Joke_8");

        Random random = new Random();
        int number = random.nextInt(jokes.size());
        return jokes.get(number);
    }
}