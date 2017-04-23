package com.komdosh.yandextestapp.ui.fragments;


import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.komdosh.yandextestapp.R;
import com.komdosh.yandextestapp.ui.activities.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;

/**
 * @author komdosh
 *         created on 21.04.17.
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TranslationFragmentTest {

	@Rule
	public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(
			MainActivity.class);
	private String textToTranslate;

	@Before
	public void init() {
		textToTranslate = "Hello";
	}

	@Test
	public void clearText() throws Exception {
		onView(withId(R.id.textToTranslate)).perform(typeText(textToTranslate))
				.check(matches(withText(textToTranslate)));

		onView(withId(R.id.playEditText)).check(matches(isDisplayed()));
		onView(withId(R.id.playTranslated)).check(matches(isDisplayed()));

		onView(withId(R.id.clearText)).perform(click());
		onView(withId(R.id.playEditText)).check(matches(not(isDisplayed())));
		onView(withId(R.id.playTranslated)).check(matches(not(isDisplayed())));
		onView(withId(R.id.textToTranslate)).check(matches(withText("")));
	}

	@Test
	public void editTextChanged() throws Exception {
		onView(withId(R.id.textToTranslate)).perform(typeText(textToTranslate))
				.check(matches(withText(textToTranslate)));

		onView(withId(R.id.playEditText)).check(matches(isDisplayed()));
		onView(withId(R.id.playTranslated)).check(matches(isDisplayed()));
	}
}