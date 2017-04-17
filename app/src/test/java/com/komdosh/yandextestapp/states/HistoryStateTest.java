package com.komdosh.yandextestapp.states;

import org.junit.Test;
import org.mockito.InjectMocks;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author komdosh
 *         created on 26.03.17.
 */
public class HistoryStateTest {

	@InjectMocks
	private HistoryState historyState = new HistoryState();

	@Test
	public void setNotifyState() throws Exception {
		historyState.setNotifyState();
		assertThat(historyState.isNotifyState(), is(true));
	}

	@Test
	public void resetNotifyState() throws Exception {
		historyState.setNotifyState();
		historyState.resetNotifyState();
		assertThat(historyState.isNotifyState(), is(false));
	}
}