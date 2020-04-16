package com.example.jokelibrary;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FunnyJokesUnitTest {


    /**
     * tellAJoke returns a random joke. For its testing we ensure it returns a String object
     * We assume the String returned is a joke
     */

    @Test
    public void testGetJokes() {

        FunnyJokes funnyJokes = new FunnyJokes();

        Object joke = funnyJokes.tellAJoke();

        assertThat(joke, instanceOf(String.class));
    }

}
