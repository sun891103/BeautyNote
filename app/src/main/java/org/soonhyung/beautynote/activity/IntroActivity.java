package org.soonhyung.beautynote.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import org.soonhyung.beautynote.R;
import org.soonhyung.beautynote.common.Global;
import org.soonhyung.beautynote.common.Utils;

import java.io.IOException;

public class IntroActivity extends AppCompatActivity {

    private StartThread mThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_intro);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        init();
    }

    private void init(){
        try {
            Utils.saveSharedPreferences(IntroActivity.this, Global.S_VERSION, Utils.getProperty(getApplicationContext(), Global.C_VERSION));
            Utils.saveSharedPreferences(IntroActivity.this, Global.S_URL, "http://" +
                    Utils.getProperty(getApplicationContext(), Global.C_HOST) + ":" +
                    Utils.getProperty(getApplicationContext(), Global.C_PORT) + "/"
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(mThread == null) {
            mThread = new StartThread();
            mThread.start();
        }
    }

    class StartThread extends Thread{
        @Override
        public void run() {
            try {
                sleep(1000);

                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    /*private void setHttpRequest(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("deviceType", "1");

        new AQueryCallback(Utils.loadSharedPreferences(IntroActivity.this, Global.S_URL)+"CM106000R.jsp", map, IntroActivity.this, "제목", "내용", true, "data", new AQueryCallback.AQueryCallBackListener() {

            @Override
            public void httpRequestError() {
                if (isFinishing()) {
                    AlertUtils.showOkDialog(IntroActivity.this, "알림", "인터넷 환경이 불안정합니다./r/n다시 시도해 주세요.", null);
                }
            }

            @Override
            public void httpRequestComplete(final JSONObject jsonObject) {
                if (jsonObject.optString("resultCode").equals("0000")) {
                    try {
                        if (!Utils.loadSharedPreferences(IntroActivity.this, Global.S_VERSION).equals(jsonObject.getJSONArray("rows").getJSONObject(0).getString("deviceVersion"))) {
                            AlertUtils.showOkDialog(IntroActivity.this, "알림", "앱의 업데이트가 필요합니다.\n확인 버튼을 누르시면 설치 페이지로 이동합니다.", new AlertUtils.OkDialogCallBack() {

                                @Override
                                public void onOk() {
                                    Intent intent = null;
                                    try {
                                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse(jsonObject.getJSONArray("rows").getJSONObject(0).getString("deviceUrl")));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    startActivity(intent);
                                    finish();
                                }
                            });
                        }else{
                            Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                            startActivity(intent);
                            overridePendingTransition(0, 0);
                            finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{

                }
            }
        });
    }*/
}
