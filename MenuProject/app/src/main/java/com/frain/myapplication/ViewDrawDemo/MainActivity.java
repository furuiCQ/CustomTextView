package com.frain.myapplication.ViewDrawDemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.frain.myapplication.R;

/**
 * Created by admin on 2016/11/3.
 */
public class MainActivity extends Activity{
    ViewDrawDemo viewDrawDemo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_demo);
        viewDrawDemo=(ViewDrawDemo)findViewById(R.id.viewdraw);
        //线程控制秒针循环递增的方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while(i<60){
                    Message msg=new Message();
                    msg.what=i;//将每次递增的次数赋值what
                    handler.sendMessage(msg);//发送消息给handle，通过View进行刷新视图
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                    if(i==60){
                        i=0;
                    }
                }


            }
        }).start();
    }



    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            DrawPoint drawPoint=new DrawPoint();//创建圆心的点对象
            drawPoint.setX(500);//设置圆心的x
            drawPoint.setY(1000);//设置圆心的y
            DrawPoint endPoint=matchTagPoint(drawPoint,500,6*msg.what);//通过每次递增的角度来计算出末位点的坐标
            viewDrawDemo.setXAndY(endPoint.getX(),endPoint.getY());//将末尾点的x，y
            super.handleMessage(msg);
        }
    };






















    /**
     *
     * @param startPoint  开始位置【圆心】
     * @param Raidus  圆的半径
     * @param corner  转动的角度
     * @return
     */
    public DrawPoint matchTagPoint(DrawPoint startPoint,float Raidus,double corner){
        double radians=Math.toRadians(corner);//将角度转换成弧度
        double sinValue=Math.sin(radians);//获得对应角度的sin值
        double cosValue=Math.cos(radians);//获得对应角度的cos值
        double lengthX=sinValue*Raidus;//sin对应边的长度
        double lengthY=cosValue*Raidus;//cos对应边的长度
        DrawPoint endPoint=new DrawPoint();
        endPoint.setX(startPoint.x+lengthX);//设置结束点的x
        endPoint.setY(startPoint.y-lengthY);//设置结束点的y
        return endPoint;
    }
    public class DrawPoint{
        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }

        double x;
        double y;
    }
}
