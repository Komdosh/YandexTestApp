package com.komdosh.yandextestapp.utils;

import android.view.View;

import java.util.List;

/**
 * Created by komdosh on 19.03.17.
 */

public class ViewListUtils {
	private ViewListUtils() {
	}

	public static void setVisibilityToList(List<View> views, int visibility) {
		for (View v : views) {
			v.setVisibility(visibility);
		}
	}
}
