package org.soonhyung.beautynote.common;

import android.app.Service;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import org.soonhyung.beautynote.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by soonhyung on 2017-09-08.
 */

public class Utils {

    public static String getProperty(Context context, String key) throws IOException {
        Resources resources = context.getResources();
        InputStream rawResource = resources.openRawResource(R.raw.config);
        Properties properties = new Properties();
        properties.load(rawResource);

        return properties.getProperty(key, null);
    }

    public static String getQuery(Context context, String key) throws IOException {
        Resources resources = context.getResources();
        InputStream rawResource = resources.openRawResource(R.raw.db);
        Properties properties = new Properties();
        properties.load(rawResource);

        return properties.getProperty(key, null);
    }

    public static void saveSharedPreferences(Context context, String key, String text) {
        SharedPreferences pref = context.getSharedPreferences("profile", Service.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString(key,text);
        edit.commit();
    }

    public static String loadSharedPreferences(Context context, String key) {
        SharedPreferences pref = context.getSharedPreferences("profile", Service.MODE_PRIVATE);

        return pref.getString(key,null);
    }
}
