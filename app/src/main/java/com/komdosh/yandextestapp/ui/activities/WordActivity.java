package com.komdosh.yandextestapp.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.komdosh.yandextestapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author komdosh
 *         created on 19.03.17
 */

public class WordActivity extends AppCompatActivity {
	public static final String TEXT_KEY = "text";
	private static final String TAG = WordActivity.class.getSimpleName();

	@BindView(R.id.word)
	TextView wordView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_word);

		ButterKnife.bind(this);

		String word = getIntent().getStringExtra(TEXT_KEY);
		if (word != null) {
			wordView.setText(word);
		} else {
			finish();
		}
	}

	@OnClick(R.id.close)
	public void close() {
		finish();
	}
}
