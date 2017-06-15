package com.atguigu.shopmalltest.type.adpater;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.atguigu.shopmalltest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/15.
 */

public class TypeLeftAdapter extends BaseAdapter {


    private final Context mContext;

    private String[] titles = new String[]{"小裙子", "上衣", "下装", "外套", "配件", "包包", "装扮", "居家宅品",
            "办公文具", "数码周边", "游戏专区"};

    public TypeLeftAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    
    //缓存高亮的位置
    private int prePosition;
    @Override
    public View getView(int position, View convertView , ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (convertView  == null) {
            convertView  = View.inflate(mContext, R.layout.item_type, null);
            viewHolder = new ViewHolder(convertView );

            convertView.setTag(viewHolder);
        }else {

            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvTitle.setText(titles[position]);
        
        if(prePosition == position) {
            //选中项的背景
            convertView.setBackgroundResource(R.drawable.type_item_background_selector);
            viewHolder.tvTitle.setTextColor(Color.parseColor("#fd3f3f"));
        }else {
            //设置默认
            convertView.setBackgroundResource(R.drawable.bg2);  //其他项背景
            viewHolder.tvTitle.setTextColor(Color.parseColor("#323437"));
        }

        return convertView;
    }

    public void setChangeSelected(int position) {
        this.prePosition = position;
    }


    static class ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
