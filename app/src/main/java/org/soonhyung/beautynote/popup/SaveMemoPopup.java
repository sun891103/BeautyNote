package org.soonhyung.beautynote.popup;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.soonhyung.beautynote.R;

public class SaveMemoPopup extends Activity {

    EditText editSubject;
    EditText editComment;
    LinearLayout linearMemo;

    InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_save_memo);

        init();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    private void init(){
        editSubject = (EditText) findViewById(R.id.edit_memo_subject);
        editComment = (EditText) findViewById(R.id.edit_memo_comment);
        linearMemo = (LinearLayout) findViewById(R.id.linear_memo);

        imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
    }

    private void initEvent(){
        linearMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imm.hideSoftInputFromWindow(editSubject.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(editComment.getWindowToken(), 0);
            }
        });
    }
}
