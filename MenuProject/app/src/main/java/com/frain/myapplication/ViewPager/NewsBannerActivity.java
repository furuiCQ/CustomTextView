package com.frain.myapplication.ViewPager;

/**
 * Created by admin on 2016/10/31.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.frain.myapplication.R;

import java.util.ArrayList;

/**
 * Created by admin on 2016/10/31.
 */
public  class NewsBannerActivity extends Activity {
    ViewPager viewPager;
    ArrayList<View> viewArrayList;//page子视图的集合列表
    ArrayList<NewsBanner> newsBannerArrayList;//数据源啊

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_banner);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        newsBannerArrayList = new ArrayList<NewsBanner>();
        viewArrayList = new ArrayList<View>();
        for (int i = 0; i < 5; i++) {
            View view = getLayoutInflater().inflate(R.layout.viewpager_item_news_banner,
                    null);
            viewArrayList.add(view);
            NewsBanner newsBanner = new NewsBanner();
            newsBanner.setImageId(R.mipmap.data_image_add);
            newsBanner.setImageText("标题" + i);
            newsBannerArrayList.add(newsBanner);
        }
        NewsBannerPagerAdapter newsBannerPagerAdapter = new NewsBannerPagerAdapter(
                viewArrayList, newsBannerArrayList
        );
        viewPager.setAdapter(newsBannerPagerAdapter);
     //   viewPager.addOnPageChangeListener();//页面改变的监听事件

    }


}