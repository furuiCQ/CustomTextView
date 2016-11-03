package com.frain.myapplication.ViewFilpper;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.frain.myapplication.R;

import org.w3c.dom.Text;

import java.util.Timer;

/**
 * ViewFlipper继承于ViewAnimator，主要适合做多界面程序的引导页面效果，
 * 轻量级的组合控件，适合展示少量的静态数据以及页面切换效果；
 * Created by admin on 2016/11/1.
 */
public class ViewFilpperActivity extends Activity {
    Button button1;
    Button button2;
    ViewFlipper viewFilpper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewfillper);
        viewFilpper = (ViewFlipper) findViewById(R.id.viewflipper);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        for (int i = 0; i < 5; i++) {
            TextView textView = new TextView(this);
            textView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT));
            textView.setText("你好" + i);
            textView.setId(R.id.textview);
            textView.setTag(i);//
            textView.setGravity(Gravity.CENTER);//设置内容居中
            viewFilpper.addView(textView);
        }
        viewFilpper.setFlipInterval(3000);//设置切换的时间
        viewFilpper.startFlipping();//开始切换

       viewFilpper.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {//布局改变监听
            @Override
            public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                if(view instanceof FrameLayout){
                    Log.i("onLayoutChange", "onLayoutChange true");
                }
                //viewFilpper.getCurrentView() 获得当前展示的视图对象
                if (viewFilpper.getCurrentView() instanceof TextView) {//判断当前展示的view对象是否是textView的实例
                    TextView textView = (TextView) viewFilpper.getCurrentView();
                    Log.i("onLayoutChange", "onLayoutChange" + textView.getTag());
                    switch ((int)textView.getTag()){
                        case 0:
                            break;
                    }
                }

            }
        });


        // viewFilpper.isFlipping() 用来判断View切换是否正在进行false true
        button1.setOnClickListener(clickListener);
        button2.setOnClickListener(clickListener);
        gestureDetector = new GestureDetector(this, gestureListener);
    }
    GestureDetector gestureDetector;//手势探测器对象
    GestureDetector.OnGestureListener gestureListener = new GestureDetector.OnGestureListener() {//手势监听事件
        @Override
        public boolean onDown(MotionEvent motionEvent) {//down事件
            Log.i("onDown", "onDown=========");
            return false;
        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {//按的动作
            Log.i("onShowPress", "onShowPress=========");
        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {//一次点击up事件；
            Log.i("onSingleTapUp", "onSingleTapUp=========");
            return false;
        }

        @Override     //滚动              //滚动事件起始点    //结束点                  //2个点x的距离 //y的距离
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            Log.i("onScroll", "onScroll=========");
            return false;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {//长按事件
            Log.i("onLongPress", "onLongPress=========");
        }

        @Override //滑动手势         //滚动事件起始点    //结束点                 //x滑动的速度 //y的滑动的速度
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            if (viewFilpper.isFlipping()) {//是否切换动画
                viewFilpper.stopFlipping();//停止切换
            }
            Log.i("distanX====>", "" + (motionEvent.getX() - motionEvent1.getX()));
            float distanceX = motionEvent.getX() - motionEvent1.getX();//滑动的方向
            if (distanceX > 0.0) {//大于0 则向右滑
                viewFilpper.showNext();
            } else {//向左滑
                viewFilpper.showPrevious();
            }
            viewFilpper.startFlipping();
            Log.i("onFling", "onFling=========");
            return true;
        }
    };//练习：ViewFlipper+GestureDetector 实现百度新闻广告栏的全部效果

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);//将触摸事件交付给手势探测器对象的onTouchEvent方法
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button1:
                    viewFilpper.showPrevious();//展示上一张
                    break;
                case R.id.button2:
                    viewFilpper.showNext();//展示下一张
                    break;
            }
        }
    };
}
