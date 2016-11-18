package com.example.zhangzk.testmaintab;

import android.os.Bundle;

import com.example.zhangzk.testmaintab.base.BaseFragment;
import com.example.zhangzk.testmaintab.fragment.IndexFragment;
import com.example.zhangzk.testmaintab.fragment.ShopMallFragment;
import com.example.zhangzk.testmaintab.fragment.SquareFragment;
import com.example.zhangzk.testmaintab.fragment.UserFragment;

import java.util.HashMap;
import java.util.Map;


/**
 * 生成主框架tab对应的fragment
 */
public class MainTabFragmentBuilder extends TabFragmentBuilder {

    /** 有些fragment不希望每次都生成新实例 */
    private Map<String, BaseFragment> mCacheFragment = null;

    /**
     * 参数用{@link #Bundle}传。
     *
     * @param tag see {@link #TAG_TREND} etc.
     * @param extras 参数存在{@link BaseFragment#setExtras(Bundle)}
     * @return
     */
    @Override
    public final BaseFragment build(String tag, Bundle extras) {
        BaseFragment fragment = null;
        fragment = getCacheFragment(tag);
        if (fragment == null) {
            if (tag.equals(MainTabSpec.TAG_INDEX)) {
                fragment = new IndexFragment();
                addFragmentToCache(tag, fragment);
			} else if (tag.equals(MainTabSpec.TAG_SQUARE)) {
				fragment = new SquareFragment();
				addFragmentToCache(tag, fragment);
			} else if (tag.equals(MainTabSpec.TAG_SHOPMALL)) {
				fragment = new ShopMallFragment();
				addFragmentToCache(tag, fragment);
			} else if (tag.equals(MainTabSpec.TAG_USER)) {
				fragment = new UserFragment();
				addFragmentToCache(tag, fragment);
			} else {
				fragment = null;
			}
        }
        
        if (fragment != null && extras != null) {
            fragment.setExtras(extras);
        }

        return fragment;
    }

    // 缓存Fragment
    private void addFragmentToCache(String tag, BaseFragment f) {
        if (mCacheFragment == null) {
            mCacheFragment = new HashMap<String, BaseFragment>();
        }
        mCacheFragment.put(tag, f);
    }

    // 获取Fragment
    private BaseFragment getCacheFragment(String tag) {
        if (mCacheFragment == null) {
            return null;
        }

        return mCacheFragment.get(tag);
    }

    // 清空缓存
    public void clearCache() {
        if (mCacheFragment != null) {
            mCacheFragment.clear();
        }
        mCacheFragment = null;
    }
}
