package com.example.zhangzk.testmaintab.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhangzk.testmaintab.application.InitApplication;
import com.example.zhangzk.testmaintab.util.AndroidUtil;
import com.example.zhangzk.testmaintab.util.CommunityLog;


public class BaseActivity extends BaseFragmentActivity {
    public Activity mActivity;
    private View rootView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CommunityLog.i("CommunityActivityLife", "onCreate:" + this + "=" + getTaskId());
        mActivity = this;
        
        InitApplication.getInstance().onActivityCreate(this, savedInstanceState);
    }

    
    /**
     * 点击rootView的时候获取焦点，关闭输入法等等操作
     */
    public void autoRequestFocus() {
        rootView = ((ViewGroup) (findViewById(android.R.id.content))).getChildAt(0);
        rootView.setFocusable(true);
        rootView.setFocusableInTouchMode(true);
        rootView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideInputByAlways();
                return false;
            }
        });
    }

    // 关闭输入法
    public void hideInputByAlways() {
        AndroidUtil.hiddenInput(BaseActivity.this, rootView);
        rootView.requestFocus();
    }

    @Override
    protected void onResume() {
    	super.onResume();
//    	MobclickAgent.onPageStart(CommunityConstant.EXTEND_ACTIVITY_STATE);
//		MobclickAgent.onResume(getApplicationContext());
		
//		// 检查推送服务是否停止,停止则打开服务
//		boolean isPushTurnedOn = PushManager.getInstance().isPushTurnedOn(getApplicationContext());
//		CommunityLog.i("GeTuiPushReceiver", "isPushTurnedOn="+isPushTurnedOn);
//		if(!isPushTurnedOn){
//			PushManager.getInstance().turnOnPush(getApplicationContext());
//		}
    }
    
	@Override
	protected void onPause() {
		super.onPause();
//		MobclickAgent.onPageEnd(CommunityConstant.EXTEND_ACTIVITY_STATE);
//		MobclickAgent.onPause(getApplicationContext());
	}

    @Override
    protected void onDestroy() {
        CommunityLog.i("CommunityActivityLife", "onDestroy:" + this);
        InitApplication.getInstance().onActivityDestroy(this);
        super.onDestroy();
    }

    /**
     * 退出程序
     */
    public void exit() {
    	// 保存统计数据
//    	MobclickAgent.onKillProcess(this);
//    	NotificationManagerWrapper.getInstance(this).cancelNotification(CommunityConstant.REDPOINT_NOTIFY_ID); //关闭通知
        InitApplication.getInstance().killAppliation();
    }
}
