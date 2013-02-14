package com.androidhive.androidlistviewwithsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import android.app.Activity;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class ItemActivity extends Activity {
	final String SETTING_TODOLIST = "shoplist";
	// declare arraylist to store string of selected items
    final ArrayList<String> mySelected = new ArrayList<String>();
	// List view
	private ListView lv;
	
	// Listview Adapter
	ArrayAdapter<String> adapter;
	
	// Search EditText
	EditText inputSearch;
	
	
	// ArrayList for Listview
	ArrayList<HashMap<String, String>> productList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        
        
        Button clearBtn = (Button) findViewById(R.id.clearBtn);
        clearBtn.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ClearSelections();
				
			}
		});
        
        // Listview Data
        String products[] = {"Air Freshener","Aluminum Foil", "Ammonia","Apples", "Apple Sauce","Auto Supplies", "Baby Food", "Bacon", "Bags, lunch", "Bags, sandwich","Bags, storage","Bags, trash","Bags, vacuum","Bagels",
        						"Bakery Goods", "Baking Powder","Baking Soda", "Bananas","Batteries", "Beans", "Beans, refried", "Beef, hamburger", "Beef Jerky", "Beef, roast", "Beef, steak", "Beer", "Beets", "Berries", "Biscuit Mix", "Bleach", "Bouillon cubes",
        						"Bread crumbs", "Bread, French", "Bread", "Broccoli", "Broom", "Buns", "Butter", "Cabbage", "Cake", "Cake / brownie mix", "Candles", "Candy","Cards","Carpet Cleaner", "Carrots", "Celery", "Cereal",
        						"Charcoal", "Cheese, block", "Cheese, cottage", "Cheese, parmesan", "Cheese, sliced", "Cheese, spread", "Cherries", "Chicken", "Chili", "Chili beans", "Chinese food", "Chips, potato", "Chips, tortilla",
        						"Chocolate, baking", "Chocolate Chips","Cleaner", "Cleanser", "Cocoa", "Cocoa Mix", "Coconut", "Coffee", "Cookies", "Cooking Spray", "Corn","Corn meal", "Corn Starch", "Corn Syrup", "Crackers", "Cream Cheese", 
        						"Cream, non-dairy", "Cream, whipping", "Croutons", "Cucumber", "Dessert", "Detergent, laundry","Dips", "Dishwasher soap","Drink Mix", "Eggs", "Evaporated Milk", "Fish", "Flour", "Fruit, canned", "Fruit, dried",
        						"Fruit, fresh", "Fruit, frozen", "Garlic", "Gelatin", "Graham Crackers", "Granola Bars", "Grapefruit", "Gravy", "Green Pepper", "Gum", "Ham", "Honey", "Hot Dogs", "Ice, block, cube", "Ice Cream", "Ice Cream Cones",
        						"Ice Cream Toppings", "Jam / Jelly", "Juice, bottled", "Juice, frozen", "Ketchup", "Lemons", "Lemon Juice", "Lettuce"};
      
        
        lv = (ListView) findViewById(R.id.list_view);
        inputSearch = (EditText) findViewById(R.id.inputSearch);
        
        // Adding items to listview
        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // NOT USED: Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, products);
        
        // Assign adapter to ListView
        lv.setAdapter(adapter);
        
        lv.setItemsCanFocus(false);
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        
        LoadSelections();
        
        
        
        /**
         * Enabling Search Filter
         * */
        inputSearch.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
				// When user changed the Text
				ItemActivity.this.adapter.getFilter().filter(cs);	
			}
			
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
		

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
        
        
        
        
        //Listener to receive selections and store item strings in mySelected arraylist
        lv.setOnItemClickListener(new OnItemClickListener() {
       	
        	public void onItemClick(AdapterView<?> a, View v, int position, long id){
        		Toast.makeText(getApplicationContext(), "Clicked on ListItem Number " + position, Toast.LENGTH_SHORT).show();   																		
        		mySelected.add((lv.getItemAtPosition(position)).toString());
        		
        	}
        } 	);
        
        
        
    }
    
    // define behavior if app is exited 
    // make sure to save selections the user has made
    @Override
    protected void onPause(){
    	SaveSelections();
    	super.onPause();
    }
    
    // for the Clear Selctions Button
    // uncheck all items
    private void ClearSelections(){
    	int count = this.lv.getAdapter().getCount();
    	
    	for (int i = 0; i<count; i++){
    		this.lv.setItemChecked(i, false);
    	}
    	// save the cleared selections
    	SaveSelections();
    }
    
    private void LoadSelections() {
    	SharedPreferences settingsActivity = getPreferences(MODE_PRIVATE);
    	
    	if (settingsActivity.contains(SETTING_TODOLIST)){
    		String savedItems = settingsActivity.getString(SETTING_TODOLIST, "");
    		
    		this.mySelected.addAll(Arrays.asList(savedItems.split(",")));
    		int count = this.lv.getAdapter().getCount();
    		
    		for (int i = 0; i < count; i++) {
    			String currentItem = (String) this.lv.getAdapter().getItem(i);
    			if (this.mySelected.contains(currentItem)) {
    				this.lv.setItemChecked(i, true);
    				
    			}
    	}
    	
    }
    }
    
    private void SaveSelections(){
    	SharedPreferences settingsActivity = getPreferences(MODE_PRIVATE);
    	SharedPreferences.Editor prefEditor = settingsActivity.edit();
    	
    	String savedItems = getSavedItems();
    	prefEditor.putString(SETTING_TODOLIST, savedItems);
    	prefEditor.commit();
    }
    private String getSavedItems() {
    	String savedItems = "";
    	int count = this.lv.getAdapter().getCount();
    	
    	for (int i = 0; i < count; i++){
    		if (this.lv.isItemChecked(i)) {
    			if (savedItems.length() > 0 ){
    				savedItems += "," + this.lv.getItemAtPosition(i);
    				
    			}
    			else{
    				savedItems += this.lv.getItemAtPosition(i);
    			}
    			}
    		}
    	return savedItems;
    	
    
    }
    
}
