package com.komdosh.yandextestapp.states;

import com.komdosh.yandextestapp.data.dto.LangsDto;
import com.komdosh.yandextestapp.data.model.Lang;

import java.util.Locale;

/**
 * @author komdosh
 *         created on 19.03.17
 */

public class LangState {

	private LangsDto langsDtos;
	private Lang sourceLang;
	private Lang targetLang;

	public LangState() {
		initLangs();
	}

	public String getLangDir() {
		if (sourceLang != null && targetLang != null) {
			return sourceLang.getCode() +
					"-" +
					targetLang.getCode();
		} else {
			return null;
		}
	}

	private void initLangs() {
		if (Locale.getDefault().getLanguage().matches("ru")) {
			sourceLang = new Lang("ru", "Русский");
			targetLang = new Lang("en", "Английский");
		} else {
			sourceLang = new Lang("ru", "Russian");
			targetLang = new Lang("en", "English");
		}
	}

	public void switchLangs() {
		Lang temp = sourceLang;
		sourceLang = targetLang;
		targetLang = temp;
	}

	public LangsDto getLangsDtos() {
		return langsDtos;
	}

	public void setLangsDtos(LangsDto langsDtos) {
		this.langsDtos = langsDtos;
	}

	public Lang getSourceLang() {
		return sourceLang;
	}

	public void setSourceLang(Lang sourceLang) {
		this.sourceLang = sourceLang;
	}

	public Lang getTargetLang() {
		return targetLang;
	}

	public void setTargetLang(Lang targetLang) {
		this.targetLang = targetLang;
	}
}
