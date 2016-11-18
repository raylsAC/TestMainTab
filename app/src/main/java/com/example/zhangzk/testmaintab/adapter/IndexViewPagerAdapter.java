package com.example.zhangzk.testmaintab.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;


/**
 * 首页页面适配器
 */
public class IndexViewPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> fragmentList;
    FragmentManager fm;



    public void setFragmentList(List<Fragment> fragmentList) {
        this.fragmentList = fragmentList;
    }

    public IndexViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fm=fm;
        this.fragmentList=fragmentList;
    }

    @Override
    public Fragment getItem(int position) {

       return  fragmentList.get(position);
    }

    @Override
    public int getCount() {
        if(null!=fragmentList){
            return  fragmentList.size();
        }
        return 0;
    }



}
