package org.soonhyung.beautynote.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import org.soonhyung.beautynote.R;
import org.soonhyung.beautynote.activity.MainActivity;
import org.soonhyung.beautynote.adapter.MemoListAdapter;
import org.soonhyung.beautynote.common.Dictionary;
import org.soonhyung.beautynote.common.Utils;
import org.soonhyung.beautynote.popup.SaveMemoPopup;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by soonhyung on 2017-09-07.
 */

public class MemoFragment extends Fragment{

    private ImageButton btnAddMemo;
    private ListView listMemo;

    private MemoListAdapter memoListAdapter;
    private ArrayList<Dictionary> arrMemo = new ArrayList<>();

    public MemoFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.flagment_memo, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        init();
    }

    private void init(){
        btnAddMemo = (ImageButton) getView().findViewById(R.id.btn_add_memo);
        listMemo = (ListView) getView().findViewById(R.id.list_memo);

        initDB();

        initEvent();
    }

    private void initDB(){
        arrMemo.clear();

        String sql = "";
        try {
            sql = Utils.getQuery(getActivity(), "db.selMemo");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Cursor cursor = MainActivity.select(sql);
        Dictionary dic;

        while(cursor.moveToNext()) {
            dic = new Dictionary();
            dic.addString("id", cursor.getString(0));
            dic.addString("subject", cursor.getString(1));
            dic.addString("comment", cursor.getString(2));
            arrMemo.add(dic);
        }

        memoListAdapter = new MemoListAdapter(getActivity(), R.layout.memo_list, arrMemo);
        listMemo.setAdapter(memoListAdapter);
    }

    private void initEvent(){
        btnAddMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SaveMemoPopup.class));
            }
        });
    }
}