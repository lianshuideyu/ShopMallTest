package com.atguigu.shopmalltest.shoppingcart;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.shopmalltest.base.BaseFragment;
import com.atguigu.shopmalltest.home.bean.GoodsBean;
import com.atguigu.shopmalltest.shoppingcart.utils.CartStorage;

/**
 * Created by Administrator on 2017/6/12.
 */

public class ShoppingcartFragment extends BaseFragment {
    private static final String TAG = ShoppingcartFragment.class.getSimpleName();//"ShoppingcartFragment"
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
        textView.setEnabled(true);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG,"绑定数据到控件上...");
        textView.setText("我是购物车");

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodsBean goodsBean  = new GoodsBean();
                goodsBean.setProduct_id("10659");
                goodsBean.setNumber(3);
                CartStorage.getInstace(mContext).updateData(goodsBean);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        for (int i=0;i<CartStorage.getInstace(mContext).getAllData().size();i++){
            Log.e("TAG",""+CartStorage.getInstace(mContext).getAllData().get(i).toString());
        }
    }
}
