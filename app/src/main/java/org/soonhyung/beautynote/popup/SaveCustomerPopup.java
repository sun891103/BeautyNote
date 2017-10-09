package org.soonhyung.beautynote.popup;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

import org.json.JSONObject;
import org.soonhyung.beautynote.R;
import org.soonhyung.beautynote.common.AQueryCallback;
import org.soonhyung.beautynote.common.AlertUtils;
import org.soonhyung.beautynote.common.URL;

import java.util.HashMap;
import java.util.Map;

public class SaveCustomerPopup extends Activity {

    EditText editCusId;
    EditText editCusName;
    EditText editCusBirth;
    EditText editCusTelNo;
    EditText editCusAddress;
    EditText editCusPoint;
    RadioGroup radioCusStatus;
    RadioGroup radioCusGender;
    String radioCusStatusValue = "10"; // 10 : 정상, 20 : 탈뢰
    String radioCusGenderValue = "20"; // 10 : 남성, 20 : 여성
    CheckBox chkSmsAgreeYn;
    Button btnCusSave;
    Button bthCusCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_save_customer);

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
        editCusId = (EditText) findViewById(R.id.edit_cus_id);
        editCusName = (EditText) findViewById(R.id.edit_cus_name);
        editCusBirth = (EditText) findViewById(R.id.edit_cus_birth);
        editCusTelNo = (EditText) findViewById(R.id.edit_cus_tel_no);
        editCusAddress = (EditText) findViewById(R.id.edit_cus_address);
        editCusPoint = (EditText) findViewById(R.id.edit_cus_point);
        radioCusStatus = (RadioGroup) findViewById(R.id.radio_cus_status);
        radioCusGender = (RadioGroup) findViewById(R.id.radio_cus_gender);
        chkSmsAgreeYn = (CheckBox) findViewById(R.id.chk_sms_agree_yn);
        btnCusSave = (Button) findViewById(R.id.btn_cus_save);
        bthCusCancel = (Button) findViewById(R.id.btn_cus_cancel);

        initEvent();
    }

    private void initEvent(){
        btnCusSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editCusName.getText().toString().equals("")){
                    AlertUtils.showOkDialog(SaveCustomerPopup.this, "알림", "이름을 입력하세요", null);
                    return;
                }

                if(editCusBirth.getText().toString().equals("")){
                    AlertUtils.showOkDialog(SaveCustomerPopup.this, "알림", "생년월일을 입력하세요", null);
                    return;
                }

                if(editCusTelNo.getText().toString().equals("")){
                    AlertUtils.showOkDialog(SaveCustomerPopup.this, "알림", "연락처를 입력하세요", null);
                    return;
                }

                if(editCusBirth.getText().toString().length() != 8){
                    AlertUtils.showOkDialog(SaveCustomerPopup.this, "알림", "생년월일을 확인하세요", null);
                    return;
                }

                AlertUtils.showYesNoDialog(SaveCustomerPopup.this, "알림", "입력한 내용을 저장하시겠습니까?", new AlertUtils.YesNoDialogCallBack() {
                    @Override
                    public void onYes() {
                        saveCustomer();
                    }

                    @Override
                    public void onNo() {

                    }
                });
            }
        });

        bthCusCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editCusName.getText().toString().equals("")||!editCusBirth.getText().toString().equals("")||!editCusTelNo.getText().toString().equals("")){
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

        radioCusStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.radio_cus_status_normal : radioCusStatusValue = "10";
                        break;
                    case R.id.radio_cus_status_leave : radioCusStatusValue = "20";
                        break;
                }
            }
        });

        radioCusGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.radio_cus_gender_man : radioCusGenderValue = "10";
                        break;
                    case R.id.radio_cus_gender_woman : radioCusGenderValue = "20";
                        break;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(!editCusName.getText().toString().equals("")||!editCusBirth.getText().toString().equals("")||!editCusTelNo.getText().toString().equals("")){
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

    private void saveCustomer(){
        AlertUtils.showSaveDialog(SaveCustomerPopup.this);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", editCusName.getText().toString());
        map.put("birth", editCusBirth.getText().toString());
        map.put("telNo", editCusTelNo.getText().toString());
        map.put("address", editCusAddress.getText().toString());
        map.put("status", radioCusStatusValue);
        map.put("gender", radioCusGenderValue);
        map.put("smsAgreeYn", chkSmsAgreeYn.isChecked() ?  "10" : "20");
        map.put("point", editCusPoint.getText().toString());

        new AQueryCallback(URL.saveCustomer, map, getApplicationContext(), new AQueryCallback.AQueryCallBackListener() {
            @Override
            public void httpRequestComplete(JSONObject jsonObject) {
                if (jsonObject.optString("resultCode").equals("0000")) {
                    AlertUtils.dismissSaveDialog(SaveCustomerPopup.this);
                    AlertUtils.showOkDialog(SaveCustomerPopup.this, "알림", "고객이 정상적으로 등록되었습니다.", new AlertUtils.OkDialogCallBack() {
                        @Override
                        public void onOk() {
                            finish();
                        }
                    });
                }else{
                    AlertUtils.dismissSaveDialog(SaveCustomerPopup.this);
                    AlertUtils.showOkDialog(SaveCustomerPopup.this, "알림", jsonObject.optString("result"), null);
                }
            }

            @Override
            public void httpRequestError() {
                AlertUtils.dismissSaveDialog(SaveCustomerPopup.this);
                AlertUtils.showOkDialog(SaveCustomerPopup.this, "알림", "인터넷 환경이 불안정 합니다./r/n다시 시도해 주세요.", null);
            }
        });
    }
}
