package org.soonhyung.beautynote.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;
import org.soonhyung.beautynote.R;
import org.soonhyung.beautynote.adapter.ReserveListAdapter;
import org.soonhyung.beautynote.common.AQueryCallback;
import org.soonhyung.beautynote.common.AlertUtils;
import org.soonhyung.beautynote.common.Dictionary;
import org.soonhyung.beautynote.common.URL;

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

        selReserve();
    }

    private void selReserve(){
        AlertUtils.showSelDialog(getActivity());

        new AQueryCallback(URL.selReserve, null, getActivity(), new AQueryCallback.AQueryCallBackListener() {
            @Override
            public void httpRequestComplete(JSONObject jsonObject) {
                if (jsonObject.optString("resultCode").equals("0000")) {
                    AlertUtils.dismissSaveDialog(getActivity());

                    /*for(int i=0; i<=20; i++){
                        Dictionary dic = new Dictionary();
                        dic.addString("time", i + "시");
                        dic.addString("custom", "임한솔");
                        arrReserve.add(dic);
                    }*/
                    try {
                        Dictionary dic;

                        for (int i = 0; i < jsonObject.getJSONArray("rows").length(); i++) {
                            dic = new Dictionary();
                            String startTime = jsonObject.getJSONArray("rows").getJSONObject(i).getString("startTime");
                            String endTime = jsonObject.getJSONArray("rows").getJSONObject(i).getString("endTime");
                            String strTime = startTime.substring(0, 2) + ":" + startTime.substring(2)
                                    + " ~ " + endTime.substring(0, 2) + ":" + endTime.substring(2);

                            dic.addString("time", strTime);
                            dic.addString("custom", jsonObject.getJSONArray("rows").getJSONObject(i).getString("name"));
                            arrReserve.add(dic);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    reserveListAdapter.notifyDataSetChanged();
                }else{
                    AlertUtils.dismissSaveDialog(getActivity());
                    AlertUtils.showOkDialog(getActivity(), "알림", jsonObject.optString("result"), null);
                }
            }

            @Override
            public void httpRequestError() {
                AlertUtils.dismissSaveDialog(getActivity());
                AlertUtils.showOkDialog(getActivity(), "알림", "인터넷 환경이 불안정 합니다./r/n다시 시도해 주세요.", null);
            }
        });
    }
}