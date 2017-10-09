package org.soonhyung.beautynote.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import org.soonhyung.beautynote.R;
import org.soonhyung.beautynote.activity.MainActivity;
import org.soonhyung.beautynote.adapter.MemoListAdapter;
import org.soonhyung.beautynote.common.AlertUtils;
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
        View view = inflater.inflate(R.layout.fragment_memo, container, false);
        init(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        initDB();
    }

    private void init(View view){
        btnAddMemo = (ImageButton) view.findViewById(R.id.btn_add_memo);
        listMemo = (ListView) view.findViewById(R.id.list_memo);
        memoListAdapter = new MemoListAdapter(getActivity(), R.layout.memo_list, arrMemo);
        listMemo.setAdapter(memoListAdapter);

        initEvent();

        initDB();
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
            dic.addString("view", "gone");
            arrMemo.add(dic);
        }

        memoListAdapter.notifyDataSetChanged();
    }

    private void initEvent(){
        btnAddMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SaveMemoPopup.class));
            }
        });

        listMemo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(arrMemo.get(i).getString("view").equals("gone")){
                    arrMemo.get(i).addString("view", "visible");
                }else if(arrMemo.get(i).getString("view").equals("visible")){
                    arrMemo.get(i).addString("view", "gone");
                }
                memoListAdapter.notifyDataSetChanged();
            }
        });

        listMemo.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                AlertUtils.showYesNoDialog(getActivity(), "메모", arrMemo.get(i).getString("subject") + "\r\n항목을 삭제하시겠습니까?", new AlertUtils.YesNoDialogCallBack() {
                    @Override
                    public void onYes() {
                        String sql = "";
                        try {
                            sql = String.format(Utils.getQuery(getActivity(), "db.delMemo"), arrMemo.get(i).getString("id"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        MainActivity.execSql(sql);
                        initDB();
                    }

                    @Override
                    public void onNo() {

                    }
                });
                return true;
            }
        });
    }
}