package com.atguigu.shopmalltest.home.fragment;

import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.atguigu.shopmalltest.R;
import com.atguigu.shopmalltest.base.BaseFragment;
import com.atguigu.shopmalltest.home.bean.HomeBean;
import com.atguigu.shopmalltest.until.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/6/12.
 */

public class HomeFragment extends BaseFragment {
    private static final String TAG = HomeFragment.class.getSimpleName();//"HomeFragment"
    private String homeUrl;

    /**
     * 初始化控件
     * retur
     */
    @Override
    public View initView() {
        Log.e(TAG,"初始化主页控件...");

        View view =  View.inflate(mContext, R.layout.fragment_home,null);

        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG,"绑定数据到控件上...");

        getDataFromNet();
    }

    private void getDataFromNet() {
        homeUrl = Constants.HOME_URL;
        OkHttpUtils
                .get()
                .url(homeUrl)
                .build()
                .execute(new MyStringCallback());

    }

    private class MyStringCallback extends StringCallback {

        @Override
        public void onError(Call call, Exception e, int id) {
            Log.e(TAG,"联网失败==" + e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {
            Log.e(TAG,"联网成功." + response);
            //解析数据
            processData(response);
        }
    }

    private void processData(String json) {

        HomeBean homeBean = JSON.parseObject(json, HomeBean.class);

        Log.e(TAG,"解析成功==" + homeBean.getResult().getAct_info().get(0).getName());
    }
}
