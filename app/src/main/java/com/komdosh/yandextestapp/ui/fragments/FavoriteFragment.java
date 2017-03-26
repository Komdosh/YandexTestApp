package com.komdosh.yandextestapp.ui.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.komdosh.yandextestapp.R;
import com.komdosh.yandextestapp.ui.adapters.HistoryViewPagerFragmentAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author komdosh
 *         created on 19.03.17
 */

public class FavoriteFragment extends Fragment {
	private static final String TAG = "FavoriteFragment";

	@BindView(R.id.historyViewPager)
	ViewPager viewPager;

	@BindView(R.id.tabs)
	TabLayout tabLayout;

	HistoryViewPagerFragmentAdapter adapter;

	public FavoriteFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_favorite, container, false);
		ButterKnife.bind(this, rootView);

		setupViewPager(viewPager);
		tabLayout.setupWithViewPager(viewPager);

		return rootView;
	}

	private void setupViewPager(ViewPager viewPager) {
		adapter = new HistoryViewPagerFragmentAdapter(getFragmentManager());

		adapter.addFragment(new HistoryFavoriteFragment(), getString(R.string.history));

		HistoryFavoriteFragment favoriteFragment = new HistoryFavoriteFragment();
		Bundle bundle = new Bundle();
		bundle.putInt(HistoryFavoriteFragment.TYPE_KEY, HistoryFavoriteFragment.FAVORITE);
		favoriteFragment.setArguments(bundle);
		adapter.addFragment(favoriteFragment, getString(R.string.favorites));

		viewPager.setAdapter(adapter);
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		//This need to update list right away after translate
		if (isVisibleToUser) {
			for (int i = 0; i < adapter.getCount(); i++) {
				((HistoryFavoriteFragment) adapter.getItem(i)).updateItems();
			}
		}
	}
}

