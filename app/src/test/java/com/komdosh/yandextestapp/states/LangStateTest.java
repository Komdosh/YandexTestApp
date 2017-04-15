package com.komdosh.yandextestapp.states;

import com.komdosh.yandextestapp.data.model.Lang;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * @author komdosh
 *         created on 26.03.17.
 */
public class LangStateTest {

	@InjectMocks
	LangState langState = new LangState();

	@Test
	public void getLangDir() throws Exception {
		assertThat(langState.getLangDir(), is("ru-en"));
	}

	@Test
	public void switchLangs() throws Exception {
		assertThat(langState.getLangDir(), is("ru-en"));
		langState.switchLangs();
		assertThat(langState.getLangDir(), is("en-ru"));
	}

	@Before
	public void init() {
		langState.setSourceLang(new Lang("ru", "Russian"));
		langState.setTargetLang(new Lang("en", "English"));
	}
}