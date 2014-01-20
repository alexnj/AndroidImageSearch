package com.alexnj.imagesearch;

import java.io.Serializable;

import com.loopj.android.image.SmartImageView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SingleImageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_image);
		
		ImageResult image = (ImageResult) getIntent().getSerializableExtra( "image" );
		
		SmartImageView ivLargeImage = (SmartImageView) findViewById( R.id.ivLargeImage );
		ivLargeImage.setImageUrl(image.getFullUrl() );
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.single_image, menu);
		return true;
	}

}
