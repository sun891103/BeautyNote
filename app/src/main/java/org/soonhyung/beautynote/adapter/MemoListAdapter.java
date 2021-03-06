package org.soonhyung.beautynote.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.soonhyung.beautynote.R;
import org.soonhyung.beautynote.common.Dictionary;

import java.util.List;

/**
 * Created by soonhyung on 2017-09-12.
 */

public class MemoListAdapter extends ArrayAdapter<Dictionary> {
    private Context context;
    private int resourceId;
    private List<Dictionary> objects;

    public MemoListAdapter(@NonNull Context context, @LayoutRes int resourceId, @NonNull List<Dictionary> objects) {
        super(context, resourceId, objects);
        this.context = context;
        this.resourceId = resourceId;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(resourceId, null);
        }

        TextView txtId = (TextView) view.findViewById(R.id.item_id);
        TextView txtSubject = (TextView) view.findViewById(R.id.item_subject);
        TextView txtComment = (TextView) view.findViewById(R.id.item_comment);
        ImageView imgLine = (ImageView) view.findViewById(R.id.item_line);
        ImageView imgVisible = (ImageView) view.findViewById(R.id.item_visible);

        txtId.setText(objects.get(position).getString("id"));
        txtSubject.setText(objects.get(position).getString("subject"));
        txtComment.setText(objects.get(position).getString("comment"));

        if(objects.get(position).getString("view").equals("visible")){
            txtComment.setVisibility(View.VISIBLE);
            imgLine.setVisibility(View.VISIBLE);
            imgVisible.setImageResource(android.R.drawable.arrow_up_float);
        } else if(objects.get(position).getString("view").equals("gone")){
            txtComment.setVisibility(View.GONE);
            imgLine.setVisibility(View.VISIBLE);
            imgVisible.setImageResource(android.R.drawable.arrow_down_float);
        }

        return view;
    }
}
