package com.komdosh.yandextestapp.ui.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.komdosh.yandextestapp.App;
import com.komdosh.yandextestapp.R;
import com.komdosh.yandextestapp.data.dto.DicTranslatedDto;
import com.komdosh.yandextestapp.data.model.entity.DaoSession;

import java.util.List;

/**
 * @author komdosh
 *         created on 19.03.17.
 */

//Every item is from Dictionary API
public class DefOfSpeechRecyclerViewAdapter extends RecyclerView.Adapter<DefOfSpeechRecyclerViewAdapter.ViewHolder> {

	private List<DicTranslatedDto> defOfSpeech;

	private DaoSession daoSession;

	private Activity activity;

	public DefOfSpeechRecyclerViewAdapter(List<DicTranslatedDto> defOfSpeech, Activity activity) {
		super();
		this.defOfSpeech = defOfSpeech;
		this.activity = activity;
		daoSession = ((App) activity.getApplicationContext()).getDaoSession();
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View post = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_def_of_speech, parent, false);

		return new ViewHolder(post);
	}


	@Override
	@SuppressLint("SetTextI18n")
	public void onBindViewHolder(ViewHolder holder, int position) {
		if (defOfSpeech == null || defOfSpeech.get(position) == null) {
			return;
		}

		holder.numberOfPos.setText(Integer.toString(position) + " ");

		if (defOfSpeech.get(position).getText() != null) {
			String wordWithSyn = defOfSpeech.get(position).getText();
			if (defOfSpeech.get(position).getSyn() != null) {
				wordWithSyn += ", ";
				wordWithSyn += TextUtils.join(", ", defOfSpeech.get(position).getSyn());
			}
			holder.syn.setText(wordWithSyn);
		} else {
			holder.syn.setVisibility(View.GONE);
		}

		if (defOfSpeech.get(position).getMean() != null) {
			holder.mean.setText("(" + TextUtils.join(", ", defOfSpeech.get(position).getMean()) + ")");
		} else {
			holder.mean.setVisibility(View.GONE);
		}

		if (defOfSpeech.get(position).getEx() != null) {
			holder.ex.setText(TextUtils.join("\n ", defOfSpeech.get(position).getEx()));
		} else {
			holder.ex.setVisibility(View.GONE);
		}


	}

	public DicTranslatedDto getItem(int position) {
		return defOfSpeech.get(position);
	}

	@Override
	public int getItemCount() {
		if (defOfSpeech == null) {
			return 0;
		}

		return defOfSpeech.size();
	}

	class ViewHolder extends RecyclerView.ViewHolder {
		TextView numberOfPos;
		TextView syn;
		TextView mean;
		TextView ex;

		//Initializing Views
		ViewHolder(View view) {
			super(view);
			numberOfPos = (TextView) view.findViewById(R.id.numberOfPos);
			syn = (TextView) view.findViewById(R.id.syn);
			mean = (TextView) view.findViewById(R.id.mean);
			ex = (TextView) view.findViewById(R.id.ex);
		}
	}

}