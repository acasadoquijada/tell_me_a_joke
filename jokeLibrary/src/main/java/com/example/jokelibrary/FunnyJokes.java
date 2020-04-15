package com.example.jokelibrary;

import java.util.List;
import java.util.Random;

public class FunnyJokes {

    private final static String[] myJokes = {
            "I dreamed I was forced to eat a giant marshmallow. When I woke up, my pillow was gone.",
            "Have you ever tried eating a clock? It's really time-consuming, especially if you go for seconds.",
            "Where do fish sleep? In the riverbed."
            };


    public static String tellAJoke(){

        Random rand = new Random();
        int rand_index = rand.nextInt(myJokes.length);

        return myJokes[rand_index];
    }
}
