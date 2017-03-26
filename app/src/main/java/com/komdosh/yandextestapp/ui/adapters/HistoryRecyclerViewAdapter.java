package com.komdosh.yandextestapp.ui.adapters;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.komdosh.yandextestapp.App;
import com.komdosh.yandextestapp.R;
import com.komdosh.yandextestapp.data.model.entity.DaoSession;
import com.komdosh.yandextestapp.data.model.entity.History;
import com.komdosh.yandextestapp.states.HistoryState;
import com.komdosh.yandextestapp.ui.fragments.TranslationFragment;

import java.util.List;

/**
 * @author komdosh
 *         created on 19.03.17.
 */

public class HistoryRecyclerViewAdapter extends RecyclerView.Adapter<HistoryRecyclerViewAdapter.ViewHolder> {

	private List<History> histories;

	private DaoSession daoSession;

	private Activity activity;

	public HistoryRecyclerViewAdapter(List<History> histories, Activity activity) {
		super();
		this.histories = histories;
		this.activity = activity;
		daoSession = ((App) activity.getApplicationContext()).getDaoSession();
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View post = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);

		return new ViewHolder(post);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		if (histories == null) {
			return;
		}

		History history = histories.get(position);

		String languages = "[" +
				history.getSourceLang().getCode() +
				"-" +
				history.getTargetLang().getCode() +
				"]";

		holder.icon.setImageResource(getFavoriteIcon(history.getFavorite()));
		holder.langs.setText(languages);
		holder.text.setText(history.getText());
		holder.translatedText.setText(history.getTranslatedText());
	}

	public History getItem(int position) {
		return histories.get(position);
	}

	@Override
	public int getItemCount() {
		if (histories == null) {
			return 0;
		}
		return histories.size();
	}

	public void remove(int position) {
		histories.remove(position);
		notifyItemRemoved(position);
	}

	private int getFavoriteIcon(boolean favorite) {
		return favorite ? R.drawable.ic_favorite_active : R.drawable.ic_favorite;
	}

	class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
		ImageView icon;

		TextView text;

		TextView translatedText;

		TextView langs;

		//Initializing Views
		ViewHolder(View view) {
			super(view);
			view.setOnCreateContextMenuListener(this);
			icon = (ImageView) view.findViewById(R.id.icon);
			text = (TextView) view.findViewById(R.id.text);
			translatedText = (TextView) view.findViewById(R.id.translatedText);
			langs = (TextView) view.findViewById(R.id.langs);

			icon.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					History history = histories.get(getAdapterPosition());
					HistoryState.getInstance().setState(1);
					history.setFavorite(!history.getFavorite());
					icon.setImageResource(getFavoriteIcon(history.getFavorite()));
					daoSession.getHistoryDao().update(history);
				}
			});

			text.setOnClickListener(toTranslate());
			translatedText.setOnClickListener(toTranslate());
		}

		private View.OnClickListener toTranslate() {
			return new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					ViewPager mainActivityPager = (ViewPager) activity.findViewById(R.id.mainActivityPager);
					if (mainActivityPager != null && histories != null && histories.get(getAdapterPosition()) != null) {
						MainViewPagerFragmentAdapter adapter = (MainViewPagerFragmentAdapter) mainActivityPager.getAdapter();
						TranslationFragment translationFragment = (TranslationFragment) adapter.getItemByClass(TranslationFragment.class);
						mainActivityPager.setCurrentItem(0); // 0 - it is TranslationFragment
						translationFragment.translateHistory(histories.get(getAdapterPosition()));
					}
				}
			};
		}

		@Override
		public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
			menu.add(R.id.delete, getAdapterPosition(), 0, activity.getString(R.string.delete));
		}
	}
}