package com.frain.myapplication.ViewDrawDemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by admin on 2016/11/3.
 */
public class ViewDrawDemo extends View{

    public ViewDrawDemo(Context context) {
        super(context);
    }

    public ViewDrawDemo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewDrawDemo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ViewDrawDemo(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
    }
    //计算控件/视图大小的方法
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    //进行内容的布局的方法
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
    //根据上面计算出来的大小和位置，进行一个绘图的过程
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {//当前控件内容的canvas
        drawColck(canvas);
        super.onDraw(canvas);
    }
    public void drawColck(Canvas canvas){
        Paint paint=new Paint();//创建一个画笔对象
        paint.setStyle(Paint.Style.STROKE);//设置画笔样式为空心
        paint.setAntiAlias(false);//true设置锯齿, false清除锯齿
        //设置画笔的颜色
        paint.setColor(getResources().getColor(android.R.color.holo_red_light));
        //设置画笔的宽度
        paint.setStrokeWidth(10);
                //画圆   //圆心的x，圆心的y，圆心半径，画笔的对象
        canvas.drawCircle(500,1000,500,paint);
            //画秒针    //起始位置x,y,结束位置的x,y，画笔对象
        canvas.drawLine(500,1000,(float) x,(float)y,paint);
        //秒针转一圈360度,60秒，每次转动时6度
        //
    }









    double x;
    double y;

    /**
     *
     * @param x 末位点的x
     * @param y 末位点的y
     */
    public void setXAndY(double x,double y){
        this.x=x;
        this.y=y;
        invalidate();//重绘机制
        //【重绘的时候，view会去判断是否有需求重绘的视图】
    }


}
