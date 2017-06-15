package com.atguigu.shopmalltest.type.fragment;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.atguigu.shopmalltest.R;
import com.atguigu.shopmalltest.base.BaseFragment;
import com.atguigu.shopmalltest.type.adpater.TagGridViewAdapter;
import com.atguigu.shopmalltest.type.bean.TagBean;
import com.atguigu.shopmalltest.until.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/6/14.
 */

public class TagFragment extends BaseFragment {

    @BindView(R.id.gv_tag)
    GridView gvTag;
    Unbinder unbinder;
    private List<TagBean.ResultBean> result;
    private TagGridViewAdapter adapter;

    @Override
    public View initView() {

        View view = View.inflate(mContext, R.layout.fragment_tag, null);
        unbinder = ButterKnife.bind(this, view);

        gvTag.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(mContext, "" + result.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        //联网请求
        getDataFromNet();

    }

    private void getDataFromNet() {

        String url = Constants.TAG_URL;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG","TagFragment联网失败==" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG","TagFragment联网成功==" + response);
                        //解析数据
                        processData(response);
                    }
                });
    }

    private void processData(String json) {
        TagBean tagBean = JSON.parseObject(json, TagBean.class);
        result = tagBean.getResult();
        Log.e("TAG","TagFragment解析成功==" + result.get(0).getName());
        if(result != null && result.size() > 0) {

            adapter = new TagGridViewAdapter(mContext,result);
            gvTag.setAdapter(adapter);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
