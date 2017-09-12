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
import org.soonhyung.beautynote.popup.SaveMemberPopup;

/**
 * Created by soonhyung on 2017-09-07.
 */

public class ReserveFragment extends Fragment {
    public ReserveFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.flagment_reserve, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        ((ImageButton) getView().findViewById(R.id.btn_add_reserve)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SaveMemberPopup.class));
            }
        });
    }
}