package org.soonhyung.beautynote.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.soonhyung.beautynote.R;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by soonhyung on 2017-09-12.
 */

public class MainFragment extends Fragment {

    private TextView txtDateTime;

    private Calendar cal;

    private TimeHandler mHandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.flagment_main, container, false);
        init(view);
        return view;
    }

    private void init(View view){
        mHandler = new TimeHandler();

        txtDateTime = (TextView) view.findViewById(R.id.txt_date_time);

        mHandler.sendEmptyMessageDelayed(0, 1000);
    }

    private String getTime(){
        cal = new GregorianCalendar();
        return String.format("%d년 %d월 %d일  %02d:%02d:%02d"
                , cal.get(Calendar.YEAR)
                , cal.get(Calendar.MONTH) + 1
                , cal.get(Calendar.DAY_OF_MONTH)
                , cal.get(Calendar.HOUR)
                , cal.get(Calendar.MINUTE)
                , cal.get(Calendar.SECOND));
    }

    class TimeHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            txtDateTime.setText(getTime());

            mHandler.sendEmptyMessageDelayed(0, 1000);
        }
    }
}
