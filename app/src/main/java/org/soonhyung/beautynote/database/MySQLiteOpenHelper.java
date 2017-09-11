package org.soonhyung.beautynote.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.soonhyung.beautynote.common.Utils;

import java.io.IOException;

/**
 * Created by soonhyung on 2017-09-12.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    Context context;

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = null;
        try {
            sql = Utils.getQuery(context, "db.createMemo");
        } catch (IOException e) {
            e.printStackTrace();
        }
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "";
        db.execSQL(sql);
        onCreate(db);
    }
}
