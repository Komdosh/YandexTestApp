package com.komdosh.yandextestapp.data.dto;

/**
 * @author komdosh
 *         created on 19.03.17
 */
public class DetectDto {
	private String code;
	private String lang;

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

	@Override
	public String toString() {
		return "DetectDto{" +
				"code='" + code + '\'' +
				", lang='" + lang + '\'' +
				'}';
	}
}
