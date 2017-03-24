package com.komdosh.yandextestapp.data.dto;

import java.util.List;

/**
 * @author komdosh
 *         created on 19.03.17
 */
public class DicTranslatedDto {
	private String text;
	private String pos;
	private List<TextObject> syn;
	private List<TextObject> mean;
	private List<DicExample> ex;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public List<TextObject> getSyn() {
		return syn;
	}

	public void setSyn(List<TextObject> syn) {
		this.syn = syn;
	}

	public List<TextObject> getMean() {
		return mean;
	}

	public void setMean(List<TextObject> mean) {
		this.mean = mean;
	}

	public List<DicExample> getEx() {
		return ex;
	}

	public void setEx(List<DicExample> ex) {
		this.ex = ex;
	}

	@Override
	public String toString() {
		return "DicTranslatedDto{" +
				"text='" + text + '\'' +
				", pos='" + pos + '\'' +
				", syn=" + syn +
				", mean=" + mean +
				", ex=" + ex +
				'}';
	}
}
