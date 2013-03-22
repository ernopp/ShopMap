package com.example.shop_map;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.util.Linkify;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.Toast;

/**
 * This class is the List Activity - the main activity of ShopMap 
 * 
 */
public class MainActivity extends Activity {
	// array adapter for list of items
	MyCustomAdapter dataAdapter = null;
	// ArrayList used to store selected Items
	ArrayList<Item> myItems = new ArrayList<Item>();
	ArrayList<Item> allItems;
	ArrayList<Recipe> allRecipes;
	ArrayList<Item> selectedRecipeItems;

	//shopNum used to keep track of shop selected
	int shopNum = -1;

	boolean firstTime = true;

	//TRIM to 42 items
	String products[] = {"Air Freshener","Aluminum Foil","Apples", "Apple Sauce","Baby Food", "Bacon", "Bags","Bagels",
			"Bakery Goods", "Baking Soda", "Bananas","Beans", "Beef", "Beer",   "Biscuit Mix",  
			"Bread", "Broccoli", 
			"Cheese", "Cherries", "Chicken", "Chili",  "Chips",
			"Corn","Corn meal", "Corn Starch", "Corn Syrup", "Crackers", "Cream Cheese", 
			"Cream", "Cucumber", "Dessert", 
			"Fruit", "Garlic", "Gum", "Ham", "Ice Cream", 
			"Jam / Jelly", "Juice", "Ketchup", "Lemons", "Lettuce", "Mangoes"};

	SpannableString provigoInfo = new SpannableString("Provigo\n3421 Avenue Du Parc\nMontr�al, QC H2X2H6\n514-281-0488");

	SpannableString metroInfo = new SpannableString("Metro\n3575 Avenue Du Parc\nMontr�al, QC H2X3P9\n514-843-3530");

	SpannableString shops[] = {provigoInfo, metroInfo}; 


	@Override
	/**
	 * This function is executed when the app is opened
	 * @param A bundle containing the saved state of the app
	 */
	protected void onCreate(Bundle savedInstanceState) {

		//call the parent implementation
		super.onCreate(savedInstanceState);
		// use res/layout/activity_main.xml for layout
		setContentView(R.layout.activity_main);

		Intent intent = getIntent();

		selectedRecipeItems = intent
				.getParcelableArrayListExtra("selectedRecipeItems");
		if (selectedRecipeItems == null)

			firstTime = true;
		else {
			firstTime = false;
			allItems = intent.getParcelableArrayListExtra("allItems");
		}

		// Generate list View from ArrayList
		displayListView();

		// create the recipes
		if (firstTime)
			createRecipes();

		else 
			allRecipes = intent.getParcelableArrayListExtra("allRecipes");

		checkButtonClick();
	}
	//initializes and displays all elements on the screen 
	private void displayListView() {

		// Initialize Array lists of groceries of type String and type Item:

		if (firstTime) {
			allItems = new ArrayList<Item>();

			// fill ArrayList of Items with elements of the "products" String
			// array
			for (int i = 0; i < products.length; i++) {
				allItems.add(new Item(i, products[i], false));
			}

		}

		else {
			for (int i = 0; i < selectedRecipeItems.size(); i++) {
				Item item = selectedRecipeItems.get(i);
				int id = item.id;
				item.setSelected(true);
				allItems.set(id, item);
			}
		}

		//create an ArrayAdapter (for the ListView) from the ArrayList of type Item
		dataAdapter = new MyCustomAdapter(this,R.layout.item_view, allItems);
		// initialize view elements (search bar and list of check-box items) from XML resources 
		ListView listView = (ListView) findViewById(R.id.list_view);
		EditText inputSearch = (EditText) findViewById(R.id.inputSearch);
		// Assign adapter to the ListView to fill the view with the product names of type Item
		listView.setAdapter(dataAdapter);

		/**
		 * Enabling Filter for Search Bar
		 * */
		inputSearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
				// When user changed the Text
				MainActivity.this.dataAdapter.getFilter().filter(cs);	
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

	private class MyCustomAdapter extends ArrayAdapter<Item> implements Filterable {

		private ArrayList<Item> itemList;
		private ArrayList<Item> fItems;
		private Filter filter;
		//constructor
		public MyCustomAdapter(Context context, int textViewResourceId, 
				ArrayList<Item> itemList) {
			super(context, textViewResourceId, itemList);
			this.itemList = new ArrayList<Item>(itemList);
			this.fItems = new ArrayList<Item>(itemList);

			//	this.itemList.addAll(itemList);

		}


		private class ViewHolder {
			//TextView code;
			CheckBox name;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder = null;
			Log.v("ConvertView", String.valueOf(position));

			if (convertView == null) {
				LayoutInflater vi = (LayoutInflater)getSystemService(
						Context.LAYOUT_INFLATER_SERVICE);
				convertView = vi.inflate(R.layout.item_view, null);

				holder = new ViewHolder();
				//holder.code = (TextView) convertView.findViewById(R.id.textView1);
				holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1); // grabs view defined by file 'item_view.xml'
				convertView.setTag(holder);

				holder.name.setOnClickListener( new View.OnClickListener() {  
					public void onClick(View v) {  
						CheckBox cb = (CheckBox) v ;  
						Item item = (Item) cb.getTag();  
						item.setSelected(cb.isChecked());
					}  
				});  
			} 
			else {
				holder = (ViewHolder) convertView.getTag();
			}


			Item item = fItems.get(position);
			if(item != null) {
				holder.name.setText(item.getName());
				holder.name.setChecked(item.isSelected());
				holder.name.setTag(item);
			}

			return convertView;

		}

		@Override
		public Filter getFilter(){

			if(filter == null){
				filter = new ItemFilter();
			}
			return filter;
		}

		private class ItemFilter extends Filter{
			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
				FilterResults results = new FilterResults();
				String prefix = constraint.toString().toLowerCase(Locale.getDefault());

				if (prefix == null || prefix.length() == 0) {
					ArrayList<Item> list = new ArrayList<Item>(itemList);
					results.values = list;
					results.count = list.size();
				}
				else{
					ArrayList<Item> list = new ArrayList<Item>(itemList);
					ArrayList<Item> nlist = new ArrayList<Item>();

					for (int i = 0; i<list.size(); i++){
						Item item = list.get(i);
						String value = item.getName();

						if(value.toLowerCase().contains(prefix.toLowerCase())) {
							nlist.add(item);
						}
						results.values = nlist;
						results.count = nlist.size();
					}
				}
				return results;
			}

			@SuppressWarnings("unchecked")
			@Override
			protected void publishResults(CharSequence constraint, FilterResults results) {
				fItems = (ArrayList<Item>)results.values;
				dataAdapter.notifyDataSetChanged();
				dataAdapter.clear();

				for(int i = 0; i<fItems.size(); i++){
					dataAdapter.add(fItems.get(i));
					dataAdapter.notifyDataSetChanged();
				}
			}
		}
	}
	
	private void createRecipes() {
		allRecipes = new ArrayList<Recipe>();
		ArrayList<Item> recipeItems = new ArrayList<Item>();

		// create the Pasta Alfredo recipe
		recipeItems.add(allItems.get(0));
		recipeItems.add(allItems.get(1));
		recipeItems.add(allItems.get(2));
		recipeItems.add(allItems.get(3));
		recipeItems.add(allItems.get(4));

		Recipe recipe1 = new Recipe("Pasta Alfredo", recipeItems, false);
		allRecipes.add(recipe1);

		recipeItems = new ArrayList<Item>();

		recipeItems.add(allItems.get(10));
		recipeItems.add(allItems.get(11));
		recipeItems.add(allItems.get(12));
		recipeItems.add(allItems.get(13));
		recipeItems.add(allItems.get(14));

		Recipe recipe2 = new Recipe("Butter Chicken", recipeItems, false);

		allRecipes.add(recipe2);
		
		recipeItems = new ArrayList<Item>();

		recipeItems.add(allItems.get(20));
		recipeItems.add(allItems.get(21));
		recipeItems.add(allItems.get(22));
		recipeItems.add(allItems.get(23));
		recipeItems.add(allItems.get(24));

		Recipe recipe3 = new Recipe("Fried Rice", recipeItems, false);

		allRecipes.add(recipe3);
		
		recipeItems = new ArrayList<Item>();

		recipeItems.add(allItems.get(30));
		recipeItems.add(allItems.get(31));
		recipeItems.add(allItems.get(32));
		recipeItems.add(allItems.get(33));
		recipeItems.add(allItems.get(34));

		Recipe recipe4 = new Recipe("Pasta Carbonara", recipeItems, false);

		allRecipes.add(recipe4);
		
		recipeItems = new ArrayList<Item>();

		recipeItems.add(allItems.get(25));
		recipeItems.add(allItems.get(26));
		

		Recipe recipe5 = new Recipe("Orange Cake", recipeItems, false);

		allRecipes.add(recipe5);
		
		firstTime = false;

	}


	// setup the actions the buttons perform on a click
	private void checkButtonClick() {

		// 'Show Items' Button
		Button myButton = (Button) findViewById(R.id.button1);
		myButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				StringBuffer responseText = new StringBuffer();
				responseText.append("The following were selected...\n");

				myItems.removeAll(myItems);

				ArrayList<Item> itemList = dataAdapter.itemList;
				for(int i=0;i<itemList.size();i++){
					Item item = itemList.get(i);
					if(item.isSelected()) {
						myItems.add(item);
					}
				}
				if (myItems.isEmpty()) {
					Toast.makeText(getApplicationContext(),
							"No Items Selected, Please Select Items and Try Again.", 
							Toast.LENGTH_LONG).show();
				}
				else {
					Intent intent = new Intent(MainActivity.this, ItemsActivity.class);
					intent.putParcelableArrayListExtra("selectedItems", myItems);
					MainActivity.this.startActivity(intent);
				}
			}
		});

		// 'Clear All' Button
		Button clearButton = (Button) findViewById(R.id.button3);
		clearButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				//StringBuffer responseText = new StringBuffer();
				//responseText.append("The following were selected...\n");
				//myItems.removeAll(myItems);

				// populate complete ArrayList of Items  (all products) :
				ArrayList<Item> itemList = dataAdapter.itemList;
				// loop through all items, if they are selected, unselect them
				for(int i=0;i<itemList.size();i++){
					Item item = itemList.get(i);
					if(item.isSelected()) {
						item.setSelected(false);
						dataAdapter.clear();
						firstTime = true;
						displayListView();
						dataAdapter.notifyDataSetChanged();
					}
				}
				for (int i = 0; i<allRecipes.size(); i++) {
					Recipe recipe=allRecipes.get(i);
					recipe.setSelected(false);
					allRecipes.set(i, recipe);
				}


			}
		});
		//'Go to Map' button
		Button mapButton = (Button) findViewById(R.id.button2);
		mapButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				//remove all elements of ArrayList myItems
				myItems.removeAll(myItems);
				// now populate the ArrayList myItems based based on if Item is selected
				ArrayList<Item> itemList = dataAdapter.itemList;
				for(int i=0;i<itemList.size();i++){
					Item item = itemList.get(i);
					if(item.isSelected()) {
						myItems.add(item);
					}
				}

				if (myItems.isEmpty()) {
					Toast.makeText(getApplicationContext(),
							"No Items Selected, Please Select Items and Try Again.", 
							Toast.LENGTH_LONG).show();
				}
				else {
					Intent intent = new Intent(MainActivity.this, MapActivity.class);
					intent.putParcelableArrayListExtra("allItems", allItems);
					MainActivity.this.startActivity(intent);
				}
			}
		});

		//shops button
		Button shopsButton = (Button) findViewById(R.id.shopsButton);
		shopsButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
				alert.setTitle("Shops Info");

				alert.setSingleChoiceItems(shops, shopNum, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						//SET SHOP HERE
						shopNum = which; //0 for provigo, 1 for metro
					}
				});
				alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();

					}
				});

				alert.setNegativeButton("CALL", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if(shopNum == 0) {
							Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: 5142810488")); 
							startActivity(callIntent);
						} 
						else if (shopNum == 1) {
							Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: 5148433530")); 
							startActivity(callIntent);
						}

					}
				});
				Linkify.addLinks(shops[0], Linkify.PHONE_NUMBERS | Linkify.WEB_URLS);
				Linkify.addLinks(shops[1], Linkify.PHONE_NUMBERS | Linkify.WEB_URLS);

				alert.show();

			}
		});
		
		//recipe button
		Button recipeButton = (Button) findViewById(R.id.recipeButton);
		recipeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(MainActivity.this,
						RecipeActivity.class);
				intent.putParcelableArrayListExtra("allItems", allItems);
				intent.putParcelableArrayListExtra("allRecipes", allRecipes);
				MainActivity.this.startActivity(intent);
			}

		});


	}



	@Override
	/**
	 * This function inflates the menu - it adds items to the action bar if it is present.
	 * @return true if the menu was successfully inflated
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}


}