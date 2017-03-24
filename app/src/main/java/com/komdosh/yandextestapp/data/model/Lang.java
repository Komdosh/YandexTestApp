package com.komdosh.yandextestapp.data.model;

import java.io.Serializable;

/**
 * @author komdosh
 *         created on 19.03.17
 */

public class Lang implements Serializable {
	private String code;
	private String name;

	public Lang(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
