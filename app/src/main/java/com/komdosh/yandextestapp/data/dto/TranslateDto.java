package com.komdosh.yandextestapp.data.dto;

import java.util.List;

/**
 * @author komdosh
 *         created on 19.03.17
 */

public class TranslateDto {
	private String code;
	private String lang;
	private List<String> text;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public List<String> getText() {
		return text;
	}

	public void setText(List<String> text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "TranslateDto{" +
				"code='" + code + '\'' +
				", lang='" + lang + '\'' +
				", text=" + text +
				'}';
	}
}
