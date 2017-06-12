package com.atguigu.shopmalltest.user;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.shopmalltest.base.BaseFragment;

/**
 * Created by Administrator on 2017/6/12.
 */

public class UserFragment extends BaseFragment {
    private static final String TAG = UserFragment.class.getSimpleName();//"UserFragment"
    private TextView textView;

    /**
     * 初始化控件
     * retur
     */
    @Override
    public View initView() {
        Log.e(TAG,"初始化主页控件...");
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG,"绑定数据到控件上...");
        textView.setText("我是用户中心");
    }

}
