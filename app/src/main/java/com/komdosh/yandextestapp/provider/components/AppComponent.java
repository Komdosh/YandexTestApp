package com.komdosh.yandextestapp.provider.components;

import com.komdosh.yandextestapp.provider.modules.AppModule;
import com.komdosh.yandextestapp.provider.modules.DbModule;
import com.komdosh.yandextestapp.provider.modules.HistoryModule;
import com.komdosh.yandextestapp.provider.modules.LangModule;
import com.komdosh.yandextestapp.ui.activities.MainActivity;
import com.komdosh.yandextestapp.ui.adapters.DictionaryRecyclerViewAdapter;
import com.komdosh.yandextestapp.ui.adapters.HistoryRecyclerViewAdapter;
import com.komdosh.yandextestapp.ui.fragments.HistoryFavoriteFragment;
import com.komdosh.yandextestapp.ui.fragments.TranslationFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Komdosh
 *         created on 15.04.2017.
 */

@Singleton
@Component(modules = {AppModule.class, HistoryModule.class, LangModule.class, DbModule.class})
public interface AppComponent {
	void inject(MainActivity mainActivity);

	void inject(HistoryRecyclerViewAdapter historyRecyclerViewAdapter);

	void inject(HistoryFavoriteFragment historyFavoriteFragment);

	void inject(TranslationFragment translationFragment);

	void inject(DictionaryRecyclerViewAdapter dictionaryRecyclerViewAdapter);
}