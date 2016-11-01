package com.frain.myapplication.ViewPager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.frain.myapplication.Fragment.PopupWindowFragment;
import com.frain.myapplication.R;

import java.util.ArrayList;

/**
 * Created by admin on 2016/10/31.
 */
public class ViewPagerActivity extends Activity{
    ViewPager viewPager;
    ArrayList<View> arrayList;
    RadioGroup radioGroup;
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;
    RadioButton radioButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        viewPager=(ViewPager)findViewById(R.id.viewpager);
        radioGroup=(RadioGroup)findViewById(R.id.radiogroup);
        radioButton1=(RadioButton)findViewById(R.id.radiobutton1);
        radioButton2=(RadioButton)findViewById(R.id.radiobutton2);
        radioButton3=(RadioButton)findViewById(R.id.radiobutton3);
        radioButton4=(RadioButton)findViewById(R.id.radiobutton4);
        radioButton1.setChecked(true);

        arrayList=new ArrayList<View>();
        View view1=getLayoutInflater().inflate(R.layout.fragment_wechat_list,null);
        View view2=getLayoutInflater().inflate(R.layout.activity_popupwindow_main,null);
        View view3=getLayoutInflater().inflate(R.layout.listview_item_news_list_type_1,null);
        View view4=getLayoutInflater().inflate(R.layout.listview_item_news_list_type_0,null);

        arrayList.add(view1);
        arrayList.add(view2);
        arrayList.add(view3);
        arrayList.add(view4);

        MyPagerAdapter myPagerAdapter=new MyPagerAdapter(arrayList);
        viewPager.setAdapter(myPagerAdapter);
        viewPager.addOnPageChangeListener(onPageChangeListener);
        radioGroup.setOnCheckedChangeListener(checkedChangeListener);
        //

    }
    //setPageMarginDrawable()设置页面之间的间隔图片
    //setCurrentItem(int item,boolean smoothScroll)//设置当前选中项的页面
    //getPageMargin()获得页与页之间的外间距
    //getCurrentItem()获得当前显示的视图item项
    //RadioGroup.OnCheckedChangeListener
    RadioGroup.OnCheckedChangeListener checkedChangeListener=new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i){
                case R.id.radiobutton1:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.radiobutton2:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.radiobutton3:
                    viewPager.setCurrentItem(2);
                    break;
                case R.id.radiobutton4:
                    viewPager.setCurrentItem(3);
                    break;
            }
        }
    };
    ViewPager.OnPageChangeListener onPageChangeListener=new ViewPager.OnPageChangeListener() {
        @Override
        //页面滚动的回调方法           页面的下标        偏移量               下标偏移量的像素
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }
        //页面选中以后的回调方法
        @Override
        public void onPageSelected(int position) {
                                    //页面的下标
            switch (position){
                case 0:
                    radioButton1.setChecked(true);//设置radioButton的选中状态
                    break;
                case 1:
                    radioButton2.setChecked(true);
                    break;
                case 2:
                    radioButton3.setChecked(true);
                    break;
                case 3:
                    radioButton4.setChecked(true);
                    break;
            }
        }
        @Override
        public void onPageScrollStateChanged(int state) {//页面滚动状态的改变回调
            // ViewPager.SCROLL_STATE_DRAGGING  1 //  此时viewPager处于拖动的状态
            // ViewPager.SCROLL_STATE_SETTLING  2 此时ViewPager处于切换页面的中
            // ViewPager.SCROLL_STATE_IDLE 0  此时viewPager处于闲置状态
            Log.i("onPageScrollState","onPageScrollStateChanged===="+state);
        }
    };

}
