package com.example.zhangzk.testmaintab.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.zhangzk.testmaintab.R;
import com.example.zhangzk.testmaintab.base.BaseFragment;
import com.example.zhangzk.testmaintab.util.CommunityLog;


/**
 * 我的页面
 * @author oliver
 *
 */
public class UserFragment extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		CommunityLog.v(TAG, "onCreateView");
		if (mPageRoot == null) {
			mPageRoot = (ViewGroup) inflater.inflate(R.layout.frame_fragment_user, container, false);
		}

		ViewParent parent = mPageRoot.getParent();
		if (parent != null && parent instanceof ViewGroup) {
			((ViewGroup) parent).removeView(mPageRoot);
		}

		return mPageRoot;
	}
}
