package com.komdosh.yandextestapp.data.model.converters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.komdosh.yandextestapp.data.dto.TranslateDto;

import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * @author komdosh
 *         created on 23.03.17.
 */

public class TranslateDtoConverter implements PropertyConverter<TranslateDto, String> {

	@Override
	public TranslateDto convertToEntityProperty(String databaseValue) {
		if (databaseValue == null) {
			return null;
		} else {
			return new Gson().fromJson(databaseValue, new TypeToken<TranslateDto>() {
			}.getType());
		}
	}

	@Override
	public String convertToDatabaseValue(TranslateDto entityProperty) {
		if (entityProperty == null) {
			return null;
		} else {
			return new Gson().toJson(entityProperty);
		}
	}
}
