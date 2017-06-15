package com.atguigu.shopmalltest.type.adpater;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.shopmalltest.R;
import com.atguigu.shopmalltest.app.GoodsInfoActivity;
import com.atguigu.shopmalltest.home.bean.GoodsBean;
import com.atguigu.shopmalltest.type.bean.TypeBean;
import com.atguigu.shopmalltest.until.Constants;
import com.atguigu.shopmalltest.until.DensityUtil;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.atguigu.shopmalltest.home.adapter.HomeAdapter.GOODS_BEAN;

/**
 * Created by Administrator on 2017/6/15.
 */

public class TypeRightAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final List<TypeBean.ResultBean.HotProductListBean> hot_product_list;
    private final List<TypeBean.ResultBean.ChildBean> child;

    /**
     * 热卖推荐
     */
    private static final int HOT = 0;
    /**
     * 常用分类
     */
    private static final int COMMON = 1;


    /**
     * 当前类型
     */
    private int currentType = HOT;

    public TypeRightAdapter(Context mContext, TypeBean.ResultBean resultBean) {
        this.mContext = mContext;
        child = resultBean.getChild();
        hot_product_list = resultBean.getHot_product_list();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HOT) {
            return new HotViewHolder(View.inflate(mContext, R.layout.item_hot_right, null));
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == HOT) {
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
            //设置数据
            hotViewHolder.setData(hot_product_list);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == HOT) {
            currentType = HOT;
        } else {
            currentType = COMMON;
        }
        return currentType;

    }


    @Override
    public int getItemCount() {
        return 1;
    }

    class HotViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ll_hot_right)
        LinearLayout llHotRight;
        @BindView(R.id.hsl_hot_right)
        HorizontalScrollView hslHotRight;

        public HotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }

        public void setData(List<TypeBean.ResultBean.HotProductListBean> hot_product_list) {

            for (int i = 0; i < hot_product_list.size(); i++) {

                //得到数据
                final TypeBean.ResultBean.HotProductListBean listBean = hot_product_list.get(i);

                /**
                 * 线性布局
                 */
                LinearLayout myLinear = new LinearLayout(mContext);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, -2);

                params.setMargins(DensityUtil.dip2px(mContext, 5), 0, DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 20));

                myLinear.setOrientation(LinearLayout.VERTICAL);
                myLinear.setGravity(Gravity.CENTER);

                myLinear.setTag(i);

                //图片
                ImageView imageView = new ImageView(mContext);
                LinearLayout.LayoutParams ivParams = new LinearLayout.LayoutParams(DensityUtil.dip2px(mContext, 80), DensityUtil.dip2px(mContext, 80));
                ivParams.setMargins(0, 0, 0, DensityUtil.dip2px(mContext, 10));

                //请求图片
                Glide.with(mContext).load(Constants.BASE_URL_IMAGE + listBean.getFigure()).into(imageView);

                myLinear.addView(imageView,ivParams);


                //文字
                LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                TextView textView = new TextView(mContext);
                textView.setGravity(Gravity.CENTER);
//                textView.setTextColor(Color.RED);
                textView.setTextColor(Color.parseColor("#ed3f3f"));
                textView.setText("￥" + listBean.getCover_price());

                myLinear.addView(textView,tvParams);
//
//
                llHotRight.addView(myLinear, params);


                //设置点击事件
                myLinear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position  = (int) v.getTag();
                        //Toast.makeText(mContext, "position=="+position, Toast.LENGTH_SHORT).show();


                        String cover_price = listBean.getCover_price();
                        String name = listBean.getName();
                        String figure = listBean.getFigure();
                        String product_id = listBean.getProduct_id();
                        GoodsBean goodsBean = new GoodsBean(cover_price, figure,name, product_id);

                        Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                        intent.putExtra(GOODS_BEAN,goodsBean);
                        mContext.startActivity(intent);
                    }
                });
            }
        }
    }

}
