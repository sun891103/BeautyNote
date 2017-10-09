package org.soonhyung.beautynote.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import org.soonhyung.beautynote.R;
import org.soonhyung.beautynote.adapter.MainDrawerListAdapter;
import org.soonhyung.beautynote.common.AlertUtils;
import org.soonhyung.beautynote.common.Dictionary;
import org.soonhyung.beautynote.common.Utils;
import org.soonhyung.beautynote.database.MySQLiteOpenHelper;
import org.soonhyung.beautynote.popup.SaveCustomerPopup;

import java.io.IOException;
import java.util.ArrayList;

import static org.soonhyung.beautynote.R.id.drawer_menu;

public class MainActivity extends AppCompatActivity {

    public static MySQLiteOpenHelper helper;
    public static SQLiteDatabase db;
    private String dbName;
    private String dbVersion;

    private MainDrawerListAdapter mainDrawerListAdapter;
    private ArrayList<Dictionary> arrMenu = new ArrayList<Dictionary>();

    DrawerLayout drawerLayout;
    ListView menuListview;

    private FloatingActionButton fab;
    private int w, h, r, fx, fy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDB();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        init();
        initEvent();
    }

    private void initDB(){
        try {
            dbName = Utils.getQuery(this, "db.name");
            dbVersion = Utils.getQuery(this, "db.version");
        } catch (IOException e) {
            e.printStackTrace();
        }
        helper = new MySQLiteOpenHelper(this, dbName, null, Integer.parseInt(dbVersion));
        db = helper.getWritableDatabase();
    }

    public static Cursor select(String sql){
        return db.rawQuery(sql, null);
    }

    public static void execSql(String sql){
        db.execSQL(sql);
    }

    private void init(){
        drawerLayout = (DrawerLayout) findViewById(drawer_menu);
        menuListview = (ListView) findViewById(R.id.list_menu);
        View header = LayoutInflater.from(this).inflate(R.layout.drawer_list_header, null);
        menuListview.addHeaderView(header);
        Dictionary dic = new Dictionary();
        dic.addString("id", "0");
        dic.addString("name", "매출등록");
        arrMenu.add(dic);
        dic = new Dictionary();
        dic.addString("id", "1");
        dic.addString("name", "매출조회");
        arrMenu.add(dic);
        dic = new Dictionary();
        dic.addString("id", "2");
        dic.addString("name", "예약등록");
        arrMenu.add(dic);
        dic = new Dictionary();
        dic.addString("id", "3");
        dic.addString("name", "예약조회");
        arrMenu.add(dic);
        dic = new Dictionary();
        dic.addString("id", "4");
        dic.addString("name", "고객등록");
        arrMenu.add(dic);
        dic = new Dictionary();
        dic.addString("id", "5");
        dic.addString("name", "고객조회");
        arrMenu.add(dic);
        dic = new Dictionary();
        dic.addString("id", "6");
        dic.addString("name", "카카오톡 보내기");
        arrMenu.add(dic);
        dic = new Dictionary();
        dic.addString("id", "7");
        dic.addString("name", "카메라");
        arrMenu.add(dic);
        dic = new Dictionary();
        dic.addString("id", "8");
        dic.addString("name", "갤러리");
        arrMenu.add(dic);
        mainDrawerListAdapter = new MainDrawerListAdapter(MainActivity.this, R.layout.drawer_list, arrMenu);
        menuListview.setAdapter(mainDrawerListAdapter);

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

        menuListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String id = arrMenu.get(i - 1).getString("id");
                String name = arrMenu.get(i - 1).getString("name");

                switch (id){
                    case "4" :
                        startActivity(new Intent(getApplicationContext(), SaveCustomerPopup.class));
                        break;
                    default :
                        AlertUtils.showOkDialog(MainActivity.this, name, "개발중", null);
                        break;
                }
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
        if(drawerLayout.isDrawerOpen(menuListview)){
            drawerLayout.closeDrawer(menuListview);
            return;
        }

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
