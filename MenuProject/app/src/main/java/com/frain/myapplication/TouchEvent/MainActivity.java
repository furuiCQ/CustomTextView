package com.frain.myapplication.TouchEvent;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.frain.myapplication.R;

/**
 * Created by admin on 2016/11/3.
 */
public class MainActivity extends Activity{
    ListView listView;
    Button button;
    boolean isShowing=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_touch_event_demo);
        listView=(ListView)findViewById(R.id.listview);
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new String[]{
                "你好", "你好",
                "你好", "你好",
                "你好", "你好",
                "你好", "你好",

        }));
        button=(Button)findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isShowing){
                    dismissListView();
                }else{
                    showListView();
                }
            }
        });
    }
    public void showListView(){
        if(listView.getVisibility()== View.GONE){
            listView.setVisibility(View.VISIBLE);
        }
        Log.i("listView.getHeight()",""+listView.getHeight());
        isShowing=true;
        ObjectAnimator scaleAnimator=ObjectAnimator.ofFloat(listView,"scaleY",0f,1f);
        scaleAnimator.setDuration(1500);
        ObjectAnimator translateAniamtor=ObjectAnimator.ofFloat(listView,"TranslationY",
                -listView.getHeight()/2,0);
        translateAniamtor.setDuration(1500);
        AnimatorSet animatorSet= new AnimatorSet();
        animatorSet.play(scaleAnimator).with(translateAniamtor);
        animatorSet.start();
    }
    public void dismissListView(){
        isShowing=false;
        Log.i("listView.getHeight()",""+listView.getHeight());
        ObjectAnimator scaleAnimator=ObjectAnimator.ofFloat(listView,"scaleY",1f,0f);
        scaleAnimator.setDuration(1500);
        ObjectAnimator translateAniamtor=ObjectAnimator.ofFloat(listView,"TranslationY",
                0,-listView.getHeight()/2);
        translateAniamtor.setDuration(1500);
        AnimatorSet animatorSet= new AnimatorSet();
        animatorSet.play(scaleAnimator).with(translateAniamtor);
        animatorSet.start();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
       //分发事件方法
      Log.i("MainActivity","dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }
    @Override          //事件处理的方法
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("MainActivity","onTouchEvent");
        if(isShowing){
            dismissListView();
        }
        return super.onTouchEvent(event);
    }
}
