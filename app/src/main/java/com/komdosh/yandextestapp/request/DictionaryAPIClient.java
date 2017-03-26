package com.komdosh.yandextestapp.request;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Komdosh
 *         created on 19.03.2017
 */

public class DictionaryAPIClient {
	private static final String BASE_URL = "https://dictionary.yandex.net/api/v1/dicservice.json/";

	private static Retrofit retrofit = null;

	private DictionaryAPIClient() {
	}

	public static Retrofit getClient() {
		if (retrofit == null) {
			retrofit = new Retrofit.Builder()
					.baseUrl(BASE_URL)
					.addConverterFactory(GsonConverterFactory.create())
					.build();
		}
		return retrofit;
	}
}
