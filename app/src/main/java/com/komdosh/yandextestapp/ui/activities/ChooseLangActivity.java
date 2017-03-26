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
import android.widget.Toast;

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

/**
 * @author komdosh
 *         created on 19.03.17
 */

public class ChooseLangActivity extends AppCompatActivity {
	public static final String INTENT_REQUEST_TYPE_OF_LANG_KEY = "TypeOfLang";
	public static final String INTENT_RESULT_LANGUAGE_KEY = "language";
	private static final String TAG = "ChooseLangActivity";
	List<Lang> langs = new ArrayList<>();

	@BindView(R.id.langRecyclerView)
	RecyclerView langRecyclerView;

	@BindView(R.id.languageActivityToolbar)
	Toolbar languageActivityToolbar;

	private LanguageAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_language);
		ButterKnife.bind(this);

		setupToolbar();
		setupRecyclerView();

		if (langs.isEmpty()) {
			getLangs();
		}
	}

	public void getLangs() {
		TranslateAPI apiService = TranslateAPIClient.getClient().create(TranslateAPI.class);
		final String lang;
		if ("ru".matches(Locale.getDefault().getLanguage())) {
			lang = "ru";
		} else {
			lang = "en";
		}
		apiService.getLangs(getString(R.string.Yandex_Translate_API), lang)
				.enqueue(new Callback<LangsDto>() {
					@Override
					public void onResponse(Call<LangsDto> call, Response<LangsDto> response) {
						if (response.body() != null) {
							for (Map.Entry<String, String> entry : response.body().getLangs().entrySet()) {
								langs.add(new Lang(entry.getKey(), entry.getValue()));
							}
							adapter.notifyDataSetChanged();
						}
					}

					@Override
					public void onFailure(Call<LangsDto> call, Throwable t) {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(ChooseLangActivity.this, getString(R.string.networkProblem), Toast
										.LENGTH_SHORT).show();
							}
						});
					}
				});
	}

	private void setupRecyclerView() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		langRecyclerView.setLayoutManager(layoutManager);
		langRecyclerView.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));
		langRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {
				Intent intent = new Intent();
				intent.putExtra(INTENT_RESULT_LANGUAGE_KEY, adapter.getItem(position));
				setResult(RESULT_OK, intent);
				finish();
			}
		}));

		adapter = new LanguageAdapter(langs);
		langRecyclerView.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}

	private void setupToolbar() {
		switch (getIntent().getIntExtra(INTENT_REQUEST_TYPE_OF_LANG_KEY, 0)) {
			case TranslationFragment.SOURCE_LANGUAGE_REQUEST:
				languageActivityToolbar.setTitle(getString(R.string.selectSrcLanguageTitle));
				break;
			case TranslationFragment.TARGET_LANGUAGE_REQUEST:
				languageActivityToolbar.setTitle(getString(R.string.selectDstLanguageTitle));
				break;
			default:
				finish();
		}
		setSupportActionBar(languageActivityToolbar);
		try {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		} catch (NullPointerException e) {
			Log.e(TAG, e.toString());
		}
	}
}

