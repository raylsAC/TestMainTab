package com.example.zhangzk.testmaintab.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.example.zhangzk.testmaintab.MainTabSpec;
import com.example.zhangzk.testmaintab.TabFragmentBuilder;
import com.example.zhangzk.testmaintab.base.BaseFragment;


/**
 * 底部tab，先{@link #addTabView(TabView, int)}，然后设置tab切换回调{@link #setOnTabChangeListener(OnTabChangeListener)}。
 *
 * 可以理解为底部tab的容器
 */
public class TabLayout extends LinearLayout implements View.OnClickListener {

    /** tab切换回调 */
    private OnTabChangeListener mOnTabChangeListener;

    private OnTabClickListener mOnTabClickListener;

    /** 当前tab的tag */
    private String mCurrentTabTag;

    public TabLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public TabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TabLayout(Context context) {
        super(context);
    }

    /**
     * 点击监听，有需要才监听
     */
    public interface OnTabClickListener {
        /**
         * 每次点击都回调
         */
        public void onTabClick(TabView view, boolean isChange);
    }

    /**
     * 切换监听，有需要才监听
     */
    public interface OnTabChangeListener {
        /**
         * tab发生变化，切换。重复点击当前tab不会触发
         *
         * @param view
         */
        public void onTabChange(TabView view);
    }

    /**
     * tab变化回调
     *
     * @param listener tab变化监听
     */
    public void setOnTabChangeListener(OnTabChangeListener listener) {
        mOnTabChangeListener = listener;
    }

    /**
     * 点击
     * @param listener
     *
     */
    public void setOnClickListener (OnTabClickListener listener) {
        mOnTabClickListener = listener;
    }

    /**
     * 加入一个tabview到layout中，并设置布局参数。
     *
     * @param tab
     * @param width 这个tab的精准宽度值，像素。常见情况是:屏幕宽/个数。
     */
    public void addTabView(TabView tab, int width) {
        LayoutParams param = new LayoutParams(
                width, LayoutParams.MATCH_PARENT);
        tab.setOnClickListener(this);
        this.addView(tab, param);
    }

    /**
     * 设置tab的选中态
     *
     * @param tag
     */
    public void setSelection(String tag) {
        for (int count = 0; count < this.getChildCount(); count++) {
            TabView tabView = (TabView) getChildAt(count);
            if (tag.equals(tabView.getTabTag())) {
            	tabView.setSelection(true);
                mCurrentTabTag = tag;
            } else {
                tabView.setSelection(false);
            }
        }
    }

    /**
     * 当前tab的tag
     *
     * @return
     */
    public String getCurrentTag() {
        return mCurrentTabTag;
    }

    /**
     * 得到tag对应的tabview
     *
     * @param tag
     * @return
     */
    public TabView getTabByTag(String tag) {
        final int tabSize = this.getChildCount();
        for (int count = 0; count < tabSize; count++) {
            TabView tabView = (TabView) getChildAt(count);
            if (tag.equals(tabView.getTabTag())) {
                return tabView;
            }
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        TabView thisTab = (TabView) v;
        
        // TODO
        if(MainTabSpec.TAG_HOME.equals(thisTab.getTabTag())){
    		return;
    	}

        boolean isChange = !mCurrentTabTag.equals(thisTab.getTabTag());
        //onclick
        if (mOnTabClickListener!=null) {
            mOnTabClickListener.onTabClick(thisTab, isChange);
        }

        if (!isChange) {
            return;
        }
        setSelection(thisTab.getTabTag());

        //onchange
        if (mOnTabChangeListener != null) {
            mOnTabChangeListener.onTabChange(thisTab);
        }
    }

    /**
     * 切换到指定的fragment
     *
     * @param containerViewId 容器view的id
     * @param frgManager see
     * @param fragmentBuilder
     * @param fragmentTag fragment对应的tag
     * @param extras 需要带给fragment的数据，没有就是null，数据保存在{@link BaseFragment#setArguments(Bundle)}
     * @return
     */
    public BaseFragment switchFragment(int containerViewId, BaseFragment fromFragment, FragmentManager frgManager,
                                       TabFragmentBuilder fragmentBuilder, String fragmentTag, Bundle extras) {
        if (containerViewId<=0 || frgManager==null || fragmentBuilder==null || fragmentTag==null) {
            return null;
        }

        BaseFragment toFragment = fragmentBuilder.build(fragmentTag, extras);

        if (toFragment == null) {
            return null;
        }
        FragmentTransaction ft = frgManager.beginTransaction();
        
        if (!toFragment.isAdded()) {   
        	ft.add(containerViewId, toFragment);
        } 
        if(fromFragment != null){
        	ft.hide(fromFragment).show(toFragment);
        }

        ft.commitAllowingStateLoss();
        return toFragment;
    }
}
