package com.example.zhangzk.testmaintab.base;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhangzk.testmaintab.R;
import com.example.zhangzk.testmaintab.application.InitApplication;
import com.example.zhangzk.testmaintab.util.CommunityLog;


public class BaseFragment extends Fragment {
	
	public final String TAG = BaseFragment.this.getClass().getSimpleName();

	//设置页面的View根节点
	protected ViewGroup mPageRoot;
	protected FragmentActivity mActivity;

	protected TextView mDefaultText;

	/** fragment需要的数据，可以在实例化的时候用 {@link BaseFragment#setExtras(Bundle)}。自带的{@link Fragment#setArguments()只能调用一次，所以不用它}  */
	@SuppressWarnings("JavadocReference")
    private Bundle extras = null;

	@Override
	public void onAttach(Activity activity) {
		CommunityLog.i(TAG, "onAttach. to " + activity.getClass().getSimpleName());
		mActivity = (FragmentActivity) activity;
		getArguments();
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		CommunityLog.i(TAG, "onCreateView.");
		if(null != mPageRoot){
			return mPageRoot;
		} else {
			View v = inflater.inflate(R.layout.default_fragment_view, container, false);
			mDefaultText = (TextView) v.findViewById(R.id.default_text);
			return v;
		}
	}

	@Override
	public void onDetach() {
		CommunityLog.i(TAG, "onDetach.");
		super.onDetach();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		CommunityLog.i(TAG, "onCreate.");
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onResume() {
		CommunityLog.i(TAG, "onResume.");
    	super.onResume();
//    	MobclickAgent.onPageStart(CommunityConstant.MAIN_FRAGMENT_STATE);
    }
    
	@Override
	public void onPause() {
		CommunityLog.i(TAG, "onPause.");
		super.onPause();
//		MobclickAgent.onPageEnd(CommunityConstant.MAIN_FRAGMENT_STATE);
	}
	
	@Override
	public void onStart() {
		CommunityLog.i(TAG, "onStart.");
		super.onStart();
	}

	@Override
	public void onDestroyView() {
		CommunityLog.i(TAG, "onDestroyView.");
		super.onDestroyView();
	}

	@Override
	public void onDestroy() {
		CommunityLog.i(TAG, "onDestroy.");
		super.onDestroy();
	}

	/**
	 * 从当前Fragment页面查找这个View。
	 * 子Fragment需要在onCreateView中，把inflate的View赋给mPageRoot。
	 * @param id
	 * @return
	 */
	protected View findViewById(int id) {
		View v = null;
		if(null != mPageRoot) {
			v = mPageRoot.findViewById(id);
		}
		return v;
	}


	/**
	 * 启动一个新的Activity，带动画的。
	 * @param activityClass Activity的类对象
	 * @param animation 是否开启动画
	 */
	protected void startActivityWithAnimation(Class<?> activityClass, boolean animation) {
		Intent intent = new Intent(mActivity, activityClass);
		startActivity(intent);
		if(animation) {
		}
	}

	/**
	 * 启动一个新的Activity，带动画的。
	 * @param activityClass Activity的类对象
	 * @param animation 是否开启动画
	 */
	protected void startActivityWithAnimation(Class<?> activityClass, boolean animation, Intent intent) {
		intent.setClassName(mActivity, activityClass.getName());
		startActivity(intent);
		if(animation) {
		}
	}

	/**
	 * 启动一个新的Activity，不带动画的。
	 * 调用startActivityWithAnimation(activityClass, false)实现。
	 * @param activityClass Activity的类对象
	 */
	protected void startActivityWithAnimation(Class<?> activityClass) {
		startActivityWithAnimation(activityClass, true);
	}

	/**
	 * 用于直接传带有自有数据的intent
	 * @param in
	 */
	protected void startActivityWithAnimation(final Intent in) {
		if (null != in) {
			startActivityWithAnimation(in, true);
		}
	}

	/**
	 * 用于直接传带有自有数据的intent
	 * @param in
	 * @param anim
	 */
	protected void startActivityWithAnimation(final Intent in, final boolean anim) {
		this.startActivity(in);
		if (anim) {

		}
	}

	protected void startActivityWithExtraAnimation(Class<?> activityClass, int enterAnim, int exitAnim) {
		Intent intent = new Intent(mActivity, activityClass);
		startActivity(intent);
		mActivity.overridePendingTransition(enterAnim, exitAnim);
	}

	/**
	 * 当Activity接收到MenuPressed事件时,首先会调用当前显示的Fragment的onMenuPressed()方法,Fragment可以截获该事件。
	 * 如果Fragment截获了该事件，则返回true；
	 * 如果返回false，Activity将采用默认的处理方式。
	 * @return
	 */
	public boolean onMenuPressed() {
		return false;
	}

	public void onCreateTask(boolean create, int reportType) {
	}

	public String getFragmentTAG() {
		return TAG;
	}

	/**
	 * 获取string，替代Fragment的是getString()方法。不要直接使用Fragment的是getString，否则可能报异常：Fragment is not attached to activity.
	 * @param id
	 * @return
	 */
	public String getResouceString(int id) {
		return InitApplication.getInstance().getString(id);
	}

	public String getResouceString(int resId, Object... formatArgs) {
		return InitApplication.getInstance().getString(resId, formatArgs);
	}

	/**
	 * 同getResouceString()
	 * @param resId
	 * @return
	 */
	public Drawable getResouceDrawable(int resId) {
	    return InitApplication.getInstance().getResources().getDrawable(resId);
	}
	
	/**
	 * 同getResouceString()
	 * @param resId
	 * @return
	 */
	public int getResouceColor(int resId) {
	    return InitApplication.getInstance().getResources().getColor(resId);
	}

	/**
	 * return application context
	 * @return
	 */
	protected Context getApplicationContext() {
		return InitApplication.getInstance().getApplicationContext();
	}

	/**
	 * @return true 父控件需要考虑中断，否则不中断
	 */
	public boolean isIntercept(){
	    return true;
	}

	/**
	 * @return ture ,当 mEasyTouchView 不为null 并且 显示时
	 */
	public boolean onBackPressed(){
	    return false;
	}

    /**
     * 获取extras数据
     * @return
     *
     */
    public Bundle getExtras() {
        return extras;
    }

    /**
     * 设置extras数据
     * @param extras
     *
     */
    public void setExtras(Bundle extras) {
        this.extras = extras;
    }
}
