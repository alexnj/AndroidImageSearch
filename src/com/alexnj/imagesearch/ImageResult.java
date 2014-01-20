package com.alexnj.imagesearch;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ImageResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2893050467496880646L;
	private String urlFull;
	private String urlThumb;
	
	public ImageResult(JSONObject json) {
		try {
			this.urlFull = json.getString("url");
			this.urlThumb = json.getString("tbUrl");
		} catch( JSONException ex ) {
			this.urlFull = null;
			this.urlThumb = null;
		}
	}
	
	public String getFullUrl() {
		return urlFull;
	}
	
	public String getThumbUrl() {
		return urlThumb;
	}
	
	public String toString() {
		return urlThumb;
	}

	public static ArrayList<ImageResult> fromJSONArray(
			JSONArray array) {
		
		ArrayList<ImageResult> results = new ArrayList<ImageResult>();
		for(int x=0; x<array.length(); x++ ) {
			try {
				results.add( new ImageResult(array.getJSONObject(x)));
			} catch( JSONException e ) {
				e.printStackTrace();
			}
		}
		return results;
	}
	
}
