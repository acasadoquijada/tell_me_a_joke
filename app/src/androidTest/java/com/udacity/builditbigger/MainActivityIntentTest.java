package com.udacity.builditbigger;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.pm.ActivityInfo;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.jokelibraryandroid.JokeActivity;
import com.udacity.gradle.builditbigger.MainActivity;
import com.udacity.gradle.builditbigger.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static androidx.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class MainActivityIntentTest {

    @Rule
    public IntentsTestRule<MainActivity> mainActivityIntentsTestRule =
            new IntentsTestRule<>(MainActivity.class);

    @Before
    public void stubIntent(){
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, null);

        intending(not(isInternal())).respondWith(result);
    }

    @Test
    public void clickOnButton_CreatesCorrectIntentInfo(){

        onView(withId(R.id.jokeButton)).perform(click());

        intended(hasExtraWithKey(JokeActivity.JOKE_KEY));
    }

    @Test
    public void clickOnButton_CreatesCorrectIntentInfo_AfterRotation() {

        mainActivityIntentsTestRule.getActivity().
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        clickOnButton_CreatesCorrectIntentInfo();
    }
}
