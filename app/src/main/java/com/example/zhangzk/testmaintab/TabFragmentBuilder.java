package com.example.zhangzk.testmaintab;

import android.os.Bundle;

import com.example.zhangzk.testmaintab.base.BaseFragment;


/**
 * 生成tab对应的fragment实例
 */
public abstract class TabFragmentBuilder {
    /**
     * tag对应的fagment
     * @param tag fragment的tag
     * @param extras 要传的参数，没有就是null 参数存在{@link BaseFragment#setExtras(Bundle)}
     * @return
     */
    public abstract BaseFragment build(String tag, Bundle extras);
}