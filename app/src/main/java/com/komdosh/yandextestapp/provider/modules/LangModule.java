package com.komdosh.yandextestapp.provider.modules;

import com.komdosh.yandextestapp.states.LangState;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Komdosh
 *         created on 15.04.2017.
 */
@Module
public class LangModule {
	private LangState langState;

	public LangModule() {
		this.langState = new LangState();
	}

	@Provides
	@Singleton
	LangState provideLangState() {
		return langState;
	}
}
