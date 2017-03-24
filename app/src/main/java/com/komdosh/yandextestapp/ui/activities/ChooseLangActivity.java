package com.komdosh.yandextestapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.komdosh.yandextestapp.R;
import com.komdosh.yandextestapp.data.dto.LangsDto;
import com.komdosh.yandextestapp.data.model.Lang;
import com.komdosh.yandextestapp.request.TranslateAPI;
import com.komdosh.yandextestapp.request.TranslateAPIClient;
import com.komdosh.yandextestapp.ui.adapters.LanguageAdapter;
import com.komdosh.yandextestapp.ui.fragments.TranslationFragment;
import com.komdosh.yandextestapp.ui.listeners.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooseLangActivity extends AppCompatActivity {

	List<Lang> langs = new ArrayList<>();

	@BindView(R.id.langs)
	RecyclerView langRecyclerView;

	@BindView(R.id.languageActivityToolbar)
	Toolbar toolbar;

	LanguageAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_language);
		ButterKnife.bind(this);

		setupToolbar();
		setupRecylcerView();
		if (langs.isEmpty()) {
			getLangs();
		}
	}

	public void getLangs() {
		TranslateAPI apiService = TranslateAPIClient.getClient().create(TranslateAPI.class);
		final String lang;
		if (Locale.getDefault().getLanguage().matches("ru")) {
			lang = "ru";
		} else {
			lang = "en";
		}
		apiService.getLangs(getString(R.string.Yandex_Translate_API), lang).enqueue(new Callback<LangsDto>() {
			@Override
			public void onResponse(Call<LangsDto> call, Response<LangsDto> response) {
				if (response.body() != null) {
					LangsDto langsDto = response.body();
					for (Map.Entry<String, String> entry : langsDto.getLangs().entrySet()) {
						langs.add(new Lang(entry.getKey(), entry.getValue()));
					}
					adapter.notifyDataSetChanged();
				}
			}

			@Override
			public void onFailure(Call<LangsDto> call, Throwable t) {
				Log.e("Request error", t.toString());
			}
		});
	}

	private void setupRecylcerView() {
		adapter = new LanguageAdapter(langs, this);
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		langRecyclerView.setLayoutManager(layoutManager);
		langRecyclerView.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));
		langRecyclerView.setAdapter(adapter);
		langRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {

				Intent intent = new Intent();
				intent.putExtra("language", adapter.getItem(position));
				setResult(RESULT_OK, intent);
				finish();

			}
		}));

		adapter.notifyDataSetChanged();
	}

	private void setupToolbar() {
		switch (getIntent().getIntExtra("TypeOfLang", 0)) {
			case TranslationFragment.SOURCE_LANGUAGE_REQUEST:
				toolbar.setTitle(getString(R.string.selectSrcLanguageTitle));
				break;
			case TranslationFragment.TARGET_LANGUAGE_REQUEST:
				toolbar.setTitle(getString(R.string.selectDstLanguageTitle));
				break;
			default:
				finish();
		}
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}
}

