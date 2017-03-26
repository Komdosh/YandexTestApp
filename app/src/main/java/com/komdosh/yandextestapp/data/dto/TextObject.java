package com.komdosh.yandextestapp.data.dto;

/**
 * @author komdosh
 *         created on 19.03.17
 */
public class TextObject {
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}
