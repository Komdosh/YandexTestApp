package com.komdosh.yandextestapp.data.model.converters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.komdosh.yandextestapp.data.dto.DictionaryDto;

import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * @author komdosh
 *         created on 23.03.17.
 */

public class DictionaryDtoConverter implements PropertyConverter<DictionaryDto, String> {

	@Override
	public DictionaryDto convertToEntityProperty(String databaseValue) {
		if (databaseValue == null) {
			return null;
		} else {
			return new Gson().fromJson(databaseValue, new TypeToken<DictionaryDto>() {
			}.getType());
		}
	}

	@Override
	public String convertToDatabaseValue(DictionaryDto entityProperty) {
		if (entityProperty == null) {
			return null;
		} else {
			return new Gson().toJson(entityProperty);
		}
	}
}
