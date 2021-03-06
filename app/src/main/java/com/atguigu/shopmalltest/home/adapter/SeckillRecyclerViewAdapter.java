package com.atguigu.shopmalltest.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.shopmalltest.R;
import com.atguigu.shopmalltest.home.bean.HomeBean;
import com.atguigu.shopmalltest.until.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/12.
 */

public class SeckillRecyclerViewAdapter extends RecyclerView.Adapter<SeckillRecyclerViewAdapter.MyViewHolder> {

    private final Context mContext;
    private final List<HomeBean.ResultBean.SeckillInfoBean.ListBean> datas;


    private LayoutInflater inflater;

    public SeckillRecyclerViewAdapter(Context mContext, HomeBean.ResultBean.SeckillInfoBean seckill_info) {
        this.mContext = mContext;
        this.datas = seckill_info.getList();
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_seckill, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //绑定数据
        HomeBean.ResultBean.SeckillInfoBean.ListBean listBean = datas.get(position);
        //加载图片
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+listBean.getFigure()).into(holder.ivFigure);
        holder.tvCoverPrice.setText("￥"+ listBean.getCover_price());
        holder.tvOriginPrice.setText("￥"+ listBean.getOrigin_price());


    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_figure)
        ImageView ivFigure;
        @BindView(R.id.tv_cover_price)
        TextView tvCoverPrice;
        @BindView(R.id.tv_origin_price)
        TextView tvOriginPrice;
        @BindView(R.id.ll_root)
        LinearLayout llRoot;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            //点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null) {
                        listener.setOnItemClick(getLayoutPosition());
                    }
                }
            });
        }
    }

    public interface OnItemClickListener{
        public void setOnItemClick(int positon);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener l){
        this.listener = l;
    }

}
