package com.komdosh.yandextestapp.utils;

import android.support.test.InstrumentationRegistry;
import android.view.View;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author komdosh
 *         created on 26.03.17.
 */
public class CustomViewUtilsTest {
	@Test
	public void setVisibilityToList() throws Exception {
		List<View> views = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			views.add(new View(InstrumentationRegistry.getContext()));
		}

		CustomViewUtils.setVisibilityToList(views, View.INVISIBLE);

		for (View v : views) {
			assertThat(v.getVisibility(), is(View.INVISIBLE));
		}
	}

}