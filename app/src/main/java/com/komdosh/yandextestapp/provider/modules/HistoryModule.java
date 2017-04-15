package com.komdosh.yandextestapp.provider.modules;

import com.komdosh.yandextestapp.states.HistoryState;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Komdosh
 *         created on 15.04.2017.
 */
@Module
public class HistoryModule {
	private HistoryState historyState;

	public HistoryModule() {
		this.historyState = new HistoryState();
	}

	@Provides
	@Singleton
	HistoryState provideHistoryState() {
		return historyState;
	}
}
