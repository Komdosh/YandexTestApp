package com.komdosh.yandextestapp.data.dto;

import java.util.List;

/**
 * @author komdosh
 *         created on 19.03.17
 */

public class DefinitionDto {
	private String text;
	private String pos;
	private String ts;
	private List<DicTranslatedDto> tr;

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

	public String getTs() {
		return ts;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

	public List<DicTranslatedDto> getTr() {
		return tr;
	}

	public void setTr(List<DicTranslatedDto> tr) {
		this.tr = tr;
	}

	@Override
	public String toString() {
		return "DefinitionDto{" +
				"text='" + text + '\'' +
				", pos='" + pos + '\'' +
				", ts='" + ts + '\'' +
				", tr=" + tr +
				'}';
	}
}
