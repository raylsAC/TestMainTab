

package com.example.zhangzk.testmaintab.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhangzk.testmaintab.R;

/**
 * 一个tabview(底部一个按钮)，实例化之后要设置tag, 图标，标题
 * <p>
 */
public class TabView extends LinearLayout {

    /** tag  */
    private String mTabTag;

    /** 图标资源  */
    private int mIconRes;
    /** 标题  */
    private String mText;

    /** 最外层布局  */
    private View mTabViewLy;
    /**   */
    private ImageView mTabIconIv;
    
    /**   */
    private TextView mTabTv;
    /** 新消息标志  */
    private View mNewContentIconV;
    
    /** 新消息数字  */
//    private TextView mNewContentNumTv;
    
    private boolean mCanselect = true;

    public TabView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    public TabView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabView(Context context) {
        super(context);
        initView(context);
    }

    /**
     * 获取它的tag
     * @return
     *
     */
    public String getTabTag() {
        return mTabTag;
    }

    /**
     * 设置tag
     * @param tabTag
     * @return
     *
     */
    public TabView setTabTag(String tabTag) {
        this.mTabTag = tabTag;
        return this;
    }

    /**
     * 获取icon资源
     * @return
     *
     */
    public int getIconRes() {
        return mIconRes;
    }

    /**
     * 设置icon资源
     * @param icon
     * @return
     *
     */
    public TabView setIconRes(int icon) {
        this.mIconRes = icon;
        mTabIconIv.setImageResource(icon);
        return this;
    }

    /**
     * 获取标题资源
     * @return
     *
     */
    public String getText() {
        return mText;
    }

    /**
     * 设置标题资源
     * @param text
     * @return
     *
     */
    public TabView setText(String text) {
        this.mText = text;
        mTabTv.setText(text);
        return this;
    }

    private void initView(Context context) {
        mTabViewLy = LayoutInflater.from(context).inflate(R.layout.main_tab_item, this);
        mTabIconIv = (ImageView) mTabViewLy.findViewById(R.id.main_tab_img);
        mTabTv = (TextView) mTabViewLy.findViewById(R.id.main_tab_txt);
        mNewContentIconV = mTabViewLy.findViewById(R.id.main_tab_tip);
//        newContentIconV.setVisibility(View.GONE);
//        newContentNumTv = (TextView) tabViewLy.findViewById(R.id.main_tab_new_num);
//        newContentNumTv.setVisibility(View.GONE);
    }

    /**
     * 设置按钮选中态
     * @param selected
     */
    public void setSelection(boolean selected) {
    	if(mCanselect){
    		mTabIconIv.setSelected(selected);
    		mTabTv.setSelected(selected);
    	}
    }
    
    /**
     * 设置底部按钮默认选中
     */
    public void setTabDefaultSelect(){
    	 mCanselect = false;
    	 mTabViewLy.setSelected(true);
    	 mTabTv.setTextColor(Color.WHITE);
    }
    
    /**
     * 设置新消息提醒显示状态
     */
    public void setTipVisibility(int visibility) {
    	mNewContentIconV.setVisibility(visibility);
    }
    
}
