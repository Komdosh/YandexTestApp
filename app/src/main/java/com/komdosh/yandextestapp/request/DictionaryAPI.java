package com.komdosh.yandextestapp.request;

import com.komdosh.yandextestapp.data.dto.DictionaryDto;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author Komdosh
 * @since 19.03.2017
 */

public interface DictionaryAPI {
	@POST("lookup")
	Call<DictionaryDto> translate(@Query("key") String key, @Query("text") String text,
	                              @Query("lang") String lang);
}
