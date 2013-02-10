package com.androidhive.androidlistviewwithsearch;

public class Item {
	//private variables
	int id;
	String itemName;
	String itemCategory;
	String itemLocation;
	
	// constructor
	public Item (){
		
	}
	// constructor
	public Item(int id, String itemName, String itemCategory, String itemLocation){
		this.id = id;
		this.itemName = itemName;
		this.itemCategory = itemCategory;
		this.itemLocation = itemLocation;
	}
	
	// constructor
	public Item (String name, String itemCategory,String itemLocation ){
		this.itemName = name;
		this.itemCategory = itemCategory;
		this.itemLocation= itemLocation;
	}
	// getting ID
	public int getItemId(){
		return this.id;
	}
	
	//setting ID
	public void setItemId(int id){
		this.id=id;
	}

	
	// getting name
	public String getItemName(){
		return this.itemName;
	}
	
	// setting name
	public void setItemName(String name){
		this.itemName = name;
	}
	
	// getting Item Category
	public String getItemCategory(){
		return this.itemCategory;
	}
	
	// setting item Category
	public void setItemCategory(String category){
		this.itemCategory = category;
	}
	
	// getting Item Location
	public String getItemLocation(){
		return this.itemCategory;
	}
		
	// setting item Location
	public void setItemLocation(String category){
		this.itemCategory = category;
	}
		
}
