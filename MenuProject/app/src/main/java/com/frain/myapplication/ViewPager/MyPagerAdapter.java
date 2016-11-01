package com.frain.myapplication.ViewPager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.frain.myapplication.R;

import java.util.ArrayList;

/**
 * 自定义PagerAdapter以显示视图
 * Created by admin on 2016/10/31.
 */
public class MyPagerAdapter extends PagerAdapter {
    ArrayList<View> arrayList;
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
    @Override
    public int getCount() {//返回数据源的总条数
        return arrayList.size();
    }
    public MyPagerAdapter(ArrayList<View> viewArrayList) {
        this.arrayList=viewArrayList;
    }
    @Override
    //获得每个item项的下标
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    //getView(ViewGroup,View,int)

    /**
     * 用于显示每个item的视图
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(arrayList.get(position));
        return arrayList.get(position);
    }
    //销毁item
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(arrayList.get(position));
    }


}

