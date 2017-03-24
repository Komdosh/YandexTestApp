package com.komdosh.yandextestapp.request;

import com.komdosh.yandextestapp.data.dto.DetectDto;
import com.komdosh.yandextestapp.data.dto.LangsDto;
import com.komdosh.yandextestapp.data.dto.TranslateDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author Komdosh
 * @since 19.03.2017
 */

public interface TranslateAPI {
	@GET("getLangs")
	Call<LangsDto> getLangs(@Query("key") String key, @Query("ui") String ui);

	@GET("detect")
	Call<DetectDto> detect(@Query("key") String key, @Query("text") String text);

	@POST("translate")
	Call<TranslateDto> translate(@Query("key") String key, @Query("text") String text,
	                             @Query("lang") String lang);
}
