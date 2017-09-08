package org.soonhyung.beautynote.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.soonhyung.beautynote.R;

/**
 * Created by soonhyung on 2017-09-07.
 */

public class MainFragment1 extends Fragment{
    public MainFragment1() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.flagment_main1, container, false);
    }
}
