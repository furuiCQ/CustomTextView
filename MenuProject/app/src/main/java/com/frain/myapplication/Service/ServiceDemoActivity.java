package com.frain.myapplication.Service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.frain.myapplication.R;

/**
 * Created by admin on 2016/11/14.
 */
public class ServiceDemoActivity extends Activity {
    String[] datas = {"Start Servicce","Stop Service(start)","Bind Service","UnBind Service","Play Music","Stop Music","Resum Music"};
    LinearLayout rootLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_demo);
        rootLinearLayout = (LinearLayout) findViewById(R.id.root_linearlayout);
        for (int i = 0; i < datas.length; i++) {
            Button button = new Button(this);
            button.setLayoutParams(
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
            button.setText(datas[i]);
            button.setId(1000 + i);
            button.setOnClickListener(clicklistener);
            rootLinearLayout.addView(button);
        }
    }

    View.OnClickListener clicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case 1000:
                    start();
                    break;
                case 1001:
                    stop();
                    break;
                case 1002:
                    bind();
                    break;
                case 1003:
                    unbind();
                    break;
                case 1004:
                    playMusic();
                    break;
                case 1005:

                    break;
            }
        }
    };
    public void playMusic(){


    }
    Intent intent;
    public void start() {
        intent = new Intent(this, MyService.class);//创建一个intent来开启服务
        this.startService(intent);//开启服务
    }
    public void stop(){
        intent = new Intent(this, MyService.class);//创建一个intent来开启服务
        this.stopService(intent);//关闭当前开启的服务
    }


    public void bind(){
        Intent intent=new Intent(this,MyService.class);
        this.bindService(intent,servicConnetcion, Context.BIND_AUTO_CREATE);
                        //intent对象 服务连接对象,创建Service模式
    }
    public void unbind(){
        this.unbindService(servicConnetcion);//与服务解除绑定
    }
    MyService myservice;
    //创建一个服务连接的对象  ，可以用来监听服务的连接与断开
    ServiceConnection servicConnetcion=new ServiceConnection() {
            @Override//服务连接的回调方法                            //此处的Binder对象是OnBind方法返回过来
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyService.MyBinder myBinder=(MyService.MyBinder)iBinder;
            myservice=myBinder.getService();
            myservice.playMusic();
            Log.i("ServiceConnection","onServiceConnected");
        }
        @Override//服务断开连接的回调 注意：当服务崩溃或者被Kill掉 执行调用
        public void onServiceDisconnected(ComponentName componentName) {
            myservice.playMusic();
            Log.i("ServiceConnection","onServiceDisconnected");
        }
    };
}
