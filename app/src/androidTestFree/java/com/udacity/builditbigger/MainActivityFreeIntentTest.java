package com.udacity.builditbigger;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.pm.ActivityInfo;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.jokelibraryandroid.JokeActivity;
import com.udacity.gradle.builditbigger.AdViewIdlingResource;
import com.udacity.gradle.builditbigger.MainActivityFragmentFree;
import com.udacity.gradle.builditbigger.MainActivityFree;
import com.udacity.gradle.builditbigger.MainActivitySource;
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
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static androidx.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class MainActivityFreeIntentTest {

    private AdViewIdlingResource mAdResource;

    @Rule
    public IntentsTestRule<MainActivityFree> mainActivityIntentsTestRule =
            new IntentsTestRule<>(MainActivityFree.class);

    @Before
    public void stubIntent(){
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, null);

        intending(not(isInternal())).respondWith(result);
    }

    /**
     * Loads the idling resources needed to test the ads
     */
    @Before
    public void setUp() {
        MainActivityFragmentFree m = (MainActivityFragmentFree)
                mainActivityIntentsTestRule.getActivity().getSupportFragmentManager().getFragments().get(0);

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
     * Checks that the intent information created when pressing the joke button is correct
     *
     * We need to ensure that the intent is created after closing the interstitial ad
     */
    @Test
    public void clickOnButton_CreatesCorrectIntentInfo(){

        mainActivityIntentsTestRule.getActivity().
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // Wait to load
        mAdResource.setIsLoadingAd(true);

        onView(withId(R.id.jokeButton)).perform(click());

        onView(withContentDescription("Interstitial close button")).check(matches(isEnabled()));
        onView(isRoot()).perform(ViewActions.pressBack());

        intended(hasExtra(is(JokeActivity.JOKE_KEY),not(isEmptyString())));

    }

    /**
     * Same as clickOnButton_CreatesCorrectIntentInfo but after rotating the Activity
     */
    @Test
    public void clickOnButton_CreatesCorrectIntentInfo_AfterRotation() {

        mainActivityIntentsTestRule.getActivity().
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Wait to load
        mAdResource.setIsLoadingAd(true);

        onView(withId(R.id.jokeButton)).perform(click());

        onView(withContentDescription("Interstitial close button")).check(matches(isEnabled()));
        onView(isRoot()).perform(ViewActions.pressBack());

        intended(hasExtra(is(JokeActivity.JOKE_KEY),not(isEmptyString())));

    }
}
