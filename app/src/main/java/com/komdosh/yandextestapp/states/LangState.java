package com.komdosh.yandextestapp.states;

import com.komdosh.yandextestapp.data.dto.LangsDto;
import com.komdosh.yandextestapp.data.model.Lang;

import java.util.Locale;

/**
 * @author komdosh
 *         created on 19.03.17
 */

public class LangState {

	private LangsDto langsDto;
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
			sourceLang = new Lang("en", "English");
			targetLang = new Lang("ru", "Russian");
		}
	}

	public void switchLangs() {
		Lang temp = sourceLang;
		sourceLang = targetLang;
		targetLang = temp;
	}

	public LangsDto getLangsDto() {
		return langsDto;
	}

	public void setLangsDto(LangsDto langsDto) {
		this.langsDto = langsDto;
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
