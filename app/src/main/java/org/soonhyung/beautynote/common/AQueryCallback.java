package org.soonhyung.beautynote.common;

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
	private AQueryCallBackListener listener;

	/**
	 * @param url 보낼주소
	 * @param mapObj 파라미터
	 * @param context
	 * @param listener 콜백받을 인터페이스
	 */
	public AQueryCallback(String url, Map<String, Object> mapObj, Context context, AQueryCallBackListener listener){
		this.url = url;
		this.mapObj = mapObj;
		this.mContext = context;
		this.listener = listener;

		init();
	}

	private void init(){
		com.androidquery.AQuery ap = new com.androidquery.AQuery(mContext);

		ap.ajax(url, mapObj, JSONObject.class, new AjaxCallback<JSONObject>(){
			@Override
			public void callback(String url, JSONObject object, AjaxStatus status) {
				if (object != null) {
					try {
						listener.httpRequestComplete(object);
					} catch (Exception e) {
						e.printStackTrace();
						listener.httpRequestError();
					}
				}
			}
		});
	}

	public interface AQueryCallBackListener{
		void httpRequestComplete(JSONObject jsonObject);
		void httpRequestError();
	}
}
