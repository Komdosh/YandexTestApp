package com.komdosh.yandextestapp.utils;

import android.util.Log;

import com.komdosh.yandextestapp.data.dto.DictionaryDto;
import com.komdosh.yandextestapp.data.dto.TranslateDto;
import com.komdosh.yandextestapp.data.model.entity.CacheRequest;
import com.komdosh.yandextestapp.data.model.entity.CacheRequestDao;
import com.komdosh.yandextestapp.data.model.entity.DaoSession;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author komdosh
 *         created on 26.03.17.
 */

public class CustomCache {

	private CacheRequestDao cacheRequestDao;

	public CustomCache(DaoSession daoSession) {
		cacheRequestDao = daoSession.getCacheRequestDao();
	}

	public void addOrUpdate(String textToTranslate, String langDir, TranslateDto translateDto,
	                        DictionaryDto dictionaryDto) {
		CacheRequest cacheRequest = cacheRequestDao.load(textToTranslate);
		if (cacheRequest == null) {
			cacheRequest = new CacheRequest(textToTranslate, langDir, translateDto, dictionaryDto, new Date());
		} else {
			if (translateDto != null) {
				cacheRequest.setTranslateDto(translateDto);
			}
			if (dictionaryDto != null) {
				cacheRequest.setDictionaryDto(dictionaryDto);
			}
		}
/*		if(!cacheRequestDao.queryBuilder().where(CacheRequestDao.Properties.TextToTranslate.eq
				(translateDto))
				.list().isEmpty()){
			cacheRequestDao.update(cacheRequest);
		} else {
			cacheRequestDao.insert(cacheRequest);
		}*/

		cacheRequestDao.insertOrReplace(cacheRequest);
	}

	public void update(CacheRequest cacheRequest) {
		cacheRequest.setDate(new Date());
		cacheRequestDao.update(cacheRequest);
	}

	public CacheRequest getFromCache(String textToTranslate) {
		return cacheRequestDao.load(textToTranslate);
	}

	public void invalidateOld() {
		Log.d("Invalidate", cacheRequestDao.queryBuilder().where(CacheRequestDao.Properties.Date.le(new
				Date().getTime() - TimeUnit.MINUTES.toMillis(2))).list().toString());
		cacheRequestDao.queryBuilder().where(CacheRequestDao.Properties.Date.le(new
				Date().getTime() - TimeUnit.HOURS.toMillis(1))).buildDelete()
				.executeDeleteWithoutDetachingEntities();
	}
}
