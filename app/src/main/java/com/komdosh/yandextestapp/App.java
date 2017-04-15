package com.komdosh.yandextestapp;

import android.app.Application;

import com.komdosh.yandextestapp.provider.components.AppComponent;
import com.komdosh.yandextestapp.provider.components.DaggerAppComponent;
import com.komdosh.yandextestapp.provider.modules.AppModule;
import com.komdosh.yandextestapp.provider.modules.DbModule;
import com.komdosh.yandextestapp.provider.modules.HistoryModule;
import com.komdosh.yandextestapp.provider.modules.LangModule;

import java.io.Serializable;

/**
 * @author komdosh
 *         created on 16.03.17
 */

public class App extends Application implements Serializable {

	private static AppComponent component;

	public static AppComponent getComponent() {
		return component;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		initDaggerComponent();
	}

	private void initDaggerComponent() {
		component = DaggerAppComponent.builder()
				.appModule(new AppModule(this))
				.historyModule(new HistoryModule())
				.langModule(new LangModule())
				.dbModule(new DbModule(this))
				.build();
	}
}
