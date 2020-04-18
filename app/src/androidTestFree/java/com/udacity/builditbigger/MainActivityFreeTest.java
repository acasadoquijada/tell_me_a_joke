package com.udacity.builditbigger;

import android.content.pm.ActivityInfo;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.udacity.gradle.builditbigger.MainActivityFree;
import com.udacity.gradle.builditbigger.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)

public class MainActivityFreeTest {

    @Rule
    public final ActivityTestRule<MainActivityFree> mMainFreeActivity =
            new ActivityTestRule<>(MainActivityFree.class);

    @Test
    public void freeFlavourShowsCorrectInfo() {

        // Text presenting the joke button
        onView(allOf(withId(R.id.instructions_text_view), isDescendantOfA(withId(R.id.fragment)))).check(matches(isDisplayed()));

        // Jester icon
        onView(allOf(
                withId(R.id.jokeIcon),
                isDescendantOfA(withId(R.id.jokeButton)),
                isDescendantOfA(withId(R.id.fragment)))).check(matches(isDisplayed()));

        // Jester bottom text
        onView(allOf(
                withId(R.id.jokeButtonText),
                isDescendantOfA(withId(R.id.jokeButton)),
                isDescendantOfA(withId(R.id.fragment)))).check(matches(isDisplayed()));

//        onView(withId(R.id.adView)).check(matches(isDisplayed()));

        // jokeButtonText

        // add delay
//        onView(withId(R.id.adView)).check(matches(isDisplayed()));

    }

    @Test
    public void freeFlavourShowsCorrectInfo_AfterRotation() {
        mMainFreeActivity.getActivity().
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        freeFlavourShowsCorrectInfo();
    }
}