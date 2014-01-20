package com.alexnj.imagesearch;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class SearchActivity extends Activity {
	EditText etSearchQuery;
	GridView gvResults;
	Button btnSearch;
	ArrayList<ImageResult> irResults = new ArrayList<ImageResult>();
	ImageResultArrayAdapter imageAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		setupViews();
		imageAdapter = new ImageResultArrayAdapter( this, irResults );
		gvResults.setAdapter( imageAdapter );
		
		gvResults.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View parent, int position, long arg3) {
				Intent i = new Intent(getApplicationContext(), SingleImageActivity.class );
				
				ImageResult imageResult = irResults.get( position );
				i.putExtra ( "image" , imageResult );
				startActivity( i );
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}
	
	public void setupViews() {
		etSearchQuery = (EditText) findViewById(R.id.etSearchQuery);
		gvResults = (GridView) findViewById(R.id.gvResults);
		btnSearch = (Button) findViewById(R.id.btnSearch);
	}
	
	public void onImageSearch(View v) {
		String query = etSearchQuery.getText().toString();
		
		AsyncHttpClient client = new AsyncHttpClient();
		
		client.get("http://ajax.googleapis.com/ajax/services/search/images?rsz=8&start=" + 0 
				+ "&v=1.0&q==" + Uri.encode( query ), 
			new JsonHttpResponseHandler() {
				@Override
				public void onSuccess( JSONObject response ) {
					JSONArray imageJsonResults = null;
					try {
						imageJsonResults= response.getJSONObject( "responseData" ).getJSONArray("results");
						irResults.clear();
						imageAdapter.addAll(ImageResult.fromJSONArray(imageJsonResults));
						Log.d("DEBUG", irResults.toString());
					} catch( JSONException e ) {
						e.printStackTrace();
					}
				}
			});
	}

}
