package com.frain.myapplication.Service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.frain.myapplication.R;

/**
 * Created by admin on 2016/11/14.
 */
public class MyService extends Service{

    MyBinder myBinder=new MyBinder();
    public class MyBinder extends Binder{
        public MyService getService(){
            return MyService.this;//通过内部类返回当前Service的对象
        }
        public void playMusic(){//创建一个内部类的方法用于调用当前类的方法
            Log.i("MyBinder","playMusic");
            MyService.this.playMusic();
        }
    }






    @Nullable
    @Override
    public IBinder onBind(Intent intent) {//执行绑定
        Log.i("MyService","onBind");
        return myBinder;
    }


    MediaPlayer mediaPlayer;
    public void playMusic(){
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        mediaPlayer=MediaPlayer.create(this,R.raw.three);//媒体播放器
        mediaPlayer.start();//开启播放功能
        mediaPlayer.getCurrentPosition();//当前播放进度
        Log.i("MyService","playMusic");
    }
    //结合Service实现一个播放器的页面功能，
    //当退出当前应用，音乐不会停止。
    //回来以后还能通过Activity页面进行控制音乐

    @Override
    public boolean onUnbind(Intent intent) {//执行解除绑定
        Log.i("MyService","onUnbind");
        return super.onUnbind(intent);
    }
    @Override
    public void onRebind(Intent intent) {//重新绑定
        Log.i("MyService","onRebind");
        super.onRebind(intent);
    }
    @Override
    public void onCreate() {//创建方法
        super.onCreate();
        Log.i("MyService","onCreate"+this.toString());
    }
    @Override//如果Service已经start过了，那么将不再执行onCreate方法直接执行Service
    public int onStartCommand(Intent intent, int flags, int startId) {//开启的方法
        Log.i("MyService","onStartCommand"+this.toString()+"startId"+startId);
        playMusic();
        flags=START_STICKY;//保活机制 系统保活机制
        //第一次系统5秒左右重启服务，第二系统10秒左右...第5次系统将不再重启该服务
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onDestroy() {//销毁
        if(mediaPlayer!=null){
            mediaPlayer.release();//meiaPlayer必须在组件销毁的时候也要释放掉
        }
        super.onDestroy();
        Log.i("MyService","onDestroy"+this.toString());
    }
}
