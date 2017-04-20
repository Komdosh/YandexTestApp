package com.komdosh.yandextestapp.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 *  @author komdosh
 *          created on 19.03.17.
 */

public class MainViewPagerFragmentAdapter extends FragmentStatePagerAdapter {
	private final List<Fragment> fragments = new ArrayList<>();

	public MainViewPagerFragmentAdapter(FragmentManager manager) {
		super(manager);
	}

	@Override
	public Fragment getItem(int position) {
		return fragments.get(position);
	}

	public Fragment getItemByClass(Class<? extends Fragment> findClass) {
		Fragment found = null;
		for (Fragment fragment : fragments) {
			if (findClass.isInstance(fragment)) {
				found = fragment;
				break;
			}
		}
		return found;
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

	public void addFragment(Fragment fragment) {
		fragments.add(fragment);
	}

	public void replaceFragment(int index, Fragment fragment) {
		fragments.set(index, fragment);
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return null;
	}
}
