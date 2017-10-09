package org.soonhyung.beautynote.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.soonhyung.beautynote.R;
import org.soonhyung.beautynote.common.Dictionary;

import java.util.List;

/**
 * Created by soonhyung on 2017-09-12.
 */

public class ReserveListAdapter extends ArrayAdapter<Dictionary> {
    private Context context;
    private int resourceId;
    private List<Dictionary> objects;

    public ReserveListAdapter(@NonNull Context context, @LayoutRes int resourceId, @NonNull List<Dictionary> objects) {
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

        TextView txtTime = (TextView) view.findViewById(R.id.item_time);
        TextView txtCustom = (TextView) view.findViewById(R.id.item_custom);

        txtTime.setText(objects.get(position).getString("time"));
        txtCustom.setText(objects.get(position).getString("custom"));

        return view;
    }
}
