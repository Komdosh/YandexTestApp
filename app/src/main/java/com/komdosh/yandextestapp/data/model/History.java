package com.komdosh.yandextestapp.data.model;

import com.komdosh.yandextestapp.data.model.converters.LangConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;

/**
 * @author komdosh
 *         created on 16.03.17
 */

@Entity
public class History {
	@Id
	Long id;
	String text;
	String translatedText;
	@Convert(converter = LangConverter.class, columnType = String.class)
	Lang targetLang;
	@Convert(converter = LangConverter.class, columnType = String.class)
	Lang sourceLang;
	boolean favorite;

	Date date;

	@Generated(hash = 1057999277)
	public History(Long id, String text, String translatedText, Lang targetLang,
	               Lang sourceLang, boolean favorite, Date date) {
		this.id = id;
		this.text = text;
		this.translatedText = translatedText;
		this.targetLang = targetLang;
		this.sourceLang = sourceLang;
		this.favorite = favorite;
		this.date = date;
	}

	@Generated(hash = 869423138)
	public History() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTranslatedText() {
		return this.translatedText;
	}

	public void setTranslatedText(String translatedText) {
		this.translatedText = translatedText;
	}

	public Lang getTargetLang() {
		return this.targetLang;
	}

	public void setTargetLang(Lang targetLang) {
		this.targetLang = targetLang;
	}

	public Lang getSourceLang() {
		return this.sourceLang;
	}

	public void setSourceLang(Lang sourceLang) {
		this.sourceLang = sourceLang;
	}

	public boolean getFavorite() {
		return this.favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
