package com.as400samplecode;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MyCountries extends Activity {
	
	ArrayList<Country> selectedCountries;
	ArrayAdapter<String> adapter;
	ArrayList<String> countryNames;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_countries);
		
		selectedCountries = new ArrayList<Country>();
		countryNames = new ArrayList<String>();
		
		Intent intent = getIntent();
		
		selectedCountries = intent.getParcelableArrayListExtra("selectedCountries");
		
		ListView lv = (ListView) findViewById(R.id.listView2);
		
		for(Country c: selectedCountries) {
			countryNames.add(c.getName());
		}
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, countryNames);
		
		lv.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_my_countries, menu);
		return true;
	}

}
