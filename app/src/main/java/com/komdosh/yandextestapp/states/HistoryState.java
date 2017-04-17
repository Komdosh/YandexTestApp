package com.komdosh.yandextestapp.states;

/**
 * @author komdosh
 *         created on 23.03.17.
 */

public class HistoryState {
	private int state;

	public HistoryState() {
		state = 0;
	}

	public void setNotifyState() {
		state = 1;
	}

	public void resetNotifyState() {
		state = 0;
	}

	public boolean isNotifyState() {
		return state == 1;
	}
}
