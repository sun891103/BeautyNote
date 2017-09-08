package org.soonhyung.beautynote.common;

import android.app.ProgressDialog;
import android.content.Context;

import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AQueryCallback {

	private String url;
	private Map<String, Object> mapObj = new HashMap<>();
	private Context mContext;
	private String proTitle;
	private String proMessage;
	private boolean flag;
	private AQueryCallBackListener listener;
	private String element;
	private ProgressDialog dialog;

	/**
	 * @param url 보낼주소
	 * @param mapObj 파라미터
	 * @param context
	 * @param proTitle 로딩에 타이틀
	 * @param proMessage 로딩에 메시지
	 * @param element 받을 형식
	 * @param listener 콜백받을 인터페이스
	 * @param flag 다이얼로그 사용 true 미사용 false
	 */
	public AQueryCallback(String url, Map<String, Object> mapObj, Context context, String proTitle, String proMessage, boolean flag, String element, AQueryCallBackListener listener){
		this.url = url;
		this.mapObj = mapObj;
		this.mContext = context;
		this.proTitle = proTitle;
		this.proMessage = proMessage;
		this.listener = listener;
		this.element = element;
		this.flag = flag;

		init();
	}

	private void init(){
		dialog = new ProgressDialog(mContext);
		dialog.setTitle(proTitle);
		dialog.setMessage(proMessage);

		com.androidquery.AQuery ap = new com.androidquery.AQuery(mContext);

		if (flag) {
			ap.progress(dialog).ajax(url, mapObj, JSONObject.class, new AjaxCallback<JSONObject>(){
				@Override
				public void callback(String url, JSONObject object,
									 AjaxStatus status) {
					if (object != null) {
						try {
							listener.httpRequestComplete(object);
							if (dialog.isShowing()) {
								dialog.dismiss();
							}
						} catch (Exception e) {
							if (dialog.isShowing()) {
								dialog.dismiss();
							}
							e.printStackTrace();
							listener.httpRequestError();
						}
					}
				}
			});
		}else{
			ap.ajax(url, mapObj, JSONObject.class, new AjaxCallback<JSONObject>(){
				@Override
				public void callback(String url, JSONObject object,
									 AjaxStatus status) {
					if (object != null) {
						try {
							listener.httpRequestComplete(object);
							if (dialog.isShowing()) {
								dialog.dismiss();
							}
						} catch (Exception e) {
							if (dialog.isShowing()) {
								dialog.dismiss();
							}
							e.printStackTrace();
							listener.httpRequestError();
						}
					}
				}
			});
		}
	}

	public interface AQueryCallBackListener{
		void httpRequestComplete(JSONObject jsonObject);
		void httpRequestError();
	}
}
