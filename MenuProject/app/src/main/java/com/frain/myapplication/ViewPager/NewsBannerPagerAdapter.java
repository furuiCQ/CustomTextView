package com.frain.myapplication.ViewPager;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PointerIconCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.frain.myapplication.R;

import java.util.ArrayList;

/**
 * Created by admin on 2016/10/31.
 */
public class NewsBannerPagerAdapter extends PagerAdapter{
    ArrayList<View> viewArrayList;//page子视图的集合列表
    ArrayList<NewsBanner> newsBannerArrayList;//数据源啊

    public NewsBannerPagerAdapter(ArrayList<View> viewArrayList,
                                  ArrayList<NewsBanner> newsBannerArrayList){
        this.viewArrayList=viewArrayList;
        this.newsBannerArrayList=newsBannerArrayList;
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public int getCount() {
        return newsBannerArrayList.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    /**
     * 创建并添加视图到ViewPager中
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view=viewArrayList.get(position);
        TextView textView=(TextView)view.findViewById(R.id.banner_textview);
        ImageView imageView=(ImageView)view.findViewById(R.id.bannner_imageview);
        NewsBanner newsBanner=newsBannerArrayList.get(position);
        textView.setText(newsBanner.getImageText());
        imageView.setImageResource(newsBanner.getImageId());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewArrayList.get(position));
    }
}
