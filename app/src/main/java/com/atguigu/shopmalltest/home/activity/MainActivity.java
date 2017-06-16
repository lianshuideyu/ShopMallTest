package com.atguigu.shopmalltest.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.atguigu.shopmalltest.R;
import com.atguigu.shopmalltest.base.BaseFragment;
import com.atguigu.shopmalltest.community.CommunityFragment;
import com.atguigu.shopmalltest.home.fragment.HomeFragment;
import com.atguigu.shopmalltest.shoppingcart.fragment.ShoppingcartFragment;
import com.atguigu.shopmalltest.type.TypeFragment;
import com.atguigu.shopmalltest.user.UserFragment;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AutoLayoutActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;

    private ArrayList<BaseFragment> fragments;
    private int position;
    private BaseFragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initFragment();

        rgMain.setOnCheckedChangeListener(this);
        //默认勾选
        rgMain.check(R.id.rb_home);
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new ShoppingcartFragment());
        fragments.add(new UserFragment());

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int id) {
        switch (id) {
            case R.id.rb_home:
                position = 0;
                break;
            case R.id.rb_type:
                position = 1;
                break;
            case R.id.rb_community:
                position = 2;
                break;
            case R.id.rb_cart:
                position = 3;
                break;
            case R.id.rb_user:
                position = 4;
                break;
        }
        BaseFragment currentFragment = fragments.get(position);
        switchFragment(currentFragment);

    }

    private void switchFragment(BaseFragment currentFragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(tempFragment != currentFragment) {
            if(tempFragment != null) {
                ft.hide(tempFragment);
            }

            if(!currentFragment.isAdded()) {
                ft.add(R.id.frameLayout,currentFragment);

            }else {
                ft.show(currentFragment);
            }


            /*if(!currentFragment.equals(fragments.get(1))) {
                TypeFragment typef = (TypeFragment) fragments.get(1);
                if(typef.tempFragment != null) {
                    ft.hide(typef.tempFragment);
                }
            }else {
                TypeFragment typef = (TypeFragment) fragments.get(1);
                if(typef.tempFragment != null) {
                    ft.show(typef.tempFragment);
                }
            }*/

            ft.commit();
            tempFragment = currentFragment;
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //得到id
        int  checkId = intent.getIntExtra("checkId",R.id.rb_home);
        switch (checkId){
            case R.id.rb_home:
                //切换到主页面
                rgMain.check(R.id.rb_home);
                break;
            case R.id.rb_cart:
                //切换到购物车
                rgMain.check(R.id.rb_cart);
                break;
        }
    }
}
