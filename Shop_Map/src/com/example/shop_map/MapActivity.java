package com.example.shop_map;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;

public class MapActivity extends Activity {

    DrawView drawView;
	ArrayList<Item> allItems;

    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
	        
		Intent intent = getIntent();
		allItems = intent.getParcelableArrayListExtra("allItems");

        drawView = new DrawView(this, allItems, 0);
        drawView.setBackgroundColor(Color.WHITE);
        setContentView(drawView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_map, menu);
		return true;
	}

}
