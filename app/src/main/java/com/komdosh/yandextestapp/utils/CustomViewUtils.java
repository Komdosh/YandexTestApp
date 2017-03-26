package com.komdosh.yandextestapp.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.List;

/**
 * @author komdosh
 *         created on 23.03.17.
 */

public class CustomViewUtils {
	private CustomViewUtils() {
	}

	public static void hideSoftKeyboard(Activity activity, EditText editText) {
		InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
	}

	public static void setVisibilityToList(List<View> views, int visibility) {
		for (View v : views) {
			v.setVisibility(visibility);
		}
	}
}
