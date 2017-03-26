package com.komdosh.yandextestapp;

import android.app.Application;

import com.komdosh.yandextestapp.data.model.entity.DaoMaster;
import com.komdosh.yandextestapp.data.model.entity.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * @author komdosh
 *         created on 16.03.17
 */

public class App extends Application {

	private DaoSession daoSession;

	@Override
	public void onCreate() {
		super.onCreate();

		DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "yandex_translate_db");
		Database db = helper.getWritableDb();
		daoSession = new DaoMaster(db).newSession();
	}

	public DaoSession getDaoSession() {
		return daoSession;
	}
}
