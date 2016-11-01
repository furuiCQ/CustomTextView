package com.frain.myapplication.ViewPageAndFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.frain.myapplication.R;

import java.util.ArrayList;

/**
 * ViewPager+Fragment
 * Created by admin on 2016/10/31.
 */
public class ViewPagerFragmentActivity extends FragmentActivity{
    //FragmentActivity父类Activity，所以依然会有getFragmentManager();---返回的依然是app包下FragmentManager对象
    //getSupportFragmentManager 返回的是v4包对应的FragmentManager对象
    ViewPager viewPager;
    ArrayList<Fragment> fragmentArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        viewPager=(ViewPager)findViewById(R.id.viewpager);
        fragmentArrayList=new ArrayList<Fragment>();
        WeChatListFragment  weChatListFragment=new WeChatListFragment();
        ContactFragment contactFragment=new ContactFragment();
        FoundFragment foundFragment=new FoundFragment();
        MineFragment mineFragment=new MineFragment();
        fragmentArrayList.add(weChatListFragment);
        fragmentArrayList.add(contactFragment);
        fragmentArrayList.add(foundFragment);
        fragmentArrayList.add(mineFragment);
        //构建一个Fragment的适配器，并传入v4包下对应FragmentManager对象
        MyFragmentPagerAdapter fragmentPagerAdapter=new MyFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentPagerAdapter);
    }

    //PageAdapter?
    //FragmentPagerAdapter--->自定义
    public class MyFragmentPagerAdapter extends FragmentPagerAdapter{
        //注意必须实现一个默认的构造器，并传入FragmentManager对象
        public MyFragmentPagerAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
        }
        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }
        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }

    }
}
