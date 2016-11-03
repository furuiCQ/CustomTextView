package com.frain.myapplication.Animation;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.frain.myapplication.R;

/**
 * Created by admin on 2016/11/1.
 */
public class AnimationDemoActivity extends Activity {
    ImageView imageView;
    AnimationSet animationSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_demo);
        imageView = (ImageView) findViewById(R.id.imageview);


//        startAlphaAniamtion();
//        startTranslateAnimation();
//        startScaleAnimation();
//        startRotateAnimation();
        //AnimationSet 动画集合
//        代码中使用TweenedAnimations的步骤：
//        1.创建一个AnimationSet对象（Animation子类）；
//        2.增加需要创建相应的Animation对象；
//        3.为Animation对象设置相应的数据；
//        4.将Animatin对象添加到AnimationSet对象当中；
//        5.使用控件对象开始执行AnimationSet。
 //       loadXmlAniamtion();
        loadDrawableAnimation();
    }
    public void loadDrawableAnimation(){
        imageView.setBackgroundResource(R.drawable.anim_progress);
        AnimationDrawable animationDrawable=(AnimationDrawable) imageView.getBackground();
        animationDrawable.start();//开始执行动画
    }





































    public void loadXmlAniamtion(){
        AnimationSet animationset= (AnimationSet)
                AnimationUtils.loadAnimation(this,R.anim.animaton_my);
        animationset.setRepeatCount(5);
        animationset.setDuration(5000);
        imageView.startAnimation(animationset);

    }
    public void startAlphaAniamtion() {//        AlphaAnimation、 透明动画
        animationSet = new AnimationSet(this, null);
        //起始透明度，结束透明度
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.3f);
       alphaAnimation.setDuration(7000);//设置动画单次的持续时间
        alphaAnimation.setRepeatCount(5);//设置动画循环次数
        animationSet.addAnimation(alphaAnimation);//将动画对象添加到动画集合里面
       // imageView.startAnimation(animationSet);//让控件开始执行动画集合中的动画
    }
    public void startTranslateAnimation() {//        TranslateAnimation、 位移动画
        //  AnimationSet animationSet = new AnimationSet(this, null);
        //起始的X,结束的x，起始的y，结束y
        TranslateAnimation translateAnimation = new TranslateAnimation(50, 550, 50, 550);
        translateAnimation.setDuration(7000);//设置动画单次的持续时间
        translateAnimation.setRepeatCount(5);//设置动画循环次数
        animationSet.addAnimation(translateAnimation);//将动画对象添加到动画集合里面
     //   imageView.startAnimation(animationSet);//让控件开始执行动画集合中的动画
    }

    public void startScaleAnimation() {//        ScaleAnimation、  缩放动画
        //  AnimationSet animationSet = new AnimationSet(this, null);
        //x的缩放起始倍数，结束x的倍数，起始的y的倍数，结束y的倍数
        ScaleAnimation scaleAnimation = new ScaleAnimation(1, 0.5f, 1, 2);
        scaleAnimation.setDuration(7000);//设置动画单次的持续时间
        scaleAnimation.setRepeatCount(5);//设置动画循环次数
        animationSet.addAnimation(scaleAnimation);//将动画对象添加到动画集合里面
       // imageView.startAnimation(animationSet);//让控件开始执行动画集合中的动画
    }
    public void startRotateAnimation() {//        RotateAnimation  旋转动画
        //  AnimationSet animationSet = new AnimationSet(this, null);
        //起始偏移角度   结束偏移角度
        RotateAnimation rotateAnimation = new RotateAnimation(0, -180);
        rotateAnimation.setDuration(7000);//设置动画单次的持续时间
        rotateAnimation.setRepeatCount(5);//设置动画循环次数
        animationSet.addAnimation(rotateAnimation);//将动画对象添加到动画集合里面
        imageView.startAnimation(animationSet);//让控件开始执行动画集合中的动画
    }

}
