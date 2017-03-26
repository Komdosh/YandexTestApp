package com.komdosh.yandextestapp.ui.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.komdosh.yandextestapp.R;
import com.komdosh.yandextestapp.ui.adapters.MainViewPagerFragmentAdapter;
import com.komdosh.yandextestapp.ui.fragments.FavoriteFragment;
import com.komdosh.yandextestapp.ui.fragments.SettingsFragment;
import com.komdosh.yandextestapp.ui.fragments.TranslationFragment;
import com.komdosh.yandextestapp.ui.views.ViewPagerWithoutSwipe;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author komdosh
 *         created on 16.03.17
 */

public class MainActivity extends AppCompatActivity {
	private static final String TAG = "MainActivity";

	@BindView(R.id.mainActivityPager)
	ViewPagerWithoutSwipe viewPager;

	@BindView(R.id.tabs)
	TabLayout tabLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		setupViewPager(viewPager);

		tabLayout.setupWithViewPager(viewPager);
		setupTabIcons();
	}

	private void setupTabIcons() {
		int[] tabIcons = {
				R.drawable.ic_tab_translate,
				R.drawable.ic_tab_favorite,
				R.drawable.ic_tab_settings
		};

		try {
			for (int i = 0; i < tabIcons.length; i++) {
				tabLayout.getTabAt(i).setIcon(tabIcons[i]);
			}

			//TODO (AT): remove when settings will be implemented
			LinearLayout tabStrip = (LinearLayout) tabLayout.getChildAt(0);
			tabStrip.getChildAt(2).setOnTouchListener(new View
					.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) { // 2 - index of settings tab
					showToastDisabledTab();
					return true;
				}
			});
		} catch (NullPointerException e) {
			Log.e(TAG, e.toString());
		}
	}

	private void showToastDisabledTab() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(MainActivity.this, getString(R.string.thisSectionInDevelop), Toast.LENGTH_SHORT)
						.show();
			}
		});
	}

	private void setupViewPager(ViewPager viewPager) {
		MainViewPagerFragmentAdapter adapter = new MainViewPagerFragmentAdapter(getSupportFragmentManager());
		adapter.addFragment(new TranslationFragment());
		adapter.addFragment(new FavoriteFragment());
		//This fragment disabled while settings not implemented
		adapter.addFragment(new SettingsFragment());
		viewPager.setAdapter(adapter);
	}
}

