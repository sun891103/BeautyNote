package org.soonhyung.beautynote.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * Created by soonhyung on 2017-09-06.
 */

public class AlertUtils {

    public interface OkDialogCallBack {
        void onOk();
    }

    public static void showOkDialog(final Activity activity, String title, String text, final OkDialogCallBack callBack){
        AlertDialog.Builder ad = new AlertDialog.Builder(activity);
        ad.setTitle(title);
        ad.setMessage(text);
        ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                if(callBack != null){
                    callBack.onOk();
                }
            }
        });

        if(!activity.isFinishing()){
            ad.create();
            ad.show();
        }
    }

    public interface YesNoDialogCallBack {
        void onYes();
        void onNo();
    }

    public static void showYesNoDialog(final Activity activity, String title, String text, final YesNoDialogCallBack callBack){
        AlertDialog.Builder ad = new AlertDialog.Builder(activity);
        ad.setTitle(title);
        ad.setMessage(text);
        ad.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                if(callBack != null){
                    callBack.onNo();
                }
            }
        });
        ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                if(callBack != null){
                    callBack.onYes();
                }
            }
        });

        if(!activity.isFinishing()){
            ad.create();
            ad.show();
        }
    }

}
