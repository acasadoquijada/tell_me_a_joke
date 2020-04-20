package com.udacity.builditbigger;

import android.content.pm.ActivityInfo;

import androidx.test.rule.ActivityTestRule;

import com.udacity.gradle.builditbigger.MainActivitySource;
import com.udacity.gradle.builditbigger.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(JUnit4.class)
public class MainActivityTest {
    @Rule
    public final ActivityTestRule<MainActivitySource> mMainActivityRule =
            new ActivityTestRule<>(MainActivitySource.class);

    /**
     * Checks the elements are shown correctly in the Activity
     */
    @Test
    public void sourceFlavorShowsCorrectInfo() {

        // Text presenting the joke button
        onView(allOf(
                withId(R.id.instructions_text_view),
                isDescendantOfA(withId(R.id.fragment)))).check(matches(isDisplayed()));

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
    }

    /**
     * Same as sourceFlavorShowsCorrectInfo but after rotating the Activity
     */
    @Test
    public void sourceFlavorShowsCorrectInfo_AfterRotation() {
        mMainActivityRule.getActivity().
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        sourceFlavorShowsCorrectInfo();
    }
}
