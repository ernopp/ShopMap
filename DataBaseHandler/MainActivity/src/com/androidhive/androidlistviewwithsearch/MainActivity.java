package com.androidhive.androidlistviewwithsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.androidhive.androidlistviewwithsearch.Item;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {
	
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
        
        
        DatabaseHandler db = new DatabaseHandler(this);
        
        // Listview Data
        String products[] = {"Air Freshener","Aluminum Foil", "Ammonia","Apples", "Apple Sauce","Auto Supplies", "Baby Food", "Bacon", "Bags, lunch", "Bags, sandwich","Bags, storage","Bags, trash","Bags, vacuum","Bagels",
        						"Bakery Goods", "Baking Powder",
        						"Baking Soda", "Bananas","Batteries", "Beans", "Beans, refried", "Beef, hamburger", "Beef Jerky", "Beef, roast", "Beef, steak", "Beer", "Beets", "Berries", "Biscuit Mix", "Bleach", "Bouillon cubes",
        						"Bread crumbs", "Bread, French", "Bread", "Broccoli", "Broom", "Buns", "Butter", "Cabbage", "Cake", "Cake / brownie mix", "Candles", "Candy","Cards","Carpet Cleaner", "Carrots", "Celery", "Cereal",
        						"Charcoal", "Cheese, block", "Cheese, cottage", "Cheese, parmesan", "Cheese, sliced", "Cheese, spread", "Cherries", "Chicken", "Chili", "Chili beans", "Chinese food", "Chips, potato", "Chips, tortilla",
        						"Chocolate, baking", "Chocolate Chips","Cleaner", "Cleanser", "Cocoa", "Cocoa Mix", "Coconut", "Coffee", "Cookies", "Cooking Spray", "Corn","Corn meal", "Corn Starch", "Corn Syrup", "Crackers", "Cream Cheese", 
        						"Cream, non-dairy", "Cream, whipping", "Croutons", "Cucumber", "Dessert", "Detergent, laundry","Dips", "Dishwasher soap","Drink Mix", "Eggs", "Evaporated Milk", "Fish", "Flour", "Fruit, canned", "Fruit, dried",
        						"Fruit, fresh", "Fruit, frozen", "Garlic", "Gelatin", "Graham Crackers", "Granola Bars", "Grapefruit", "Gravy", "Grenn Pepper", "Gum", "Ham", "Honey", "Hot Dogs", "Ice, block, cube", "Ice Cream", "Ice Cream Cones",
        						"Ice Cream Toppings", "Jam / Jelly", "Juice, bottled", "Juice, frozen", "Ketchup", "Lemons", "Lemon Juice", "Lettuce"};
        
     //   db.fillDatabase(products);

        List<Item> itemList = db.getAllItems();
        
        for (Item cn : itemList) {
            String log = "Id: "+cn.getItemId()+" ,Name: " + cn.getItemName() + " ,Category: " + cn.getItemCategory()+ " ,Location: " 
        + cn.getItemLocation() ;
                // Writing Contacts to log
        Log.d("Name: ", log);
       
        }
       // db.addItem(new Item ("battikh", null, null));
      //  Item test = (db.getItem(2));
        
        //Log.d("Name:", "Displaying new Item :"+test.getItemId()+" "+test.getItemName()+" "+test.getItemCategory()+" "+test.getItemLocation());
        
   
      
        
        lv = (ListView) findViewById(R.id.list_view);
        inputSearch = (EditText) findViewById(R.id.inputSearch);
        
        // Adding items to listview
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, products);
        lv.setAdapter(adapter);
        
        /**
         * Enabling Search Filter
         * */
        inputSearch.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
				// When user changed the Text
				MainActivity.this.adapter.getFilter().filter(cs);	
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
    }
    
}
