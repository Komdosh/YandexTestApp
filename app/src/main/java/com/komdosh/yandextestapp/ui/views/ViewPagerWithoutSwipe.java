package com.komdosh.yandextestapp.ui.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author komdosh
 *         created on 23.03.17.
 */

public class ViewPagerWithoutSwipe extends ViewPager {
	public ViewPagerWithoutSwipe(Context context) {
		super(context);
	}

	public ViewPagerWithoutSwipe(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		// Never allow swiping to switch between pages
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// Never allow swiping to switch between pages
		return false;
	}
}
