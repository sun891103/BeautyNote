package org.soonhyung.beautynote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.soonhyung.beautynote.R;
import org.soonhyung.beautynote.common.Dictionary;

import java.util.ArrayList;

public class MainDrawerListAdapter extends ArrayAdapter<Dictionary> {
	private Context context;
	private int textViewResourceId;

	private final int[] rId = {R.drawable.btn_sub1_sel, R.drawable.btn_sub2_sel
			, R.drawable.btn_sub3_sel, R.drawable.btn_sub4_sel, R.drawable.btn_sub5_sel
			, R.drawable.btn_sub6_sel, R.drawable.btn_sub7_sel, R.drawable.btn_sub8_sel
			, R.drawable.btn_logout_sel};

	public MainDrawerListAdapter(Context context, int textViewResourceId, ArrayList<Dictionary> arrayList) {
		super(context, textViewResourceId, arrayList);
		this.context = context;
		this.textViewResourceId = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final int pos = position;
		View view = convertView;

		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(textViewResourceId, null);
		}

		ImageView imageview = (ImageView)view.findViewById(R.id.imageview);
		ImageView imageview2 = (ImageView)view.findViewById(R.id.imageview2);
		LinearLayout linearVi = (LinearLayout)view.findViewById(R.id.linear_vi);

		if (pos == rId.length-1) {
			imageview.setVisibility(View.GONE);
			imageview2.setVisibility(View.VISIBLE);
			imageview2.setBackgroundResource(rId[pos]);
		}else{
			imageview.setVisibility(View.VISIBLE);
			imageview2.setVisibility(View.GONE);
			imageview.setBackgroundResource(rId[pos]);
		}

		linearVi.setVisibility(View.VISIBLE);

		return view;
	}
}
