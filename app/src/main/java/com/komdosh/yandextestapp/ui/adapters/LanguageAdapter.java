package com.komdosh.yandextestapp.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.komdosh.yandextestapp.R;
import com.komdosh.yandextestapp.data.model.Lang;

import java.util.List;

/**
 * Created by komdosh on 19.03.17.
 */

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.ViewHolder> {

	private List<Lang> languages;
	private Context context;

	public LanguageAdapter(List<Lang> languages, Context context) {
		super();
		this.languages = languages;
		this.context = context;
	}

	@Override
	public LanguageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View post = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_language, parent, false);
		return new LanguageAdapter.ViewHolder(post);
	}

	@Override
	public void onBindViewHolder(LanguageAdapter.ViewHolder holder, int position) {
		if (languages == null) {
			return;
		}

		Lang item = languages.get(position);

		holder.lang.setVisibility(View.VISIBLE);
		holder.lang.setText(item.getName());

	}

	public Lang getItem(int position) {
		return languages.get(position);
	}

	@Override
	public int getItemCount() {
		if (languages == null) {
			return 0;
		}

		return languages.size();
	}

	class ViewHolder extends RecyclerView.ViewHolder {
		TextView lang;

		ViewHolder(View view) {
			super(view);
			lang = (TextView) view.findViewById(R.id.lang);
		}
	}
}
