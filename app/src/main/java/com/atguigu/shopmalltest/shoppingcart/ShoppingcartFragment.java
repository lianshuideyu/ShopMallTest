package com.atguigu.shopmalltest.shoppingcart;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.shopmalltest.R;
import com.atguigu.shopmalltest.base.BaseFragment;
import com.atguigu.shopmalltest.home.bean.GoodsBean;
import com.atguigu.shopmalltest.shoppingcart.adapter.ShoppingCartAdapter;
import com.atguigu.shopmalltest.shoppingcart.utils.CartStorage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/6/12.
 */

public class ShoppingcartFragment extends BaseFragment {
    private static final String TAG = ShoppingcartFragment.class.getSimpleName();//"ShoppingcartFragment"
    @BindView(R.id.tv_shopcart_edit)
    TextView tvShopcartEdit;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.checkbox_all)
    CheckBox checkboxAll;
    @BindView(R.id.tv_shopcart_total)
    TextView tvShopcartTotal;
    @BindView(R.id.btn_check_out)
    Button btnCheckOut;
    @BindView(R.id.ll_check_all)
    LinearLayout llCheckAll;
    @BindView(R.id.checkbox_delete_all)
    CheckBox checkboxDeleteAll;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.btn_collection)
    Button btnCollection;
    @BindView(R.id.ll_delete)
    LinearLayout llDelete;
    @BindView(R.id.iv_empty)
    ImageView ivEmpty;
    @BindView(R.id.tv_empty_cart_tobuy)
    TextView tvEmptyCartTobuy;
    @BindView(R.id.ll_empty_shopcart)
    LinearLayout llEmptyShopcart;
    Unbinder unbinder;
    private ShoppingCartAdapter adapter;

    /**
     * 初始化控件
     * retur
     */
    @Override
    public View initView() {
        Log.e(TAG, "初始化主页控件...");
        View view = View.inflate(mContext, R.layout.fragment_shopping_cart, null);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "绑定数据到控件上...");
        List<GoodsBean> list =  CartStorage.getInstace(mContext).getAllData();
        if(list != null && list.size() >0){
            //购物车有数据
            llEmptyShopcart.setVisibility(View.GONE);

            adapter = new ShoppingCartAdapter(mContext,list);
            //设置RecyclerView的适配器
            recyclerview.setAdapter(adapter);

            //布局管理器
            recyclerview.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));

            //设置点击事件


        }else{
            //购物车没有数据
            llEmptyShopcart.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        for (int i = 0; i < CartStorage.getInstace(mContext).getAllData().size(); i++) {
            Log.e("TAG", "" + CartStorage.getInstace(mContext).getAllData().get(i).toString());
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_shopcart_edit, R.id.checkbox_all, R.id.btn_check_out, R.id.checkbox_delete_all, R.id.btn_delete, R.id.btn_collection, R.id.tv_empty_cart_tobuy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_shopcart_edit:
                Toast.makeText(mContext, "编辑", Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkbox_all:
                Toast.makeText(mContext, "全选", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_check_out:
                Toast.makeText(mContext, "结算", Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkbox_delete_all:
                Toast.makeText(mContext, "删除全选", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_delete:
                Toast.makeText(mContext, "删除按钮", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_collection:
                Toast.makeText(mContext, "收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_empty_cart_tobuy:
                Toast.makeText(mContext, "去逛逛", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
