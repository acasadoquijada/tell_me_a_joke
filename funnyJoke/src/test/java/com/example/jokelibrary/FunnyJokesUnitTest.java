package com.example.jokelibrary;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FunnyJokesUnitTest {


    /**
     * tellAJoke returns a random joke. For its testing we ensure it returns a String object
     * and that the String is not empty
     * We assume the String returned is a joke
     */

    @Test
    public void testGetJokes() {

        Object joke = FunnyJokes.tellAJoke();

        assertThat(joke, instanceOf(String.class));

        assertFalse(((String) joke).isEmpty());
    }

}
