package com.example.jokelibraryandroid;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.example.jokelibraryandroid.JokeActivity.JOKE_KEY;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.AllOf.allOf;

public class JokeActivityIntentTest {

    private static final String testJoke = "I'm a text joke!!";

    @Rule
    public final IntentsTestRule<JokeActivity> jokeActivityActivityTestRule =
            new IntentsTestRule<JokeActivity>(JokeActivity.class) {
                @Override
                protected Intent getActivityIntent() {
                    Context targetContext = InstrumentationRegistry.getInstrumentation()
                            .getTargetContext();
                    Intent result = new Intent(targetContext, JokeActivity.class);
                    result.putExtra(JOKE_KEY, testJoke);
                    return result;
                }
            };

    @Before
    public void stubIntent(){

        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, null);

        intending(not(isInternal())).respondWith(result);
    }
    @Test
    public void clickShareButton_SharesJoke() {

        onView(withId(R.id.share)).perform(click());

        // Obtained from here:
        // https://groups.google.com/forum/#!topic/android-testing-support-library/Mj3tF5S7puU
        intended(allOf(hasAction(Intent.ACTION_CHOOSER),
                hasExtra(is(Intent.EXTRA_INTENT),
                        allOf( hasAction(Intent.ACTION_SEND),
                                hasExtra(Intent.EXTRA_TEXT, testJoke) ))));
    }

    @Test
    public void clickShareButton_SharesJoke_AfterRotation(){
        jokeActivityActivityTestRule.getActivity().
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        clickShareButton_SharesJoke();
    }
}
