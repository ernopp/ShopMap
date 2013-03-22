package com.example.shop_map;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ItemsActivity extends MainActivity {

	// Listview Adapter
	ArrayAdapter<String> adapter;
	
	ArrayList<Item> selectedItems;
	ArrayList<String> itemNames;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_items);

		selectedItems = new ArrayList<Item>();
		itemNames = new ArrayList<String>();
		
		Intent intent = getIntent();
		
		selectedItems = intent.getParcelableArrayListExtra("selectedItems");
		
		ListView lv = (ListView) findViewById(R.id.listView2);
		
		for(Item i: selectedItems) {
			itemNames.add(i.getName());
		}
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, itemNames);
		
		lv.setAdapter(adapter);

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_items, menu);
		return true;
	}

}

