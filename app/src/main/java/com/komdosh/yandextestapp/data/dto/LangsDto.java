package com.komdosh.yandextestapp.data.dto;

import java.util.List;
import java.util.Map;

/**
 * @author komdosh
 *         created on 19.03.17
 */
public class LangsDto {
	private List<String> dirs;
	private Map<String, String> langs;

	public List<String> getDirs() {
		return dirs;
	}

	public void setDirs(List<String> dirs) {
		this.dirs = dirs;
	}

	public Map<String, String> getLangs() {
		return langs;
	}

	public void setLangs(Map<String, String> langs) {
		this.langs = langs;
	}

	@Override
	public String toString() {
		return "LangsDto{" +
				"dirs=" + dirs +
				", langs=" + langs +
				'}';
	}
}
