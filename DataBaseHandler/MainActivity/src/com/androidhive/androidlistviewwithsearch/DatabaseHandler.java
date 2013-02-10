//Code inspired from http://www.androidhive.info/2011/11/android-sqlite-database-tutorial/

package com.androidhive.androidlistviewwithsearch;


import java.util.ArrayList;
import java.util.List;
import com.androidhive.androidlistviewwithsearch.Item;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHandler extends SQLiteOpenHelper {
	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "itemManager";

	// Item table name
	private static final String TABLE_ITEMS = "item";

	// Items Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "itemName";
	private static final String KEY_CATG = "itemCategory";
	private static final String KEY_LCT = "itemLocation";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_ITEMS_TABLE = "CREATE TABLE " + TABLE_ITEMS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT NOT NULL UNIQUE,"
				+ KEY_CATG + " TEXT," + KEY_LCT + " TEXT"+ ")";
		db.execSQL(CREATE_ITEMS_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);

		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new item
	void addItem(Item item) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, item.getItemName()); // Item Name
		values.put(KEY_CATG, item.getItemCategory()); // Item Category
		values.put(KEY_LCT, item.getItemLocation()); // Item Location

		// Inserting Row
		db.insert(TABLE_ITEMS, null, values);
		db.close(); // Closing database connection
	}

	// Getting single item
	Item getItem(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_ITEMS, new String[] { KEY_ID,
				KEY_NAME, KEY_CATG,KEY_LCT }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Item item = new Item(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2), cursor.getString(3));
		// return item
		return item;
	}
	
	public void fillDatabase(String products[]){
		for (int i=0 ; i<products.length; i++){
			Item item = new Item(products[i],null,null);
			this.addItem(item);
		}
	}
	
	
	// Getting All Items
	public List<Item> getAllItems() {
		List<Item> itemList = new ArrayList<Item>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_ITEMS;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Item item = new Item();
				item.setItemId(Integer.parseInt(cursor.getString(0)));
				item.setItemName(cursor.getString(1));
				item.setItemCategory(cursor.getString(2));
				item.setItemLocation(cursor.getString(3));
				
				// Adding item to list
				itemList.add(item);
			} while (cursor.moveToNext());
		}

		// return item list
		return itemList;
	}

	// Updating single item
	public int updateItem(Item item) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, item.getItemName());
		values.put(KEY_CATG, item.getItemCategory());
		values.put(KEY_LCT, item.getItemLocation());

		// updating row
		return db.update(TABLE_ITEMS, values, KEY_ID + " = ?",
				new String[] { String.valueOf(item.getItemId()) });
	}

	// Deleting single item
	public void deleteItem(Item item) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_ITEMS, KEY_ID + " = ?",
				new String[] { String.valueOf(item.getItemId()) });
		db.close();
	}


	// Getting items Count
	public int getItemsCount() {
		String countQuery = "SELECT  * FROM " + TABLE_ITEMS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}
}
