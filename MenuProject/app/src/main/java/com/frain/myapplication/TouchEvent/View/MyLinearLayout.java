package com.frain.myapplication.TouchEvent.View;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by admin on 2016/11/3.
 */
public class MyLinearLayout extends LinearLayout{
    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
    }

    @Override//分发事件方法
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //事件的对象
        Log.i("MyLinearLayout","dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override //事件拦截方法
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("MyLinearLayout","onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
    }
    @Override          //事件处理的方法
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("MyLinearLayout","onTouchEvent");
        return super.onTouchEvent(event);
    }

}
