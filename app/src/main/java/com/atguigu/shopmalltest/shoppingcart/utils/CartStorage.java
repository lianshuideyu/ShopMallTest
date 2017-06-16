package com.atguigu.shopmalltest.shoppingcart.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;

import com.atguigu.shopmalltest.home.bean.GoodsBean;
import com.atguigu.shopmalltest.until.CacheUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */

public class CartStorage {

    public static final String JSON_CART = "json_cart";
    private static CartStorage instace;
    private Context mContext;
    //SparseArray替代HashMap-在内存中
    private SparseArray<GoodsBean> sparseArray;

    public CartStorage(Context mContext) {
        this.mContext = mContext;
        sparseArray = new SparseArray();
        //从本地获取数据
        listToSparseArray();
    }

    /**
     * 把List的数据转换成SparseArray
     */
    private void listToSparseArray() {
        //从本地读取的集合数据
        List<GoodsBean> beanList = getAllData();
        //循环将数据转存到sparseArray
        for(int i = 0; i < beanList.size(); i++) {
            GoodsBean goodsBean = beanList.get(i);
            sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()),goodsBean);
        }
    }

    /**
     * 得到所有数据
     * @return
     */
    public List<GoodsBean> getAllData() {
        return getLocalData();
    }

    /**
     * 得到本地缓存的数据
     * @return
     */
    private List<GoodsBean> getLocalData() {
        List<GoodsBean> goodsBeens = new ArrayList<>();

        //从本地中去数据
        String json = CacheUtils.getString(mContext, JSON_CART);
        if(!TextUtils.isEmpty(json)) {
            //把它在转换成列表
            goodsBeens = new Gson().fromJson(json,new TypeToken<List<GoodsBean>>(){

            }.getType());
        }
        //把json数据解析成list数据

        return goodsBeens;
    }

    /**
     * 懒汉模式，获取单例
     * @param context
     * @return
     */
    public static CartStorage getInstace(Context context){
        if(instace == null) {
            synchronized (CartStorage.class){
                if(instace == null) {
                    instace = new CartStorage(context);
                }
            }
        }

        return instace;
    }

    /**
     * 添加数据
     * @param goodsBean
     */
    public void addData(GoodsBean goodsBean){
        //1数据添加到sparseArray
        GoodsBean tempGoodsBean = sparseArray.get(Integer.parseInt(goodsBean.getProduct_id()));
        //先判断是否已保存过
        if(tempGoodsBean != null) {
            //已经保存过,更新商品在购物车中的数量
            tempGoodsBean.setNumber(goodsBean.getNumber() + tempGoodsBean.getNumber());
        }else {
            //没有添加过
            tempGoodsBean = goodsBean;
        }

        //添加到集合中--内存
        sparseArray.put(Integer.parseInt(tempGoodsBean.getProduct_id()), tempGoodsBean);

        //2保存到本地--持久性
        saveLocal();
    }

    /**
     * 保存到本地
     */
    private void saveLocal() {
        //1把sparseArray转换成List
        List<GoodsBean> goodsBeanList = sparseArrayToList();
        //2.使用Gson把List转json的String类型数据
        String savaJson = new Gson().toJson(goodsBeanList);
        //3.使用CacheUtils缓存数据
        CacheUtils.setString(mContext,JSON_CART,savaJson);
    }
    /**
     * 把sparseArray转成List
     * @return
     */
    private List<GoodsBean> sparseArrayToList(){
        //列表数据
        List<GoodsBean> goodsBeanList = new ArrayList<>();
        for(int i = 0; i < sparseArray.size(); i++) {
            GoodsBean goodsBean = sparseArray.valueAt(i);
            goodsBeanList.add(goodsBean);
        }
        return goodsBeanList;
    }

    /**
     * 删除数据
     * @param goodsBean
     */
    public void deleteData(GoodsBean goodsBean){
        //1删除数据
        sparseArray.delete(Integer.parseInt(goodsBean.getProduct_id()));
        //2.保存到本地
        saveLocal();
    }

    /**
     * 修改数据
     * @param goodsBean
     */
    public void updateData(GoodsBean goodsBean){
        //1.删除数据
        sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()),goodsBean);

        //2.保持到本地
        saveLocal();
    }

    /**
     * 是否在购物车中存在
     * @param product_id
     * @return
     */
    public GoodsBean findDete(String product_id) {
        return  sparseArray.get(Integer.parseInt(product_id));

    }
}
