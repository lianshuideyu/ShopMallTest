package com.atguigu.shopmalltest.type;

import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.atguigu.shopmalltest.R;
import com.atguigu.shopmalltest.base.BaseFragment;
import com.atguigu.shopmalltest.type.fragment.ListFragment;
import com.atguigu.shopmalltest.type.fragment.TagFragment;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/6/12.
 */

public class TypeFragment extends BaseFragment {
    private static final String TAG = TypeFragment.class.getSimpleName();//"TypeFragment"
    @BindView(R.id.tl_1)
    SegmentTabLayout tl1;
    @BindView(R.id.iv_type_search)
    ImageView ivTypeSearch;
    @BindView(R.id.fl_type)
    FrameLayout flType;
    Unbinder unbinder;
    Unbinder unbinder1;

    private String[] titls = {"分类", "标签"};
    private ArrayList<BaseFragment> fragments;
    private BaseFragment tempFragment;
    /**
     * 初始化控件
     * retur
     */
    @Override
    public View initView() {
        Log.e(TAG, "初始化主页控件...");
        View view = View.inflate(mContext, R.layout.fragment_type, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "绑定数据到控件上...");
        //设置标题数据
        tl1.setTabData(titls);

        initFragment();


        //设置点击SegmentTabLayout的监听
        tl1.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switchFragment(fragments.get(position));
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    private void switchFragment(BaseFragment currentFragment) {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        if(tempFragment != currentFragment) {
            if(tempFragment != null) {
                ft.hide(tempFragment);
            }

            if(!currentFragment.isAdded()) {
                ft.add(R.id.frameLayout,currentFragment);

            }else {
                ft.show(currentFragment);
            }

            ft.commit();
            tempFragment = currentFragment;
        }

    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new ListFragment());
        fragments.add(new TagFragment());

        //默认显示listFragment
        switchFragment(fragments.get(0));
    }

    @OnClick(R.id.iv_type_search)
    public void onViewClicked() {
        Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
