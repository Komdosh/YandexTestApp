package com.komdosh.yandextestapp.request;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Komdosh
 * @since 19.03.2017
 */

public class TranslateAPIClient {
	private static final String BASE_URL = "https://translate.yandex.net/api/v1.5/tr.json/";

	private static Retrofit retrofit = null;

	private TranslateAPIClient() {
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
