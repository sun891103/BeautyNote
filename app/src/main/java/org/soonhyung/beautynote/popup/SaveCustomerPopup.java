package org.soonhyung.beautynote.popup;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import org.soonhyung.beautynote.R;

public class SaveCustomerPopup extends Activity {

    EditText editSubject;
    EditText editComment;
    Button btnMemoSave;
    Button btnMemoCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_save_customer);

        //init();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    /*private void init(){
        editSubject = (EditText) findViewById(R.id.edit_memo_subject);
        editComment = (EditText) findViewById(R.id.edit_memo_comment);
        btnMemoSave = (Button) findViewById(R.id.btn_memo_save);
        btnMemoCancel = (Button) findViewById(R.id.btn_memo_cancel);

        initEvent();
    }

    private void initEvent(){
        btnMemoSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editSubject.getText().toString().equals("")){
                    AlertUtils.showOkDialog(SaveCustomerPopup.this, "알림", "제목을 입력하세요", null);
                    return;
                }

                if(editComment.getText().toString().equals("")){
                    AlertUtils.showOkDialog(SaveCustomerPopup.this, "알림", "내용을 입력하세요", null);
                    return;
                }

                AlertUtils.showYesNoDialog(SaveCustomerPopup.this, "알림", "입력한 내용을 저장하시겠습니까?", new AlertUtils.YesNoDialogCallBack() {
                    @Override
                    public void onYes() {
                        String sql = "";
                        try {
                            sql = Utils.getQuery(getApplicationContext(), "db.insMemo");

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        MainActivity.execSql(String.format(sql, editSubject.getText().toString(), editComment.getText().toString()));

                        finish();
                    }

                    @Override
                    public void onNo() {

                    }
                });
            }
        });

        btnMemoCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editSubject.getText().toString().equals("")||!editComment.getText().toString().equals("")){
                    AlertUtils.showYesNoDialog(SaveCustomerPopup.this, "알림", "작성한 내역이 취소됩니다.", new AlertUtils.YesNoDialogCallBack() {
                        @Override
                        public void onYes() {
                            finish();
                        }

                        @Override
                        public void onNo() {

                        }
                    });
                }else {
                    finish();
                }
            }
        });
    }*/

    @Override
    public void onBackPressed() {
        /*if(!editSubject.getText().toString().equals("")||!editComment.getText().toString().equals("")){
            AlertUtils.showYesNoDialog(SaveCustomerPopup.this, "알림", "작성한 내역이 취소됩니다.", new AlertUtils.YesNoDialogCallBack() {
                @Override
                public void onYes() {
                    finish();
                }

                @Override
                public void onNo() {

                }
            });
        }else {
            finish();
        }*/
    }
}
