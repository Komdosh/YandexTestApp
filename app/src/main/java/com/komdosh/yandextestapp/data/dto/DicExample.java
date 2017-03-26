package com.komdosh.yandextestapp.data.dto;

import java.util.List;

/**
 * @author komdosh
 *         created on 19.03.17
 */

public class DicExample {
	private String text;
	private List<TextObject> tr;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<TextObject> getTr() {
		return tr;
	}

	public void setTr(List<TextObject> tr) {
		this.tr = tr;
	}

	@Override
	public String toString() {
		return text + " - " + tr.get(0);
	}
}
