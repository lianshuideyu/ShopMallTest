package com.atguigu.shopmalltest.shoppingcart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.shopmalltest.R;
import com.atguigu.shopmalltest.home.bean.GoodsBean;
import com.atguigu.shopmalltest.shoppingcart.view.AddSubView;
import com.atguigu.shopmalltest.until.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/13.
 */

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.MyViewHoler> {

    private final Context mContext;
    private final List<GoodsBean> datas;


    public ShoppingCartAdapter(Context mContext, List<GoodsBean> list) {
        this.mContext = mContext;
        this.datas = list;
    }

    @Override
    public MyViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHoler(View.inflate(mContext, R.layout.item_shop_cart, null));
    }

    @Override
    public void onBindViewHolder(MyViewHoler holder, int position) {
//1.先得到数据
        GoodsBean goodsBean = datas.get(position);
        //2.绑定数据
//        holder.cbGov.setChecked(goodsBean.isChecked());
        //图片
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+goodsBean.getFigure()).into(holder.ivGov);
        //设置名称
        holder.tvDescGov.setText(goodsBean.getName());
        //设置价格
        holder.tvPriceGov.setText("￥"+goodsBean.getCover_price());

        //设置数量
        holder.addSubView.setValue(goodsBean.getNumber());

        holder.addSubView.setMinValue(1);
        //设置库存-来自服务器-
        holder.addSubView.setMaxValue(100);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHoler extends RecyclerView.ViewHolder {
        @BindView(R.id.cb_gov)
        CheckBox cbGov;
        @BindView(R.id.iv_gov)
        ImageView ivGov;
        @BindView(R.id.tv_desc_gov)
        TextView tvDescGov;
        @BindView(R.id.tv_price_gov)
        TextView tvPriceGov;
        @BindView(R.id.addSubView)
        AddSubView addSubView;
        public MyViewHoler(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
