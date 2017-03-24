package com.komdosh.yandextestapp.ui.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.komdosh.yandextestapp.R;
import com.komdosh.yandextestapp.ui.adapters.MainViewPagerFragmentAdapter;
import com.komdosh.yandextestapp.ui.fragments.FavoriteFragment;
import com.komdosh.yandextestapp.ui.fragments.SettingsFragment;
import com.komdosh.yandextestapp.ui.fragments.TranslationFragment;
import com.komdosh.yandextestapp.ui.views.ViewPagerWithoutSwipe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

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

		viewPager.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		});

		tabLayout.setupWithViewPager(viewPager);

		setupTabIcons();
	}

	private void setupTabIcons() {
		int[] tabIcons = {
				R.drawable.ic_tab_translate,
				R.drawable.ic_tab_favorite,
				R.drawable.ic_tab_settings
		};

		for (int i = 0; i < tabIcons.length; i++) {
			tabLayout.getTabAt(i).setIcon(tabIcons[i]);
		}
	}

	private void setupViewPager(ViewPager viewPager) {
		MainViewPagerFragmentAdapter adapter = new MainViewPagerFragmentAdapter(getSupportFragmentManager());
		adapter.addFragment(new TranslationFragment());
		adapter.addFragment(new FavoriteFragment());
		adapter.addFragment(new SettingsFragment());
		viewPager.setAdapter(adapter);
	}
}

