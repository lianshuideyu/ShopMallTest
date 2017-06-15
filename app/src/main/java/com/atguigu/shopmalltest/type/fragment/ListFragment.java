package com.atguigu.shopmalltest.type.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.atguigu.shopmalltest.R;
import com.atguigu.shopmalltest.base.BaseFragment;
import com.atguigu.shopmalltest.type.adpater.TypeLeftAdapter;
import com.atguigu.shopmalltest.type.adpater.TypeRightAdapter;
import com.atguigu.shopmalltest.type.bean.TypeBean;
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

public class ListFragment extends BaseFragment {

    @BindView(R.id.lv_left)
    ListView lvLeft;
    @BindView(R.id.rv_right)
    RecyclerView rvRight;
    Unbinder unbinder;

    private TypeLeftAdapter leftAdapter;

    //联网地址
    private String[] urls = new String[]{Constants.SKIRT_URL, Constants.JACKET_URL, Constants.PANTS_URL,
            Constants.OVERCOAT_URL, Constants.ACCESSORY_URL, Constants.BAG_URL, Constants.DRESS_UP_URL,
            Constants.HOME_PRODUCTS_URL, Constants.STATIONERY_URL,
            Constants.DIGIT_URL, Constants.GAME_URL};

    private List<TypeBean.ResultBean> result;
    private TypeRightAdapter rightAdapter;


    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_list, null);
        unbinder = ButterKnife.bind(this, view);

        lvLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //点击高亮的item位置
                leftAdapter.setChangeSelected(position);

              //刷新适配器
                leftAdapter.notifyDataSetChanged();

                getDataFromNet(urls[position]);
            }
        });
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        leftAdapter = new TypeLeftAdapter(mContext);
        lvLeft.setAdapter(leftAdapter);

        //联网请求,默认请求第一个item
        getDataFromNet(urls[0]);
    }

    private void getDataFromNet(String url) {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG","ListFragment联网失败==" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG","ListFragment联网成功==" + response);
                        //解析数据
                        processData(response);
                    }
                });

    }

    private void processData(String response) {
        TypeBean typeBean = JSON.parseObject(response, TypeBean.class);
        result = typeBean.getResult();
        Log.e("TAG","ListFragment解析成功=="+typeBean.getResult().get(0).getName());

        rightAdapter = new TypeRightAdapter(mContext,result.get(0));
        rvRight.setAdapter(rightAdapter);

        //布局管理
        GridLayoutManager manager = new GridLayoutManager(mContext,3);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(position == 0) {
                    return 3;
                }else {
                    return 1;
                }
            }
        });

        rvRight.setLayoutManager(manager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
