package com.example.shop_map;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;

public class MyTabsListener implements ActionBar.TabListener {
	private Fragment fragment;
	
	public MyTabsListener(Fragment fragment) {
		this.fragment = fragment;
	}
	
	@Override
	 public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
		int k = tab.getPosition();
		
		switch (k) {
			case 0: ft.replace(R.id.fragment_A, fragment);
			case 1: ft.replace(R.id.fragment_A, fragment);
		}
     }
	
	@Override
     public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) { 
		//if(fragment != null) {
			ft.remove(fragment);
		//}
	}

	@Override
     public void onTabReselected(ActionBar.Tab tab,
             FragmentTransaction ft) { }
}
