package com.frain.myapplication.PropertyAnimation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PointFEvaluator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeConverter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.frain.myapplication.R;

import org.w3c.dom.Text;

/**
 * Created by admin on 2016/11/2.
 */
public class ValueAnimatorTwoActivity extends Activity{
    Button button;//
    TextView textView;//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animator_demo);
        button=(Button)findViewById(R.id.button1);
        textView=(TextView)findViewById(R.id.textview);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnimatorSet();
            }
        });
      //  startValueAnimator();
    }
//    ValueAnimator是整个属性动画机制当中最核心的一个类，前面我们已经提到了，
//    属性动画的运行机制是通过不断地对值进行操作来实现的，
//    而初始值和结束值之间的动画过渡就是由ValueAnimator这个类来负责计算的。
//    它的内部使用一种时间循环的机制来计算值与值之间的动画过渡，我们只需要将初始值和结束值提供给ValueAnimator，
//    并且告诉它动画所需运行的时长，那么ValueAnimator就会自动帮我们完成从初始值平滑地过渡到结束值这样的效果。
    public void startValueAnimator(){
        //Animaotr
        //1.利用静态方法创建一个ValueAnimator对象，并设置值的初始值,中间值和结束值
        ValueAnimator valueAnimator=ValueAnimator.ofFloat(0f,100f,20f,60,40f);
        Log.i("ValueAnimator","ValueAnimator======1");
        //2.设置动画的持续时间
        valueAnimator.setDuration(4000);
        Log.i("ValueAnimator","ValueAnimator======2");
        //给动画对象绑定更新的监听
        valueAnimator.addUpdateListener(
                new ValueAnimator.AnimatorUpdateListener() {//动画更新监听事件【更新次数与系统进程调度与硬件的影响】
                    @Override//动画更新的回调方法
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        Log.i("onAnimationUpdate","onAnimationUpdate"+valueAnimator.getAnimatedValue());
                    }
                });
        //3.执行动画
        valueAnimator.start();
        Log.i("ValueAnimator","ValueAnimator======3");
    }

    //ObjectAnimator 对于对象进行属性改变的动画操作类，可以改变对象的所有包含的属性【注意：必须是有set/get方法】
    public void startObjectAnimator(){
        //1.ObjectAnimator调用静态方法初始化并设置初始值
        //需要改变属性的对象,属性名字,初始值，结束值
        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(textView,"ScaleX",1.0f,2.0f);
        //2.设置动画的时间
        objectAnimator.setDuration(5000);
        //3.执行动画
        objectAnimator.start();
    }
    //组合动画AnimatorSet
    public void startAnimatorSet(){
        //1.ObjectAnimator调用静态方法初始化并设置初始值
        //需要改变属性的对象,属性名字,初始值，结束值
        ObjectAnimator scaleAnimator=ObjectAnimator.ofFloat(textView,"ScaleX",1.0f,6.0f,2.0f);
        //2.设置动画的时间
        scaleAnimator.setDuration(5000);
        ObjectAnimator alphaAnimaotr=ObjectAnimator.ofFloat(textView,"alpha",1.0f,0.2f);
        alphaAnimaotr.setDuration(5000);
        ObjectAnimator bgAnimator=ObjectAnimator.ofInt(textView,"TextColor",
                getResources().getColor(android.R.color.black),
                getResources().getColor(android.R.color.holo_red_light));
        bgAnimator.setDuration(5000);
        //创建Set集合用来设置动画的执行顺序
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.play(scaleAnimator)//play返回的对象是builder对象
                .with(alphaAnimaotr)
                .with(bgAnimator);//与透明动画一起执行
        animatorSet.addListener(animatorListener);
        animatorSet.start();//动画开启
    }

    //scaleAnimator.setRepeatCount(5);设置重复次数
    //animatorSet.cancel();动画取消
    //动画执行过程监听事件
    Animator.AnimatorListener animatorListener=new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animator) {//动画开始
            Log.i("onAnimationStart","onAnimationStart");
        }
        @Override
        public void onAnimationEnd(Animator animator) {//动画结束
            Log.i("onAnimationEnd","onAnimationEnd");
        }
        @Override
        public void onAnimationCancel(Animator animator) {//动画取消
            Log.i("onAnimationCancel","onAnimationCancel");
        }
        @Override
        public void onAnimationRepeat(Animator animator) {//动画重复
            Log.i("onAnimationRepeat","onAnimationRepeat");
        }
    };
    public void setTextViewAnimator(){


    }
    //分析
    //1.第一种动画效果  圆形背景的切换+文字颜色的改变Background ，textColor
    //2.第二种动画效果  透明度的改变
    //3.把第一种动画效果应用于6个TextView上面，然后设置好执行时间的延时
    //4.第二种动画效果应用Touch To Start的TextView上，重复执行
}
