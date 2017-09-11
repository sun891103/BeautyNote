package org.soonhyung.beautynote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import org.soonhyung.beautynote.R;
import org.soonhyung.beautynote.common.Dictionary;

import java.util.ArrayList;

public class MainDrawerListAdapter extends ArrayAdapter<Dictionary> {
	private Context context;
	private int resourceId;

	private final int[] rId = {R.drawable.btn_sub1_sel, R.drawable.btn_sub2_sel, R.drawable.btn_sub3_sel
	, R.drawable.btn_sub4_sel, R.drawable.btn_sub5_sel, R.drawable.btn_sub6_sel, R.drawable.btn_sub7_sel
	, R.drawable.btn_sub8_sel, R.drawable.btn_sub9_sel};

	public MainDrawerListAdapter(Context context, int resourceId, ArrayList<Dictionary> arrayList) {
		super(context, resourceId, arrayList);
		this.context = context;
		this.resourceId = resourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;

		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(resourceId, null);
		}

		ImageView imageview = (ImageView)view.findViewById(R.id.imageview);
		imageview.setBackgroundResource(rId[position]);

		return view;
	}
}
