package com.atguigu.shopmalltest.community.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.atguigu.shopmalltest.R;
import com.atguigu.shopmalltest.base.BaseFragment;
import com.atguigu.shopmalltest.community.adapter.NewPostListViewAdapter;
import com.atguigu.shopmalltest.community.bean.NewPostBean;
import com.atguigu.shopmalltest.until.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/6/12.
 */

public class NewPostFragment extends BaseFragment {
    private static final String TAG = NewPostFragment.class.getSimpleName();//"UserFragment"
    @BindView(R.id.lv_new_post)
    ListView lvNewPost;
    Unbinder unbinder;
    private NewPostListViewAdapter adapter;
    private List<NewPostBean.ResultBean> result;


    /**
     * 初始化控件
     * retur
     */
    @Override
    public View initView() {
        Log.e(TAG, "初始化主页控件...");
        View view = View.inflate(mContext, R.layout.fragment_new_post, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "绑定数据到控件上...");

        getDataFromNet();
    }

    private void getDataFromNet() {
        String url = Constants.NEW_POST_URL;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "NewPostFragment联网失败==" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "NewPostFragment联网成功==" + response);
                        //解析数据
                        if (response != null) {

                            processData(response);
                        }
                    }
                });

    }

    private void processData(String json) {


        NewPostBean newPostBean = JSON.parseObject(json, NewPostBean.class);
        Log.e("TAG", "newPostFragment解析成功==" + newPostBean.getResult().get(0).getUsername());
        result = newPostBean.getResult();

        //设置适配器数据...
        adapter = new NewPostListViewAdapter(mContext,result);
        lvNewPost.setAdapter(adapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
