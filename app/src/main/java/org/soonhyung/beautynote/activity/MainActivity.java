package org.soonhyung.beautynote.activity;

import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;

import org.soonhyung.beautynote.R;
import org.soonhyung.beautynote.common.AlertUtils;

import static org.soonhyung.beautynote.R.id.drawer_layout;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ListView menuListview;

    private FloatingActionButton fab;
    private int w, h, r, fx, fy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        init();
        initEvent();
    }

    private void init(){
        drawerLayout = (DrawerLayout) findViewById(drawer_layout);
        menuListview = (ListView) findViewById(R.id.menu_listview);
        fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    private void initEvent(){
        fab.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    fx = (int) motionEvent.getRawX();
                    fy = (int) motionEvent.getRawY();
                } else if(motionEvent.getAction() == MotionEvent.ACTION_MOVE){
                    if((int)motionEvent.getX() > r || (int)motionEvent.getX() < w - r) {
                        fab.setX((int) motionEvent.getRawX() - r);
                    }
                    if((int)motionEvent.getY() > r || (int)motionEvent.getY() < h - r) {
                        fab.setY((int)motionEvent.getRawY() - r);
                    }
                } else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(Math.abs(fx - (int)fab.getX()) < 200 && Math.abs(fy - (int)fab.getY()) < 200) {
                        drawerLayout.openDrawer(menuListview);
                    }
                }

                return true;
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            display.getSize(size);
            this.w = size.x;
            this.h = size.y;
        }

        r = fab.getWidth() / 2;

        fab.setX(w - r * 3);
        fab.setY(h - r * 3);
    }

    @Override
    public void onBackPressed() {
        AlertUtils.showYesNoDialog(MainActivity.this, "뷰티노트", "종료하시겠습니까?", new AlertUtils.YesNoDialogCallBack() {
            @Override
            public void onYes() {
                ActivityCompat.finishAffinity(MainActivity.this);
                System.runFinalizersOnExit(true);
                System.exit(0);
            }

            @Override
            public void onNo() {

            }
        });
    }
}
