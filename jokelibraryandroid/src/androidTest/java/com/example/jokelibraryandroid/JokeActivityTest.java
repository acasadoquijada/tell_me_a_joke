package com.example.jokelibraryandroid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.jokelibraryandroid.JokeActivity.JOKE_KEY;

@RunWith(AndroidJUnit4.class)
public class JokeActivityTest {


    private static final String testJoke = "I'm a text joke!!";

    @Rule
    public final ActivityTestRule<JokeActivity> jokeActivityActivityTestRule =
            new ActivityTestRule<JokeActivity>(JokeActivity.class) {
                @Override
                protected Intent getActivityIntent() {
                    Context targetContext = InstrumentationRegistry.getInstrumentation()
                            .getTargetContext();
                    Intent result = new Intent(targetContext, JokeActivity.class);
                    result.putExtra(JOKE_KEY, testJoke);
                    return result;
                }
            };

    @Test
    public void jokeActivityIsDisplayed(){

        onView(withId(R.id.jokeLabel)).check(matches(isDisplayed()));

        onView(withId(R.id.jokeText)).check(matches(isDisplayed()));
    }

    @Test
    public void jokeActivityIsDisplayed_AfterRotation(){
        jokeActivityActivityTestRule.getActivity().
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        jokeActivityIsDisplayed();
    }

    @Test
    public void jokeActivityShowsCorrectJoke(){
        onView(withId(R.id.jokeText)).check(matches(withText(testJoke)));
    }

    @Test
    public void jokeActivityShowsCorrectJoke_AfterRotation(){
        jokeActivityActivityTestRule.getActivity().
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        jokeActivityShowsCorrectJoke();
    }

}
