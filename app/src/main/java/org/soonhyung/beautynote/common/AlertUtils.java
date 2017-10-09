package org.soonhyung.beautynote.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Build;

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            ad.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    if(callBack != null){
                        callBack.onOk();
                    }
                }
            });
        }

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

    private static ProgressDialog saveDialog;
    private static ProgressDialog selDialog;

    public static void showSaveDialog(Activity activity) {
        saveDialog = new ProgressDialog(activity);
        saveDialog.setTitle("저장중");
        saveDialog.setMessage("잠시만 기다려 주세요.");
        saveDialog.setCancelable(false);
        saveDialog.show();
    }

    public static void dismissSaveDialog(Activity activity) {
        if(saveDialog.isShowing()){
            saveDialog.dismiss();
        }
    }

    public static void showSelDialog(Activity activity) {
        saveDialog = new ProgressDialog(activity);
        saveDialog.setTitle("조회중");
        saveDialog.setMessage("잠시만 기다려 주세요.");
        saveDialog.setCancelable(false);
        saveDialog.show();
    }

    public static void dismissSelDialog(Activity activity) {
        if(saveDialog.isShowing()){
            saveDialog.dismiss();
        }
    }
}
