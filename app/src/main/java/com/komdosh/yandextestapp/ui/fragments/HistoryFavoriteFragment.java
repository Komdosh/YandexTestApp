package com.komdosh.yandextestapp.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.komdosh.yandextestapp.App;
import com.komdosh.yandextestapp.R;
import com.komdosh.yandextestapp.data.model.entity.DaoSession;
import com.komdosh.yandextestapp.data.model.entity.History;
import com.komdosh.yandextestapp.data.model.entity.HistoryDao;
import com.komdosh.yandextestapp.states.HistoryState;
import com.komdosh.yandextestapp.ui.adapters.HistoryRecyclerViewAdapter;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author komdosh
 *         created on 19.03.17.
 */

public class HistoryFavoriteFragment extends Fragment {

	public static final String TYPE_KEY = "type";
	public static final int HISTORY = 0;
	public static final int FAVORITE = 1;
	private static final String TAG = "HistoryFavoriteFragment";
	List<History> histories;
	private int type;
	private RecyclerView recyclerView;
	private HistoryRecyclerViewAdapter historyRecyclerViewAdapter;
	private DaoSession daoSession;

	public HistoryFavoriteFragment() {
		// Required empty public constructor for adapter
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		daoSession = ((App) getActivity().getApplication()).getDaoSession();
		View rootView = inflater.inflate(R.layout.fragment_history, container, false);
		recyclerView = (RecyclerView) rootView.findViewById(R.id.historyRecyclerView);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		historyRecyclerViewAdapter = new HistoryRecyclerViewAdapter(loadListOfItem(), getActivity());
		recyclerView.setAdapter(historyRecyclerViewAdapter);
		historyRecyclerViewAdapter.notifyDataSetChanged();
		registerForContextMenu(recyclerView);
		return rootView;
	}

	public List<History> loadListOfItem() {
		if (histories == null) {
			histories = new ArrayList<>();
		} else {
			histories.clear();
		}

		QueryBuilder<History> historiesQueryWithDateOrder = daoSession.getHistoryDao()
				.queryBuilder()
				.orderDesc(HistoryDao.Properties.Date);

		if (getArguments() != null && getArguments().containsKey(TYPE_KEY)) {
			type = getArguments().getInt(TYPE_KEY);
			switch (type) {
				case FAVORITE:
					histories.addAll(historiesQueryWithDateOrder.where(HistoryDao.Properties.Favorite.eq(true)).list());
					break;
				case HISTORY:
				default:
					histories.addAll(historiesQueryWithDateOrder.list());
			}
		} else {
			type = HISTORY;
			histories.addAll(historiesQueryWithDateOrder.list());
		}
		return histories;
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser) {
			updateItems();
		}
	}

	public void updateItems() {
		HistoryState historyState = HistoryState.getInstance();
		if (historyRecyclerViewAdapter != null && historyState.getState() == 1) {
			loadListOfItem();
			historyRecyclerViewAdapter.notifyDataSetChanged();
			historyState.setState(0);
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getGroupId()) {
			case R.id.delete:
				deleteItem(item.getItemId());
				return true;
			default:
				return super.onContextItemSelected(item);
		}
	}

	private void deleteItem(long id) {
		daoSession.getHistoryDao().deleteByKey(id);
		historyRecyclerViewAdapter.remove((int) id);
	}
}
