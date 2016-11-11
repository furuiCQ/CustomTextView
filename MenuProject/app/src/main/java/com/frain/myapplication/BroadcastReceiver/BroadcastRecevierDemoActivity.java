package com.frain.myapplication.BroadcastReceiver;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.frain.myapplication.R;

/**
 * Created by admin on 2016/11/10.
 */
public class BroadcastRecevierDemoActivity extends Activity{
    MyBroadCastRecevier myBroadCastRecevier;
    Button registerButton;
    Button unregsiterButton;
    Button sendButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcastrecevier);
        myBroadCastRecevier=new MyBroadCastRecevier();
        registerButton=(Button)findViewById(R.id.register_button);
        unregsiterButton=(Button)findViewById(R.id.unregister_button);
        sendButton=(Button)findViewById(R.id.send_button);
        registerButton.setOnClickListener(listener);
        unregsiterButton.setOnClickListener(listener);
        sendButton.setOnClickListener(listener);
    }
    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.register_button:
                    registerBroadCast();
                    break;
                case R.id.unregister_button:
                    unregister();
                    break;
                case R.id.send_button:
                    send();
                    break;
            }
        }
    };
    //广播
    //作用 通知的作用
    //例子:
    //广播分为三种
    // 1.无序广播
    // 2.有序广播  优先级之分
    // 3.粘性广播
    //Notification+广播实现控制页面播放音乐的功能
    //如何使用广播
    //1.自定义一个广播类
    //2.重写里面的onReceive方法
    //3.将广播进行注册
    //注册分为2种 1.静态注册
    // <receiver android:name=".BroadcastReceiver.MyBroadCastRecevier">
//    <intent-filter>
//    <action android:name="www.baidu.com.play"/>
//    <action android:name="www.baidu.com.stop"/>
//    <action android:name="www.baidu.com.resume"/>
    //    </intent-filter>
//    </receiver>
    // 2.动态注册 注册完成以后，退出当前Activity的时候记得反注册
    public void registerBroadCast(){
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("www.baidu.com.play");
//        intentFilter.addAction("www.baidu.com.hello");
        //匹配的时候，广播的Action大于等于发送的Action
        this.registerReceiver(myBroadCastRecevier,intentFilter);
    }
    public void unregister(){
        this.unregisterReceiver(myBroadCastRecevier);//可以理解为是销毁
    }
    //如果发送一条广播
    public void send(){
        Intent intent=new Intent();
        intent.putExtra("id",110);
        intent.setAction("www.baidu.com.hello");//设置动作
        sendBroadcast(intent);//发送
    }
    /**
     * that was originally registered here.
     * Are you missing a call to unregisterReceiver()?
     * 异常。动态注册在组件被销毁的时候没有执行反注册
     */
    //周四-周日的练习 周一交上
    //音乐播放器
    //1.实现通知栏常驻功能 播放键，暂停键，继续键，下一首，上一首,进度条
    //2.音乐播放Activity,播放键，暂停键，继续键，下一首，上一首,进度条
    //3.进阶练习音乐播放列表,读取本地内存中.mp3后缀的文件，并将其呈现出列表的形式
    //给Notification中的自定义布局设置点击事件，
    // 并让点击事件发送广播，通知Activity中的
    //对应的方法
    //Notification+广播
    // 实现自定义布局的响应
}

