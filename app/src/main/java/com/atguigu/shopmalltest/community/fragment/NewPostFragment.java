package com.atguigu.shopmalltest.community.fragment;

import android.util.Log;
import android.view.View;

import com.atguigu.shopmalltest.R;
import com.atguigu.shopmalltest.base.BaseFragment;

/**
 * Created by Administrator on 2017/6/12.
 */

public class NewPostFragment extends BaseFragment {
    private static final String TAG = NewPostFragment.class.getSimpleName();//"UserFragment"

    /**
     * 初始化控件
     * retur
     */
    @Override
    public View initView() {
        Log.e(TAG,"初始化主页控件...");
        View view = View.inflate(mContext, R.layout.fragment_new_post, null);

        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG,"绑定数据到控件上...");
    }

}
