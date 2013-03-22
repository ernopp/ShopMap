package com.example.shop_map;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.ParcelFormatException;
import android.os.Parcelable;

public class Recipe implements Parcelable {

	String name;
	ArrayList<Item> items;
	boolean selected;

	public Recipe(Parcel input) {
		readFromParcel(input);
	}

	public Recipe(String name, ArrayList<Item> items, boolean selected) {
		super();
		this.name = name;
		this.items = items;
		this.selected = selected;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeTypedList(items);
		dest.writeByte((byte) (selected ? 1 : 0));
	}

	@SuppressWarnings("unchecked")
	private void readFromParcel(Parcel input) throws ParcelFormatException {
		this.name = input.readString();
		items = new ArrayList<Item>();
		input.readTypedList(items, Item.CREATOR);
		this.selected=input.readByte()==1;
		
//		Parcelable [] parcelableArray = input.readTypedList(Item.class.getClassLoader());
//		ArrayList<Item> resultArray = null;
//		
//		if (parcelableArray!=null) {
//			resultArray = new Item[parcelableArray.length];
//			for (int i =0; i<parcelableArray.length; i++) 
//				resultArray[i]=(Item)parcelableArray[i];
//			this.items=resultArray;
//		}
//		else
//			throw new ParcelFormatException("Parceling the recipe object failed.");
//			
	}

	public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {

		public Recipe createFromParcel(Parcel in) {
			return new Recipe(in);
		}

		@Override
		public Recipe[] newArray(int size) {
			return new Recipe[size];
		}
	};

	protected String getName() {
		return name;
	}

	protected ArrayList<Item> getItems() {
		return items;
	}
	
	protected boolean isSelected() {
		return selected;
	}

	protected void setName(String name) {
		this.name = name;

	}

	protected void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	
	protected void setSelected(boolean selected) {
		this.selected = selected;
	}

}
