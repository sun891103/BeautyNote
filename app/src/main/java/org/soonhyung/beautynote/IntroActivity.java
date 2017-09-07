package org.soonhyung.beautynote;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_intro);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        //init();

        Intent intent = new Intent(IntroActivity.this, MainActivity.class);
        startActivity(intent);
    }

    /*@Override
    public void onWindowFocusChanged(boolean hasFocus) {
        int w = 0;
        int h = 0;
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            display.getSize(size);
            w = size.x;
            h = size.y;
        }

        ImageView introBackImage = (ImageView) findViewById(R.id.introBackImage);
        Bitmap img = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        img = Bitmap.createScaledBitmap(img, w, h, true);

        introBackImage.setImageBitmap(img);
    }

    private void init(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/
}
