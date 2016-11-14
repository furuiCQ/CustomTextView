package com.frain.myapplication.Service.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * 假设该Service是B Application的Service
 * Created by admin on 2016/11/14.
 */
public class GetSumService extends Service {
    public void playMusic(){
        Log.i("playMusic","playMusic");
    }
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    public void setN(long n) {
        this.n = n;
    }
    long n = 0;
    //第三步，实现AIDL中的抽象类stub
    private GetSumAIDL.Stub mBinder  = new GetSumAIDL.Stub() {//成员内部类
        @Override
        public void playMusic() throws RemoteException {
            GetSumService.this.playMusic();
        }
        @Override
        public long getSum() throws RemoteException {
            return n;
        }
        @Override
        public void setN(long n) throws RemoteException {
            GetSumService.this.setN(n);
        }

    };


}
