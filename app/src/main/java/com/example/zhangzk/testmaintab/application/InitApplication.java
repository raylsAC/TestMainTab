package com.example.zhangzk.testmaintab.application;

import java.lang.ref.WeakReference;
import java.util.Stack;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;

import com.example.zhangzk.testmaintab.util.AndroidUtil;
import com.example.zhangzk.testmaintab.util.CommunityLog;


public class InitApplication extends Application{
	
	private static String TAG = InitApplication.class.getSimpleName();

    private static InitApplication mInitApplication;
    private boolean isDownload;

    // 当前的 ActivityStack, 靠每个activity调用 onActivityCreate 和 onActivityDestroy维护这个信息
    private Stack<WeakReference<Activity>> mActivityStack = new Stack<WeakReference<Activity>>();

    

    @Override
    public void onCreate() {
        super.onCreate();
        String processName = AndroidUtil.getProcessName(getApplicationContext());
        String packageName = getPackageName();
        if(!TextUtils.isEmpty(processName) && processName.equalsIgnoreCase(packageName)){
        	mInitApplication = this;
        	AndroidUtil.init(getApplicationContext()); // 系统参数获取
//        	initOpenNetLocation(getApplicationContext()); // 定位城市
        }
    }
	
    public boolean isDownload() {
  		return isDownload;
  	}

  	public void setDownload(boolean isDownload) {
  		this.isDownload = isDownload;
  	}
	@Override
	public void onTerminate() {
		super.onTerminate();
	}

	public static InitApplication getInstance(){
		return mInitApplication;
	}
	
	/**
	 * 每个activity 的 onCreate 里调用这个函数
	 *
	 * @param activity
	 * @param savedInstanceState 这个参数没什么用，只是为了更好保证onActivityDestroy是在onCreate里调用的
	 */
	public void onActivityCreate(Activity activity, Bundle savedInstanceState) {
		mActivityStack.push(new WeakReference<Activity>(activity));
	}

	/**
	 * 每个activity的 onDestory函数里调用这个函数
	 *
	 * @param activity
	 */
	public void onActivityDestroy(Activity activity) {
		if (mActivityStack.empty()) {
			return;
		}

		if (mActivityStack.peek().get() != activity) {
			CommunityLog.e(TAG, "onActivityDestory not match");
			return;
		}

		mActivityStack.pop();
	}
	
	/**
	 * finish stack中的所有activity
	 */
	public void finishActivityStack() {
		while (!mActivityStack.empty()) {
			WeakReference<Activity> activity = mActivityStack.pop();
			if (activity.get() != null && !activity.get().isFinishing()) {
				activity.get().finish();
			}
		}
	}

    /**
     * 退出应用
     */
	public void killAppliation() {
		finishActivityStack();
        Process.killProcess(Process.myPid());
	}

}
