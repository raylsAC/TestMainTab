package com.example.zhangzk.testmaintab;

import com.example.zhangzk.testmaintab.view.TabLayout;
import com.example.zhangzk.testmaintab.view.TabView;

import java.util.ArrayList;
import java.util.List;


/**
 * tab点击事件管理类
 * */
public class MainTabClickManager {

	private List<TabLayout.OnTabClickListener> mOnTabClickListeners = new ArrayList<TabLayout.OnTabClickListener>();
	
	private static MainTabClickManager mTabClickManager = null;
	
	private MainTabClickManager(){
		
	}
	
	public static MainTabClickManager getInstance(){
		if(mTabClickManager == null){
			mTabClickManager = new MainTabClickManager();
		}
		return mTabClickManager;
	}
	
	// 添加点击监听
	public void addTabClickListener(TabLayout.OnTabClickListener onTabClickListener){
		if(onTabClickListener != null && !mOnTabClickListeners.contains(onTabClickListener)){
			mOnTabClickListeners.add(onTabClickListener);
		}
	}
	
	// 移除点击监听
	public void removeTabClickListener(TabLayout.OnTabClickListener onTabClickListener){
		if(mOnTabClickListeners.contains(onTabClickListener)){
			mOnTabClickListeners.remove(onTabClickListener);
		}
	}
	
	// 响应点击事件
	public void onTabClick(TabView view, boolean isChange){
		for (int i = 0; i < mOnTabClickListeners.size(); i++) {
			mOnTabClickListeners.get(i).onTabClick(view, isChange);
		}
	}
}
