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
	HistoryState historyState = HistoryState.getInstance();

	@Test
	public void setNotifyState() throws Exception {
		historyState.setNotifyState();
		assertThat(historyState.readAndClearNotifyState(), is(1));
	}

	@Test
	public void resetNotifyState() throws Exception {
		historyState.setNotifyState();
		historyState.resetNotifyState();
		assertThat(historyState.readAndClearNotifyState(), is(0));
	}

	@Test
	public void readAndClearNotifyState() throws Exception {
		historyState.setNotifyState();
		assertThat(historyState.readAndClearNotifyState(), is(1));
		assertThat(historyState.readAndClearNotifyState(), is(0));
	}

}