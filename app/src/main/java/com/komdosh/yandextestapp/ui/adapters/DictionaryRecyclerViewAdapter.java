package com.komdosh.yandextestapp.ui.adapters;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.komdosh.yandextestapp.R;
import com.komdosh.yandextestapp.data.dto.DefinitionDto;

import java.util.List;

/**
 * @author komdosh
 *         created on 19.03.17.
 */

//Every item is from Dictionary API
public class DictionaryRecyclerViewAdapter extends RecyclerView.Adapter<DictionaryRecyclerViewAdapter.ViewHolder> {

	private List<DefinitionDto> definitionDtos;

	private Activity activity;

	public DictionaryRecyclerViewAdapter(List<DefinitionDto> definitionDtos, Activity activity) {
		super();
		this.definitionDtos = definitionDtos;
		this.activity = activity;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View post = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dictionary, parent, false);

		return new ViewHolder(post);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		if (definitionDtos == null || definitionDtos.get(position) == null) {
			return;
		}

		DefinitionDto definitionDto = definitionDtos.get(position);

		holder.pos.setText(definitionDto.getPos());
		holder.dictionaryRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
		DefOfSpeechRecyclerViewAdapter defOfSpeechRecyclerViewAdapter = new
				DefOfSpeechRecyclerViewAdapter(definitionDto.getTr());
		holder.dictionaryRecyclerView.setAdapter(defOfSpeechRecyclerViewAdapter);
		defOfSpeechRecyclerViewAdapter.notifyDataSetChanged();
	}

	public DefinitionDto getItem(int position) {
		return definitionDtos.get(position);
	}

	@Override
	public int getItemCount() {
		if (definitionDtos == null) {
			return 0;
		}

		return definitionDtos.size();
	}

	class ViewHolder extends RecyclerView.ViewHolder {
		TextView pos;
		RecyclerView dictionaryRecyclerView;

		//Initializing Views
		ViewHolder(View view) {
			super(view);
			pos = (TextView) view.findViewById(R.id.pos);
			dictionaryRecyclerView = (RecyclerView) view.findViewById(R.id.defOfSpeechRecyclerView);
		}
	}

}