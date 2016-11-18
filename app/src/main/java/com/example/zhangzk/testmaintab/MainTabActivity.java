package com.example.zhangzk.testmaintab;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.example.zhangzk.testmaintab.base.BaseActivity;
import com.example.zhangzk.testmaintab.base.BaseFragment;
import com.example.zhangzk.testmaintab.fragment.IndexFragment;
import com.example.zhangzk.testmaintab.util.AndroidUtil;
import com.example.zhangzk.testmaintab.util.CommunityLog;
import com.example.zhangzk.testmaintab.view.TabLayout;
import com.example.zhangzk.testmaintab.view.TabView;


/**
 * 主界面，垂直方向两大区域：fragment和tab。
 * 要控制这个文件的代码量，业务逻辑尽量封装成独立模块/文件
 * 。每一个功能往这里添加代码要考虑三部分：初始化，响应，反初始化(如监听，还没结束的后台操作)，可根据实际选择实现。
 */
public class MainTabActivity extends BaseActivity {

    public static final String TAG = MainTabActivity.class.getSimpleName();

    /** tab布局 */
    private TabLayout mTabLy;

    /** fragment容器id */
    private int mContainerViewId = R.id.main_activity_content_ly;

    /** 要进入的tab */
    public static final String BUNDLE_KEY_TAB_TAG = "tab_tag";
    public static final String BUNDLE_KEY_EXTRA_START_FOR_EXIT = "exit";

    /** 生成对应的fragment */
    private MainTabFragmentBuilder mFragmentBuilder;
    
    private BaseFragment mCurrentFragment;

    public static boolean IS_RESUMED = false;
    
    /** 记录上次点击返回键的时间，用于控制退出操作 */
    private long mLastOnKeyBackTime = 0;
//    private DefaultAlertDialog mConfirmUpdateDialog;

    /**
     * 启动这个activity并定位到指定的tab
     * @param starter caller activity
     * @param tabTag 哪个tab
     * @param extras 额外参数，如果有的话。fragment里面，使用{@link BaseFragment#getExtras()}
     */
    public static void startMainActivity(Activity starter, String tabTag, Bundle extras) {
        if (starter == null || tabTag == null) {
            return;
        }
        if (extras == null) {
            extras = new Bundle();
        }
        extras.putString(BUNDLE_KEY_TAB_TAG, tabTag);
        Intent intent = new Intent(starter, MainTabActivity.class);
        intent.putExtras(extras);
        starter.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_main);

        // 初始化底部栏
        initTab();
        

        // go to fragment
        firstEnterFragment(getIntent());

//        mConfirmUpdateDialog = new DefaultAlertDialog(this);
//        if(VersionUpdateManager.getInstance(this).isForceToUpdate()){
//            showUpdateForceDialog(VersionUpdateManager.getInstance(this).getmVersionInfo().appLink);
//            return;
//        }
//
//        if(VersionUpdateManager.getInstance(this).isHasNewVersion()){
//            showUpdateDialog(VersionUpdateManager.getInstance(this).getmVersionInfo().appLink);
//        }
    }

//    /**
//     * 强制更新Dilaog
//     * @param appLink
//     */
//    private void showUpdateForceDialog(final String appLink){
//        mConfirmUpdateDialog.setTitle(getString(R.string.user_setting_force_update));
//        mConfirmUpdateDialog.setContent(VersionUpdateManager.getInstance(this).getmVersionInfo().description + "");
//        mConfirmUpdateDialog.setRightBtnTextColor(getResources().getColor(R.color.global_bg_color_green_more));
//        mConfirmUpdateDialog.setRightBtnStr(getString(R.string.update));
//        mConfirmUpdateDialog.setCanceledOnTouchOutside(false);
//        mConfirmUpdateDialog.setRightBtnListener(new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//            	dialog.dismiss();
//                if (AndroidUtil.isSDCardExist()) {
//                    //UserNotificationUpdateActivity.startUserVersionUpdateActivity(mActivity, appLink);
//                } else {
//                    CommonToast.showToast(mActivity, getString(R.string.common_sdcard_not_exist));
//                }
//            }
//        });
//        mConfirmUpdateDialog.setLeftBtnListener(new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                exit();
//            }
//        });
//        mConfirmUpdateDialog.setOnKeyListener(new OnKeyListener() {
//
//			@Override
//			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
//				return true;
//			}
//		});
//        mConfirmUpdateDialog.show();
//    }
//
//    /**
//     * 有新版本
//     * @param appLink
//     */
//    private void showUpdateDialog(final String appLink){
//        mConfirmUpdateDialog.setTitle(getString(R.string.user_setting_about_version));
//        mConfirmUpdateDialog.setContent(VersionUpdateManager.getInstance(this).getmVersionInfo().description+"");
//        mConfirmUpdateDialog.setRightBtnTextColor(getResources().getColor(R.color.global_bg_color_green_more));
//        mConfirmUpdateDialog.setRightBtnStr(getString(R.string.update));
//        mConfirmUpdateDialog.setCanceledOnTouchOutside(true);
//        mConfirmUpdateDialog.setRightBtnListener(new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//            	dialog.dismiss();
//                if (AndroidUtil.isSDCardExist()) {
//                	  //UserNotificationUpdateActivity.startUserVersionUpdateActivity(mActivity, appLink); // TODO
//                } else {
//                    CommonToast.showToast(mActivity, getString(R.string.common_sdcard_not_exist));
//                }
//            }
//        });
//        mConfirmUpdateDialog.setLeftBtnListener(new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                mConfirmUpdateDialog.dismiss();
//            }
//        });
//        mConfirmUpdateDialog.show();
//    }

    @Override
    protected void onResume() {
        super.onResume();

        IS_RESUMED = true;
    }

    @Override
    protected void onPause() {
        super.onPause();

        IS_RESUMED = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mFragmentBuilder.clearCache();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        this.handleNewIntent(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) { 
        CommunityLog.i(TAG, "onKeyDown");
        //TODO
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){
        	doBack();
			return false;
		}
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 初始化bottom tab view
     */
    private void initTab() {
        mFragmentBuilder = new MainTabFragmentBuilder();
        mTabLy = (TabLayout) findViewById(R.id.main_activity_tab_ly);
        mTabLy.setOnTabChangeListener(new TabLayout.OnTabChangeListener() {
            @Override
            public void onTabChange(TabView view) {
                switchFragment(view.getTabTag(), null);
            }
        });
        mTabLy.setOnClickListener(new TabLayout.OnTabClickListener() {
            @Override
            public void onTabClick(TabView view, boolean isChange) {
                //TODO
            	MainTabClickManager.getInstance().onTabClick(view, isChange);
            }
        });

        final int tabSize = MainTabSpec.sAllTabs.length;
        final int eachWidth = AndroidUtil.getScreenWidth() / tabSize;
        for (int tabCount = 0; tabCount < tabSize; tabCount++) {
            MainTabSpec.Tab tab = MainTabSpec.sAllTabs[tabCount];
            // tab view
            TabView oneTab = new TabView(this);
            oneTab.setTabTag(tab.getTag()).setText(getApplicationContext().getResources().getString(tab.getText())).setIconRes(tab.getIcon());
            mTabLy.addTabView(oneTab, eachWidth);
        }
        
        //TODO
        switchFragment(MainTabSpec.TAG_INDEX, null);
        
        setTabTipVisibility(MainTabSpec.TAG_USER, View.VISIBLE);			
        //TODO
    }

    /**
     * 默认打开的fragment
     * @param intent
     */
    private void firstEnterFragment(Intent intent) {
        handleIntent(intent);
    }


    private void handleNewIntent(final Intent in) {
        if (null != in) {
            final boolean exit = in.getBooleanExtra(BUNDLE_KEY_EXTRA_START_FOR_EXIT, false);
            if (exit) {
                exit();
                return;
            }
            handleIntent(in);
        }
    }

    private void handleIntent(Intent intent) {
        if (intent == null || intent.getExtras() == null) {
            return;
        } 

        // 提取传进来的参数
        Bundle fromExtras = intent.getExtras();
        String tag = fromExtras.getString(BUNDLE_KEY_TAB_TAG);

        if (tag==null) {
            return;
        }

        // 封装要传出去的参数 //TODO
        Bundle toExtras = null;

        switchFragment(tag, toExtras);
    }

    /**
     * 用户点击返回键退出的处理
     */
    private void doBack() {
        // child处理
        mTabLy.getCurrentTag();
        if (mCurrentFragment != null && mCurrentFragment.onBackPressed()) {
            return;
        }
        doExit();
    }
    

    /**
     * 处理用户点击返回键操作
     */
    private void doExit() {

        // 双击判断的时间门限
        final long THRESHOLD = 2 * 1000;

        // 获取时间差
        long curOnKeyBackTime = System.currentTimeMillis();
        long diffTime = curOnKeyBackTime - mLastOnKeyBackTime;

        // 更新点击时间
        mLastOnKeyBackTime = curOnKeyBackTime;

        // 如果两次点击返回键的时间大于阈值，则弹出提示toast
        if (diffTime > THRESHOLD) {
            Toast.makeText(mActivity, "再按一次退出", Toast.LENGTH_LONG).show();
        } else {// 否则走退出流程
            exit();
        }
    }


    /**
     * 跳到某个fragment
     * @param tabTag
     *
     */
    public void switchToFragment(String tabTag) {
        switchToFragment(tabTag, false);
    }

    /**
     * 跳转到指定fragment
     * @param tabTag fragment的标签
     * @param hasAnimation 是否有切换动画（目前默认的动画是淡入淡出）
     */
    public void switchToFragment(String tabTag, boolean hasAnimation) {
        switchFragment(tabTag, null);
    }

    private void switchFragment(String fragmentTag, Bundle extras) {
        BaseFragment newFragment = mTabLy.switchFragment(mContainerViewId, mCurrentFragment, getSupportFragmentManager(),
                mFragmentBuilder, fragmentTag, extras);
        if (newFragment!=null) {
            mCurrentFragment = newFragment;
            mTabLy.setSelection(fragmentTag);
        }
//        MobclickAgent.onEvent(getApplicationContext(), CommunityConstant.MAIN_TAB_CLICK, fragmentTag);
    }
    
    // 设置消息提醒是否可见
    private  void setTabTipVisibility(String tabTag, int visibility){
    	TabView tabView = mTabLy.getTabByTag(tabTag);
    	if(tabView == null){
    		return;
    	}
    	tabView.setTipVisibility(visibility);
    }

    /**
     * 当前是否是首页fragment
     * @return
     *
     */
    public boolean checkIsIndexFragment(){
        if (mCurrentFragment instanceof IndexFragment) {
            return true;
        }
        return false;
    }

}
