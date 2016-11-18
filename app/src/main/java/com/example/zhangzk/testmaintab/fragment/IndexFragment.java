package com.example.zhangzk.testmaintab.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import com.example.zhangzk.testmaintab.R;
import com.example.zhangzk.testmaintab.adapter.IndexViewPagerAdapter;
import com.example.zhangzk.testmaintab.base.BaseFragment;
import com.example.zhangzk.testmaintab.fragment.indexInfo.PortAgentFragment;
import com.example.zhangzk.testmaintab.fragment.indexInfo.PortInfoAndResFragment;
import com.example.zhangzk.testmaintab.fragment.indexInfo.PortLineupFragment;
import com.example.zhangzk.testmaintab.fragment.indexInfo.PortPdaFragment;
import com.example.zhangzk.testmaintab.util.CommunityLog;

import java.util.ArrayList;
import java.util.List;


public class IndexFragment extends BaseFragment implements View.OnClickListener {

	private static final int TYPE_PORT_INFO = 0;
	private static final int TYPE_PORT_AGENT = 1;
	private static final int TYPE_PORT_PDA = 2;
	private static final int TYPE_PORT_LINEUP = 3;

	private TextView mPortInfoBtn;
	private TextView mPortAgentBtn;
	private TextView mPortPdaBtn;
	private TextView mPortLineupBtn;

	private PortInfoAndResFragment mPortInfoAndResFragment;
	private PortAgentFragment mPortAgentFragment;
	private PortPdaFragment mPortPdaFragment;
	private PortLineupFragment mPortLineupFragment;

	private ViewPager mViewPager;
	private IndexViewPagerAdapter mViewPagerAdapter;

	private List<Fragment> mFragmentList;

	public IndexFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		CommunityLog.v(TAG, "onCreateView");
		if (mPageRoot == null) {
			mPageRoot = (ViewGroup) inflater.inflate(R.layout.frame_fragment_index, container, false);


			initUI();
			initEvent();
			initFragmentList();

			mViewPager.setAdapter(mViewPagerAdapter);
			clickTopMenu(TYPE_PORT_INFO);
		}

		ViewParent parent = mPageRoot.getParent();
		if (parent != null && parent instanceof ViewGroup) {
			((ViewGroup) parent).removeView(mPageRoot);
		}

		return mPageRoot;
	}

	private void initUI() {

		mViewPager = (ViewPager) findViewById(R.id.port_info_viewpager);

		mPortInfoBtn = (TextView) findViewById(R.id.port_info_info_btn);
		mPortAgentBtn = (TextView) findViewById(R.id.port_info_agent_btn);
		mPortPdaBtn = (TextView) findViewById(R.id.port_info_pda_btn);
		mPortLineupBtn = (TextView) findViewById(R.id.port_info_lineup_btn);
	}

	private void initEvent() {
		mPortInfoBtn.setOnClickListener(this);
		mPortAgentBtn.setOnClickListener(this);
		mPortPdaBtn.setOnClickListener(this);
		mPortLineupBtn.setOnClickListener(this);

		mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(int position) {
				clickTopMenu(position);
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
	}

	private void initFragmentList(){
		if(mFragmentList == null || mViewPagerAdapter == null){
			mFragmentList = new ArrayList<Fragment>();
			mPortInfoAndResFragment = new PortInfoAndResFragment();
			mPortAgentFragment = new PortAgentFragment();
			mPortPdaFragment = new PortPdaFragment();
			mPortLineupFragment = new PortLineupFragment();
			mFragmentList.add(mPortInfoAndResFragment);
			mFragmentList.add(mPortAgentFragment);
			mFragmentList.add(mPortPdaFragment);
			mFragmentList.add(mPortLineupFragment);
			mViewPagerAdapter=new IndexViewPagerAdapter(getChildFragmentManager(),mFragmentList);
		}

	}

	private void clickTopMenu(int position){
		if(TYPE_PORT_INFO == position){
			mPortInfoBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_port_info_and_res_select));
			mPortAgentBtn.setBackgroundColor(getResources().getColor(R.color.blue));
			mPortPdaBtn.setBackgroundColor(getResources().getColor(R.color.blue));
			mPortLineupBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_port_lineup_normal));
		}else if(TYPE_PORT_AGENT == position){
			mPortInfoBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_port_info_and_res_normal));
			mPortAgentBtn.setBackgroundColor(getResources().getColor(R.color.blue_23));
			mPortPdaBtn.setBackgroundColor(getResources().getColor(R.color.blue));
			mPortLineupBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_port_lineup_normal));
		}else if(TYPE_PORT_PDA == position){
			mPortInfoBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_port_info_and_res_normal));
			mPortAgentBtn.setBackgroundColor(getResources().getColor(R.color.blue));
			mPortPdaBtn.setBackgroundColor(getResources().getColor(R.color.blue_23));
			mPortLineupBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_port_lineup_normal));
		}else if(TYPE_PORT_LINEUP == position){
			mPortInfoBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_port_info_and_res_normal));
			mPortAgentBtn.setBackgroundColor(getResources().getColor(R.color.blue));
			mPortPdaBtn.setBackgroundColor(getResources().getColor(R.color.blue));
			mPortLineupBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_port_lineup_select));
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.port_info_info_btn:
				mViewPager.setCurrentItem(TYPE_PORT_INFO);
				clickTopMenu(TYPE_PORT_INFO);
				break;
			case R.id.port_info_agent_btn:
				mViewPager.setCurrentItem(TYPE_PORT_AGENT);
				clickTopMenu(TYPE_PORT_AGENT);
				break;
			case R.id.port_info_pda_btn:
				mViewPager.setCurrentItem(TYPE_PORT_PDA);
				clickTopMenu(TYPE_PORT_PDA);
				break;
			case R.id.port_info_lineup_btn:
				mViewPager.setCurrentItem(TYPE_PORT_LINEUP);
				clickTopMenu(TYPE_PORT_LINEUP);
				break;
			default:
				break;
		}
	}

}
