package com.udacity.builditbigger;

import android.content.pm.ActivityInfo;
import android.os.SystemClock;
import android.widget.NumberPicker;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.udacity.gradle.builditbigger.AdViewIdlingResource;
import com.udacity.gradle.builditbigger.MainActivityFragmentFree;
import com.udacity.gradle.builditbigger.MainActivityFree;
import com.udacity.gradle.builditbigger.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)

public class MainActivityFreeTest {

    private AdViewIdlingResource mAdResource;

    @Rule
    public final ActivityTestRule<MainActivityFree> mMainFreeActivity =
            new ActivityTestRule<>(MainActivityFree.class);

    /**
     * Loads the idling resources needed to test the ads
     */
    @Before
    public void setUp() {
        MainActivityFragmentFree m = (MainActivityFragmentFree)
                mMainFreeActivity.getActivity().getSupportFragmentManager().getFragments().get(0);

        mAdResource = new AdViewIdlingResource(m.getAdView());
        IdlingRegistry.getInstance().register(mAdResource);
    }

    /**
     * Unloads the idling resources
     */
    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(mAdResource);
    }

    /**
     * Checks that the info in screen is correct (do not test the ads)
     */
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
    }

    /**
     * Same as freeFlavourShowsCorrectInfo but after rotating the activity
     */
    @Test
    public void freeFlavourShowsCorrectInfo_AfterRotation() {
        mMainFreeActivity.getActivity().
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        freeFlavourShowsCorrectInfo();
    }

    /**
     * Check both ads, AdView and InterstitialAd are displayed. Also tests JokeActivity is launched
     * after closing InterstitialAd.
     *
     * Based in https://github.com/firebase/quickstart-android/blob/master/admob/app/src/
     * androidTest/java/com/google/samples/quickstart/admobexample/InterstitialAdTest.java
     *
     * Please be aware that the tests related to ads may fail due to timing issues.
     * if so, rerun the tests.
     */

    @Test
    public void adsAreShown_JokeActivityLaunched(){

        // Wait to load
        mAdResource.setIsLoadingAd(true);

        // Confirm that banner ad appears
        onView(withId(R.id.adView)).check(matches(isDisplayed()));

        // Click show interstitial button
        onView(withId(R.id.jokeButton)).perform(click());

        // Check add is there
        onView(withContentDescription("Interstitial close button")).check(matches(isEnabled()));

         // Close
        onView(isRoot()).perform(ViewActions.pressBack());

        // Joke Activity is launched
        onView(withId(R.id.shareText)).check(matches(isDisplayed()));
    }

}