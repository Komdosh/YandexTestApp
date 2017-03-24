package com.komdosh.yandextestapp.states;

/**
 * @author komdosh
 *         created on 23.03.17.
 */

public class HistoryState {
	private static final HistoryState ourInstance = new HistoryState();

	private int state;

	private HistoryState() {
		state = 0;
	}

	public static HistoryState getInstance() {
		return ourInstance;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
