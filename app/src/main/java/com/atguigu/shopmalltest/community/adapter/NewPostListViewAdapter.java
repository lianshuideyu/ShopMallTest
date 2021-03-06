package com.atguigu.shopmalltest.community.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.shopmalltest.R;
import com.atguigu.shopmalltest.community.bean.NewPostBean;
import com.atguigu.shopmalltest.until.Constants;
import com.bumptech.glide.Glide;
import com.opendanmaku.DanmakuItem;
import com.opendanmaku.DanmakuView;
import com.opendanmaku.IDanmakuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/16.
 */

public class NewPostListViewAdapter extends BaseAdapter {


    private final List<NewPostBean.ResultBean> result;
    private final Context mContext;


    public NewPostListViewAdapter(Context mContext, List<NewPostBean.ResultBean> result) {
        this.mContext = mContext;
        this.result = result;
    }

    @Override
    public int getCount() {
        return result == null ? 0 : result.size();
    }

    @Override
    public NewPostBean.ResultBean getItem(int position) {
        return result.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_listview_newpost, null);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }

        NewPostBean.ResultBean resultBean = result.get(position);
        viewHolder.tvCommunityUsername.setText(resultBean.getUsername());
        Glide.with(mContext)
                .load(Constants.BASE_URL_IMAGE + resultBean.getFigure())
                .into(viewHolder.ivCommunityFigure);

        viewHolder.tvCommunitySaying.setText(resultBean.getSaying());
        viewHolder.tvCommunityLikes.setText(resultBean.getLikes());
        viewHolder.tvCommunityComments.setText(resultBean.getComments());
        //设置头像
        Glide.with(mContext)
                .load(Constants.BASE_URL_IMAGE + resultBean.getAvatar())
                .into(viewHolder.ibNewPostAvatar);

        //设置弹幕
        List<String> comment_list = resultBean.getComment_list();
        if(comment_list != null && comment_list.size() > 0) {
            //有弹幕
            viewHolder.danmakuView.setVisibility(View.VISIBLE);

            List<IDanmakuItem> list = new ArrayList<>();
            for(int i = 0; i < comment_list.size(); i++) {
                list.add(new DanmakuItem(mContext,comment_list.get(i),viewHolder.danmakuView.getWidth()));

            }

            //随机
            Collections.shuffle(list);
            viewHolder.danmakuView.addItem(list,true);
            viewHolder.danmakuView.show();

        }else {
            viewHolder.danmakuView.setVisibility(View.GONE);

        }

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_community_username)
        TextView tvCommunityUsername;
        @BindView(R.id.tv_community_addtime)
        TextView tvCommunityAddtime;
        @BindView(R.id.rl)
        RelativeLayout rl;
        @BindView(R.id.ib_new_post_avatar)
        ImageButton ibNewPostAvatar;
        @BindView(R.id.iv_community_figure)
        ImageView ivCommunityFigure;
        @BindView(R.id.tv_community_saying)
        TextView tvCommunitySaying;
        @BindView(R.id.tv_community_likes)
        TextView tvCommunityLikes;
        @BindView(R.id.tv_community_comments)
        TextView tvCommunityComments;

        @BindView(R.id.danmakuView)
        DanmakuView danmakuView;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);


        }
    }
}
