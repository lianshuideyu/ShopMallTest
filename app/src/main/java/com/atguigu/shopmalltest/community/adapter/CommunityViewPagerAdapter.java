package com.atguigu.shopmalltest.community.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.atguigu.shopmalltest.community.fragment.HotPostFragment;
import com.atguigu.shopmalltest.community.fragment.NewPostFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */

public class CommunityViewPagerAdapter extends FragmentPagerAdapter{

    private List<Fragment> fragmentList;

    private String[] titles = new String[]{"新帖", "热帖"};


    public CommunityViewPagerAdapter(FragmentManager fm) {
        super(fm);
        initFragments();
    }

    private void initFragments() {
        fragmentList = new ArrayList<>();

        fragmentList.add(new NewPostFragment());
        fragmentList.add(new HotPostFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return titles[position];
    }

}
