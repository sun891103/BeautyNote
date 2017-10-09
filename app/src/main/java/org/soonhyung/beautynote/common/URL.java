package org.soonhyung.beautynote.common;

import android.content.Context;

import java.io.IOException;

/**
 * Created by soonhyung on 2017-10-09.
 */

public class URL {
    private static String url;

    public static String saveCustomer = "saveCustomer.do";
    public static String selReserve = "selReserve.do";

    public static void set(Context context){
        try {
            url = Utils.getProperty(context, "config.Host") + ":"
                + Utils.getProperty(context, "config.Port") + "/"
                + Utils.getProperty(context, "config.Directory") + "/";

            saveCustomer = url + saveCustomer;
            selReserve = url + selReserve;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
