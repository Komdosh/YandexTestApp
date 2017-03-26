package com.komdosh.yandextestapp.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author komdosh
 *         created on 19.03.17.
 */

public class HistoryViewPagerFragmentAdapter extends FragmentPagerAdapter {
	private final List<Fragment> fragments = new ArrayList<>();
	private final List<String> fragmentTitles = new ArrayList<>();

	public HistoryViewPagerFragmentAdapter(FragmentManager manager) {
		super(manager);
	}

	@Override
	public Fragment getItem(int position) {
		return fragments.get(position);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

	public void addFragment(Fragment fragment, String title) {
		fragments.add(fragment);
		fragmentTitles.add(title);
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return fragmentTitles.get(position);
	}
}
