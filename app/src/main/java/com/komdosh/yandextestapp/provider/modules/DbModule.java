package com.komdosh.yandextestapp.provider.modules;

import android.app.Application;
import android.support.annotation.NonNull;

import com.komdosh.yandextestapp.data.model.entity.DaoMaster;
import com.komdosh.yandextestapp.data.model.entity.DaoSession;

import org.greenrobot.greendao.database.Database;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author komdosh
 *         created on 15.04.17.
 */

@Module
public class DbModule {
	private DaoSession daoSession;

	public DbModule(@NonNull Application app) {
		DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(app, "yandex_translate_db");
		Database db = helper.getWritableDb();
		daoSession = new DaoMaster(db).newSession();
	}

	@Provides
	@Singleton
	DaoSession provideDaoSession() {
		return daoSession;
	}
}
