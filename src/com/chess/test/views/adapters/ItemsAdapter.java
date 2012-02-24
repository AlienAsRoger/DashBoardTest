package com.chess.test.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class ItemsAdapter<T> extends BaseAdapter {

	protected List<T> itemsList;
	protected Context context;
	protected final LayoutInflater inflater;

	// protected ImageLoader mImageLoader;

	public ItemsAdapter(Context context, List<T> itemList) {
		itemsList = itemList;
		this.context = context;
		inflater = LayoutInflater.from(context);
		// mImageLoader = ImageLoader.getInstance(context);
	}

	public void setItemsList(List<T> list) {
		itemsList = list;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return itemsList.size();
	}

	@Override
	public T getItem(int position) {
		return itemsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void remove(T item) {
		if (itemsList.remove(item))
			notifyDataSetChanged();
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup parent) {
		if (convertView == null)
			convertView = createView(parent);
		// if (itemsList != null)
		bindView(itemsList.get(pos), pos, convertView);
		return convertView;
	}

	protected abstract View createView(ViewGroup parent);

	protected abstract void bindView(T item, int pos, View convertView);

}
