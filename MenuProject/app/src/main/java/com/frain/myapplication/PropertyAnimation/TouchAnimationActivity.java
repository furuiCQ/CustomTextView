package com.frain.myapplication.PropertyAnimation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import com.frain.myapplication.R;

/**
 * Created by admin on 2016/11/2.
 */
public class TouchAnimationActivity extends Activity {
    Button button;//
    TextView textView;//
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animator_demo);
        button = (Button) findViewById(R.id.button1);
        textView = (TextView) findViewById(R.id.textview_e);
        textView2=(TextView)findViewById(R.id.show_textview);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnimation();
            }
        });
    }
    int i=0;
    public void startAnimation() {
        textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_bg_progress));
        final AnimationDrawable animationDrawable = (AnimationDrawable) textView.getBackground();
        animationDrawable.setOneShot(false);
        animationDrawable.start();
        AlphaAnimation aas = new AlphaAnimation(1.0f, 1.0f);
        aas.setDuration(1000);
        aas.setRepeatCount(10000);
        aas.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {//动画结束
//                //停止帧动画
                Log.i("onAnimationEnd","onAnimationEnd");
            }
            @Override
            public void onAnimationRepeat(Animation animation) {//动画重复
                if(i%2==0){
                    textView.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                }else{
                    textView.setTextColor(getResources().getColor(android.R.color.black));
                }
                i++;
                Log.i("onAnimationRepeat","onAnimationRepeat");
            }
            @Override
            public void onAnimationStart(Animation animation) {//动画开启
                Log.i("onAnimationStart","onAnimationStart");

            }
        });
        textView.startAnimation(aas);

        //第二个动画
        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(textView2,"alpha",1.0f,0.0f);
        objectAnimator.setDuration(2000);
        objectAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        objectAnimator.setRepeatCount(10000);
        objectAnimator.start();

    }
}
