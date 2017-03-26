package com.komdosh.yandextestapp.data.model.converters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.komdosh.yandextestapp.data.model.Lang;

import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * @author komdosh
 *         created on 23.03.17.
 */

public class LangConverter implements PropertyConverter<Lang, String> {

	@Override
	public Lang convertToEntityProperty(String databaseValue) {
		if (databaseValue == null) {
			return null;
		} else {
			return new Gson().fromJson(databaseValue, new TypeToken<Lang>() {
			}.getType());
		}
	}

	@Override
	public String convertToDatabaseValue(Lang entityProperty) {
		if (entityProperty == null) {
			return null;
		} else {
			return new Gson().toJson(entityProperty);
		}
	}
}
