package org.soonhyung.beautynote.common;

import java.util.HashMap;
import java.util.Map;

public class Dictionary {

	private Map<String,Object> 	map;

	public Dictionary() {
		map = new HashMap<String,Object>();
	}

	public Dictionary(String key, String value) {
		map = new HashMap<String,Object>();
		map.put(key, value);
	}

	public void addString(String key, String value) {
		map.put(key, value);
	}

	public String getString(String key) {
		return (String)map.get(key);
	}

}
