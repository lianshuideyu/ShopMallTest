package com.atguigu.shopmalltest.type.fragment;

import android.view.View;

import com.atguigu.shopmalltest.R;
import com.atguigu.shopmalltest.base.BaseFragment;

/**
 * Created by Administrator on 2017/6/14.
 */

public class ListFragment extends BaseFragment{

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_list, null);

        return view;
    }

    @Override
    public void initData() {
        super.initData();
    }

}
