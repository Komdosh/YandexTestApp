package com.komdosh.yandextestapp.utils;

import com.komdosh.yandextestapp.data.dto.DictionaryDto;
import com.komdosh.yandextestapp.data.dto.TranslateDto;
import com.komdosh.yandextestapp.data.model.entity.CacheRequest;
import com.komdosh.yandextestapp.data.model.entity.CacheRequestDao;
import com.komdosh.yandextestapp.data.model.entity.DaoSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * @author komdosh
 *         created on 26.03.17.
 */

public class CustomCacheTest {

	@Mock
	CacheRequestDao cacheRequestDao;

	@InjectMocks
	CustomCache customCache = new CustomCache(mock(DaoSession.class));

	@Test
	public void addOrUpdate() throws Exception {
		CacheRequest cacheRequest = new CacheRequest("text", "ru-en", null, null, null);
		when(cacheRequestDao.load(anyString())).thenReturn(cacheRequest);
		when(cacheRequestDao.insertOrReplace(any(CacheRequest.class))).thenReturn(0L);

		customCache.addOrUpdate("text", "ru-en", new TranslateDto(), null);

		assertNotNull(cacheRequest.getTranslateDto());
		assertNull(cacheRequest.getDictionaryDto());

		customCache.addOrUpdate("text", "ru-en", null, new DictionaryDto());

		verify(cacheRequestDao, times(2)).load(eq("text"));
		verify(cacheRequestDao, times(2)).insertOrReplace(cacheRequest);
		verifyNoMoreInteractions(cacheRequestDao);
		assertNotNull(cacheRequest.getTranslateDto());
		assertNotNull(cacheRequest.getDictionaryDto());
	}

	@Test
	public void update() throws Exception {
		CacheRequest cacheRequest = new CacheRequest("text", "ru-en", new TranslateDto(), new
				DictionaryDto(), null);
		doNothing().when(cacheRequestDao).update(any(CacheRequest.class));

		customCache.update(cacheRequest);

		verify(cacheRequestDao, times(1)).update(cacheRequest);
		verifyNoMoreInteractions(cacheRequestDao);
		assertNotNull(cacheRequest.getDate());
	}

	@Before
	public void init() {
		when(mock(DaoSession.class).getCacheRequestDao()).thenReturn(mock(CacheRequestDao.class));
	}
}