package org.soonhyung.beautynote.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import org.soonhyung.beautynote.R;
import org.soonhyung.beautynote.popup.SaveMemoPopup;

/**
 * Created by soonhyung on 2017-09-07.
 */

public class MemoFragment extends Fragment{

    ImageButton btnAddMemo;

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

        initEvent();
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