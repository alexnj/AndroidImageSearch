package com.alexnj.imagesearch;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.loopj.android.image.SmartImageView;

public class SingleImageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_image);
		
		ImageResult image = (ImageResult) getIntent().getSerializableExtra( "image" );
		
		SmartImageView ivLargeImage = (SmartImageView) findViewById( R.id.ivLargeImage );
		ivLargeImage.setImageUrl(image.getFullUrl() );
		setTitle("");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.single_image, menu);
		return true;
	}
	
	public void onClickBack(MenuItem mi) {
		finish();
	}

}
