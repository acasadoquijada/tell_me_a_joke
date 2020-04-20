package com.example.jokelibrary;

import java.util.List;
import java.util.Random;

/**
 * Java class that supplies with jokes
 *
 * Jokes from here: https://short-funny.com/one-liners.php
 */
public class FunnyJokes {

    private final static String[] myJokes = {
            "I dreamed I was forced to eat a giant marshmallow. When I woke up, my pillow was gone.",
            "Have you ever tried eating a clock? It's really time-consuming, especially if you go for seconds.",
            "Where do fish sleep? In the riverbed.",
            "My dog is an awesome fashion adviser. Every time I ask him what I look like in my clothes, he says WOW!",
            "Mom! I’m a 3d printer! - Oh come on, Tommy, close the door when you poop.",
            "Why do bees hum? They don't remember the text!",
            "Why are ghost such bad liars? Because they are easy to see through.",
            "I called the hospital but the line was dead.",
            "If I got 50 cents for every failed math exam, I’d have 6.30 dollars now.",
            " I heard women love a man in uniform. Can’t wait to start working at McDonalds."
            };


    /**
     * Joke getter
     * @return a random joke
     */
    public static String tellAJoke(){

        Random rand = new Random();
        int rand_index = rand.nextInt(myJokes.length);

        return myJokes[rand_index];
    }
}
