package com.komdosh.yandextestapp.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by komdosh on 19.03.17.
 */

public class MainViewPagerFragmentAdapter extends FragmentPagerAdapter {
	private final List<Fragment> mFragmentList = new ArrayList<>();

	public MainViewPagerFragmentAdapter(FragmentManager manager) {
		super(manager);
	}

	@Override
	public Fragment getItem(int position) {
		return mFragmentList.get(position);
	}

	public Fragment getItemByClass(Class<? extends Fragment> findClass) {
		Fragment found = null;
		for (Fragment fragment : mFragmentList) {
			if (findClass.isInstance(fragment)) {
				found = fragment;
				break;
			}
		}
		return found;
	}

	@Override
	public int getCount() {
		return mFragmentList.size();
	}

	public void addFragment(Fragment fragment) {
		mFragmentList.add(fragment);
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return null;
	}
}
