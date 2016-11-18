package com.example.zhangzk.testmaintab.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class BaseFragmentActivity extends FragmentActivity {
	
	protected FragmentManager mFragmentManager;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		mFragmentManager = getSupportFragmentManager();
	}
	
	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}	
}
