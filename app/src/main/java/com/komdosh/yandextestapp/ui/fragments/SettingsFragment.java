package com.komdosh.yandextestapp.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.komdosh.yandextestapp.R;

import butterknife.ButterKnife;

//TODO (AT): implement settings fragment
public class SettingsFragment extends Fragment {

	private static final String TAG = SettingsFragment.class.getSimpleName();

	public SettingsFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
		ButterKnife.bind(this, rootView);

		return rootView;
	}
}
