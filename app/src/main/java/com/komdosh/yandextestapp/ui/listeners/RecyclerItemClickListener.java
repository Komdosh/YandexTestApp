package com.komdosh.yandextestapp.ui.listeners;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

//This class used for implement click on RecyclerView item for example on history tab
public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
	private OnItemClickListener mListener;
	private GestureDetector mGestureDetector;

	public RecyclerItemClickListener(Context context, OnItemClickListener listener) {
		mListener = listener;
		mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
			@Override
			public boolean onSingleTapUp(MotionEvent e) {
				return true;
			}
		});
	}

	@Override
	public void onRequestDisallowInterceptTouchEvent(final boolean disallowIntercept) {
		//Need not to disallow touch events
	}

	@Override
	public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
		View childView = view.findChildViewUnder(e.getX(), e.getY());
		if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
			mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
		}
		return false;
	}

	@Override
	public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
		//Need not to capture touch events
	}

	public interface OnItemClickListener {
		void onItemClick(View view, int position);
	}
}