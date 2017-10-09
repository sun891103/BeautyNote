package org.soonhyung.beautynote.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import org.soonhyung.beautynote.R;
import org.soonhyung.beautynote.adapter.ReserveListAdapter;
import org.soonhyung.beautynote.common.Dictionary;

import java.util.ArrayList;

/**
 * Created by soonhyung on 2017-09-07.
 */

public class ReserveFragment extends Fragment {

    private ImageButton btnAddReserve;
    private ListView listReserve;

    private ReserveListAdapter reserveListAdapter;
    private ArrayList<Dictionary> arrReserve = new ArrayList<>();

    public ReserveFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reserve, container, false);
        init(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void init(View view){
        btnAddReserve = (ImageButton) view.findViewById(R.id.btn_add_reserve);
        listReserve = (ListView) view.findViewById(R.id.list_reserve);
        reserveListAdapter = new ReserveListAdapter(getActivity(), R.layout.reserve_list, arrReserve);
        listReserve.setAdapter(reserveListAdapter);

        for(int i=10; i<=21; i++){
            Dictionary dic = new Dictionary();
            dic.addString("time", i + "시");
            dic.addString("custom", "임한솔");
            arrReserve.add(dic);
        }

        reserveListAdapter.notifyDataSetChanged();
    }
}