package com.komdosh.yandextestapp.data.dto;

import java.util.List;

/**
 * @author komdosh
 *         created on 19.03.17
 */

public class DictionaryDto {
	//Very strange structure of API
	private List<DefinitionDto> def;

	public List<DefinitionDto> getDef() {
		return def;
	}

	public void setDef(List<DefinitionDto> def) {
		this.def = def;
	}

	@Override
	public String toString() {
		return "DictionaryDto{" +
				"def=" + def +
				'}';
	}
}
