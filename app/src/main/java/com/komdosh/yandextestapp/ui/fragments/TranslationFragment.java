package com.komdosh.yandextestapp.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.komdosh.yandextestapp.App;
import com.komdosh.yandextestapp.R;
import com.komdosh.yandextestapp.data.dto.DefinitionDto;
import com.komdosh.yandextestapp.data.dto.DictionaryDto;
import com.komdosh.yandextestapp.data.dto.TranslateDto;
import com.komdosh.yandextestapp.data.model.Lang;
import com.komdosh.yandextestapp.data.model.entity.CacheRequest;
import com.komdosh.yandextestapp.data.model.entity.DaoSession;
import com.komdosh.yandextestapp.data.model.entity.History;
import com.komdosh.yandextestapp.data.model.entity.HistoryDao;
import com.komdosh.yandextestapp.request.DictionaryAPI;
import com.komdosh.yandextestapp.request.DictionaryAPIClient;
import com.komdosh.yandextestapp.request.TranslateAPI;
import com.komdosh.yandextestapp.request.TranslateAPIClient;
import com.komdosh.yandextestapp.states.HistoryState;
import com.komdosh.yandextestapp.states.LangState;
import com.komdosh.yandextestapp.ui.activities.ChooseLangActivity;
import com.komdosh.yandextestapp.ui.activities.WordActivity;
import com.komdosh.yandextestapp.ui.adapters.DictionaryRecyclerViewAdapter;
import com.komdosh.yandextestapp.utils.CustomCache;
import com.komdosh.yandextestapp.utils.CustomViewUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.komdosh.yandextestapp.utils.CustomViewUtils.hideSoftKeyboard;

/**
 * @author komdosh
 *         created on 19.03.17
 */

public class TranslationFragment extends Fragment {

	public static final int SOURCE_LANGUAGE_REQUEST = 1;
	public static final int TARGET_LANGUAGE_REQUEST = 2;

	@BindView(R.id.sourceLang)
	TextView sourceLang;

	@BindView(R.id.targetLang)
	TextView targetLang;

	@BindView(R.id.toolbar)
	Toolbar toolbar;

	@BindView(R.id.translatedText)
	TextView translatedText;

	@BindView(R.id.textToTranslate)
	EditText textToTranslate;

	@BindView(R.id.dictionaryWord)
	TextView dictionaryWord;

	@BindView(R.id.dictionaryWordTs)
	TextView dictionaryWordTs;

	@BindView(R.id.favoriteIcon)
	ImageView favoriteIcon;

	@BindView(R.id.dictionaryLayout)
	LinearLayout dictionaryLayout;

	@BindView(R.id.dictionaryRecyclerView)
	RecyclerView dictionaryRecyclerView;

	@BindViews({R.id.playTranslated, R.id.favoriteIcon, R.id.shareTranslate, R.id.fullScreenWord,
			R.id.translatedText, R.id.dictionaryDescription, R.id.clearText, R.id.playEditText})
	List<View> translateControl;

	LangState langState = LangState.getInstance();

	DaoSession daoSession;

	History history;

	HistoryState historyState;

	DictionaryRecyclerViewAdapter dictionaryRecyclerViewAdapter;

	List<DefinitionDto> dictionaryList;

	CustomCache cache;

	public TranslationFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {

		daoSession = ((App) getActivity().getApplication()).getDaoSession();

		View rootView = inflater.inflate(R.layout.fragment_translation, container, false);
		ButterKnife.bind(this, rootView);

		((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
		historyState = HistoryState.getInstance();
		cache = new CustomCache(daoSession);
		cache.invalidateOld();
		setupEditText();
		setTextViewLangsFromState();
		setupDictionaryRv();
		return rootView;
	}

	public void setupEditText() {
		textToTranslate.setHorizontallyScrolling(false);
		textToTranslate.setLines(Integer.MAX_VALUE);
		textToTranslate.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_DONE) {

					if (textToTranslate.getText().toString().matches("")) {
						CustomViewUtils.setVisibilityToList(translateControl, View.INVISIBLE);
					} else {
						translate();
					}

					hideSoftKeyboard(getActivity(), textToTranslate);

					return true;
				}
				return false;
			}
		});
	}

	public void translateHistory(History history) {
		if (textToTranslate == null) {
			textToTranslate = (EditText) getActivity().findViewById(R.id.textToTranslate);
		}
		this.history = history;
		textToTranslate.setText(history.getText());
		translatedText.setText(history.getTranslatedText());
		CustomViewUtils.setVisibilityToList(translateControl, View.VISIBLE);

		langState.setSourceLang(history.getSourceLang());
		langState.setTargetLang(history.getTargetLang());
		setTextViewLangsFromState();
		dictionary();
	}

	@OnClick(R.id.fullScreenWord)
	public void fullScreenWord() {
		Intent intent = new Intent(getActivity(), WordActivity.class);
		intent.putExtra("word", translatedText.getText());
		startActivity(intent);
	}

	@OnClick(R.id.recordText)
	public void recordText() {
		showToastInDevelop();
	}

	@OnClick(R.id.playEditText)
	public void playEditText() {
		showToastInDevelop();
	}

	@OnClick(R.id.playTranslated)
	public void playTranslated() {
		showToastInDevelop();
	}

	@OnClick(R.id.shareTranslate)
	public void shareTranslate() {
		showToastInDevelop();
	}

	@OnClick(R.id.favoriteIcon)
	public void favoriteText() {
		history.setFavorite(!history.getFavorite());
		favoriteIcon.setImageResource(history.getFavorite() ? R.drawable.ic_favorite_active : R.drawable.ic_favorite);
		daoSession.getHistoryDao().update(history);
		historyState.setState(1);
	}

	@OnClick(R.id.clearText)
	public void clearText() {
		textToTranslate.setText("");
		hideSoftKeyboard(getActivity(), textToTranslate);
		CustomViewUtils.setVisibilityToList(translateControl, View.INVISIBLE);
		dictionaryLayout.setVisibility(View.INVISIBLE);
	}

	/*	@OnTextChanged(R.id.textToTranslate)
	public void editTextChanged() {
		if (textToTranslate.getText().toString().matches("")) {
			CustomViewUtils.setVisibilityToList(translateControl, View.INVISIBLE);
		} else {
			if (translateControl.get(0).getVisibility() != View.VISIBLE) {
				CustomViewUtils.setVisibilityToList(translateControl, View.VISIBLE);
			}
			translate();
			dictionary();
		}
	}*/

	public void translate() {
		if (TextUtils.isEmpty(textToTranslate.getText())) {
			return;
		}

		final String localTextToTranslate = textToTranslate.getText().toString();

		if (localTextToTranslate.split(" ").length == 1) {
			dictionary();
		} else {
			dictionaryLayout.setVisibility(View.VISIBLE);
		}

		CacheRequest cacheRequest = cache.getFromCache(localTextToTranslate);

		if (cacheRequest != null) {
			fillTranslate(cacheRequest.getTranslateDto(), localTextToTranslate);
			return;
		}

		TranslateAPI apiService = TranslateAPIClient.getClient().create(TranslateAPI.class);
		apiService.translate(getString(R.string.Yandex_Translate_API), localTextToTranslate, langState.getLangDir())
				.enqueue(new Callback<TranslateDto>() {
					@Override
					public void onResponse(Call<TranslateDto> call, Response<TranslateDto> response) {
						if (response.body() != null) {
							fillTranslate(response.body(), localTextToTranslate);
							cache.addOrUpdate(localTextToTranslate, langState.getLangDir(), response.body(), null);
						}
					}

					@Override
					public void onFailure(Call<TranslateDto> call, Throwable t) {
						CustomViewUtils.setVisibilityToList(translateControl, View.INVISIBLE);
						dictionaryLayout.setVisibility(View.INVISIBLE);
						showToastNetworkProblem();
					}
				});
	}

	private void fillTranslate(TranslateDto translateDto, String localTextToTranslate) {
		if (translateDto.getText() != null && !translateDto.getText().isEmpty() && translateDto
				.getText().get(0).length() > 1) {
			CustomViewUtils.setVisibilityToList(translateControl, View.VISIBLE);
			translatedText.setText(translateDto.getText().get(0));
			HistoryDao historyDao = daoSession.getHistoryDao();
			history = historyDao.queryBuilder()
					.where(HistoryDao.Properties.Text.eq(localTextToTranslate),
							HistoryDao.Properties.SourceLang.eq(new Gson().toJson(langState.getSourceLang())))
					.unique();
			if (history == null) {
				history = new History(null, localTextToTranslate, translateDto.getText().get(0),
						langState.getTargetLang(), langState.getSourceLang(), false, new Date());
				favoriteIcon.setImageResource(R.drawable.ic_favorite);
				historyDao.save(history);
			} else {
				favoriteIcon.setImageResource(history.getFavorite() ? R.drawable.ic_favorite_active : R.drawable.ic_favorite);
				history.setDate(new Date());
				historyDao.update(history);
			}
			historyState.setState(1);
		} else {
			CustomViewUtils.setVisibilityToList(translateControl, View.INVISIBLE);
		}
	}

	private void fillDictionary(DictionaryDto dictionaryDto) {
		Log.d("dictionaryDto", dictionaryDto.toString());
		if (!dictionaryDto.getDef().isEmpty()) {
			dictionaryList.clear();
			dictionaryList.addAll(dictionaryDto.getDef());
			dictionaryRecyclerViewAdapter.notifyDataSetChanged();
			dictionaryLayout.setVisibility(View.VISIBLE);
			DefinitionDto definitionDto = dictionaryDto.getDef().get(0);
			if (definitionDto != null && definitionDto.getTs() != null) {
				if (!definitionDto.getTs().isEmpty()) {
					dictionaryWordTs.setText(getString(R.string.tsTemplate, definitionDto.getTs()));
				}
				if (!definitionDto.getText().isEmpty()) {
					dictionaryWord.setText(definitionDto.getText());
				}
			}
		}
	}

	private void setupDictionaryRv() {
		dictionaryList = new ArrayList<>();
		dictionaryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		dictionaryRecyclerViewAdapter = new
				DictionaryRecyclerViewAdapter(dictionaryList, getActivity());
		dictionaryRecyclerView.setAdapter(dictionaryRecyclerViewAdapter);
		dictionaryRecyclerViewAdapter.notifyDataSetChanged();
	}

	public void dictionary() {

		final String localTextToTranslate = textToTranslate.getText().toString();

		CacheRequest cacheRequest = cache.getFromCache(localTextToTranslate);

		if (cacheRequest != null) {
			fillDictionary(cacheRequest.getDictionaryDto());
			return;
		}

		DictionaryAPI apiService = DictionaryAPIClient.getClient().create(DictionaryAPI.class);
		apiService.translate(getString(R.string.Yandex_Dictionary_API), localTextToTranslate,
				langState.getLangDir())
				.enqueue(new Callback<DictionaryDto>() {
					@Override
					public void onResponse(Call<DictionaryDto> call, Response<DictionaryDto> response) {
						if (response.body() != null) {
							fillDictionary(response.body());
							cache.addOrUpdate(localTextToTranslate, langState.getLangDir(), null,
									response.body());
						} else {
							dictionaryLayout.setVisibility(View.INVISIBLE);
						}
					}

					@Override
					public void onFailure(Call<DictionaryDto> call, Throwable t) {
						dictionaryLayout.setVisibility(View.INVISIBLE);
						showToastNetworkProblem();
					}
				});
	}


	@OnClick(R.id.sourceLang)
	public void chooseSourceLang() {
		startChooseLangIntent(SOURCE_LANGUAGE_REQUEST);
	}

	@OnClick(R.id.targetLang)
	public void chooseTargetLang() {
		startChooseLangIntent(TARGET_LANGUAGE_REQUEST);
	}

	private void startChooseLangIntent(int type) {
		Intent intent = new Intent(getContext(), ChooseLangActivity.class);
		intent.putExtra("TypeOfLang", type);
		startActivityForResult(intent, type);
	}

	@OnClick(R.id.switchLang)
	public void switchLang() {
		langState.switchLangs();
		setTextViewLangsFromState();

		if (!translatedText.getText().equals("")) {
			String translatedTextTemp = translatedText.getText().toString();
			translatedText.setText(textToTranslate.getText().toString());
			textToTranslate.setText(translatedTextTemp);
			translate();
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data == null) {
			return;
		}
		Lang lang;
		switch (requestCode) {
			case SOURCE_LANGUAGE_REQUEST:
				lang = (Lang) data.getSerializableExtra("language");
				sourceLang.setText(lang.getName());
				langState.setSourceLang(lang);
				break;
			case TARGET_LANGUAGE_REQUEST:
				lang = (Lang) data.getSerializableExtra("language");
				targetLang.setText(lang.getName());
				langState.setTargetLang(lang);
				break;
			default:
				showToastUnexpectedError();
		}
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (!isVisibleToUser && getActivity() != null && textToTranslate != null) {
			hideSoftKeyboard(getActivity(), textToTranslate);
		}
	}

	private void setTextViewLangsFromState() {
		try {
			sourceLang.setText(langState.getSourceLang().getName());
			targetLang.setText(langState.getTargetLang().getName());
		} catch (NullPointerException e) {
			Log.d("NPE langs TextView", e.toString());
		}
	}

	private void showToastInDevelop() {
		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(getContext(), TranslationFragment.this.getString(R.string.thisButtonIsNotImplementedYet), Toast.LENGTH_SHORT).show();
			}
		});
	}

	private void showToastNetworkProblem() {
		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(getContext(), TranslationFragment.this.getString(R.string.networkProblem), Toast
						.LENGTH_SHORT).show();
			}
		});
	}

	private void showToastUnexpectedError() {
		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(getContext(), TranslationFragment.this.getString(R.string.unexpectedError), Toast.LENGTH_SHORT).show();
			}
		});
	}
}
