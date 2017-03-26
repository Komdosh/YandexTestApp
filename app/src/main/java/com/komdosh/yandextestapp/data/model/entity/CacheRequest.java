package com.komdosh.yandextestapp.data.model.entity;

import com.komdosh.yandextestapp.data.dto.DictionaryDto;
import com.komdosh.yandextestapp.data.dto.TranslateDto;
import com.komdosh.yandextestapp.data.model.converters.DictionaryDtoConverter;
import com.komdosh.yandextestapp.data.model.converters.TranslateDtoConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

import java.util.Date;

/**
 * @author komdosh
 *         created on 26.03.17.
 */
@Entity
public class CacheRequest {
	@Id
	String textToTranslate;
	@Unique
	String langDir;
	@Convert(converter = TranslateDtoConverter.class, columnType = String.class)
	TranslateDto translateDto;
	@Convert(converter = DictionaryDtoConverter.class, columnType = String.class)
	DictionaryDto dictionaryDto;
	Date date;

	@Generated(hash = 1079683927)
	public CacheRequest(String textToTranslate, String langDir,
	                    TranslateDto translateDto, DictionaryDto dictionaryDto, Date date) {
		this.textToTranslate = textToTranslate;
		this.langDir = langDir;
		this.translateDto = translateDto;
		this.dictionaryDto = dictionaryDto;
		this.date = date;
	}

	@Generated(hash = 203054966)
	public CacheRequest() {
	}

	public String getTextToTranslate() {
		return this.textToTranslate;
	}

	public void setTextToTranslate(String textToTranslate) {
		this.textToTranslate = textToTranslate;
	}

	public String getLangDir() {
		return this.langDir;
	}

	public void setLangDir(String langDir) {
		this.langDir = langDir;
	}

	public TranslateDto getTranslateDto() {
		return this.translateDto;
	}

	public void setTranslateDto(TranslateDto translateDto) {
		this.translateDto = translateDto;
	}

	public DictionaryDto getDictionaryDto() {
		return this.dictionaryDto;
	}

	public void setDictionaryDto(DictionaryDto dictionaryDto) {
		this.dictionaryDto = dictionaryDto;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
