package com.frain.myapplication.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
        import android.util.Log;

/**
 * Created by admin on 2016/11/10.
 */
public class MyBroadCastRecevier extends BroadcastReceiver{
    //接收的回调 可以接收到所有的广播
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("onReceive","onReceive");
        //接收到广播后执行的逻辑
        if(intent.getAction().equals("www.baidu.com.play")){
            Log.i("onReceive","播放音乐");
        }else{
            Log.i("onReceive","其他的广播进来了");
        }

    }
}
