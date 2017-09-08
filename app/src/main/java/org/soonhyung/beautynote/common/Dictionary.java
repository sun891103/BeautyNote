package org.soonhyung.beautynote.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Dictionary {

	public static final int	DATA_STRING			= 0;
	public static final int	DATA_INTEGER		= 1;

	private Map<String,Object> 	map;
	private Map<String,Integer> dataTypeMap;

	public Dictionary() {
		map = new HashMap<String,Object>();
		dataTypeMap = new HashMap<String,Integer>();
	}

	public Dictionary(String[] key, String value[]) {
		map = new HashMap<String,Object>();
		dataTypeMap = new HashMap<String,Integer>();

		for(int i=0;i<key.length;i++) {
			map.put(key[i],value[i]);
			dataTypeMap.put(key[i],DATA_STRING);
		}
	}

	public Dictionary(String[] key, Integer value[]) {
		map = new HashMap<String,Object>();
		dataTypeMap = new HashMap<String,Integer>();

		for(int i=0;i<key.length;i++) {
			map.put(key[i],value[i]);
			dataTypeMap.put(key[i],DATA_INTEGER);
		}
	}	

	public Dictionary(String[] key, Object value[], int type[]) {
		map = new HashMap<String,Object>();
		dataTypeMap = new HashMap<String,Integer>();

		for(int i=0;i<key.length;i++) {
			map.put(key[i],value[i]);
			dataTypeMap.put(key[i],type[i]);
		}
	}

	public Dictionary(String url){
		if(url!=null && url.length()>0) {
			String urlData = url.substring(url.indexOf("/")+2);

			map = new HashMap<String,Object>();
			dataTypeMap = new HashMap<String,Integer>();

			String []splitParam = urlData.split("&");

			for(int i=0;i<splitParam.length;i++ )
			{
				int idx = 0;
				int len = 0;
				String value;
				String key;

				if( splitParam[i] != null 
						&& (len = splitParam[i].length()) > 0 
						&& (idx = splitParam[i].indexOf("=")) > -1 )
				{

					key = splitParam[i].substring(0,idx).trim();
					value = (len == idx+1) ? "" : splitParam[i].substring(idx+1).trim();
					map.put(key,value);
					dataTypeMap.put(key,DATA_STRING);
				}
			}
		}
	}

	public void addString(String key, String value) {
		map.put(key,value);
	}

	public String getString(String key) {
		return (String)map.get(key);
	}

	public int getSize() {
		if(map!=null)
			return map.size();
		else
			return 0;
	}

	public String[] getKeys(){
		String[] keys = new String[map.size()];
		Iterator<String> iterator = map.keySet().iterator();

		int count = 0;
		while(iterator.hasNext()){
			keys[count] = (String)iterator.next();
			count++;
		}
		return keys;
	}
}
