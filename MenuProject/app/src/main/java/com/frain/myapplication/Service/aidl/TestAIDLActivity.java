package com.frain.myapplication.Service.aidl;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.frain.myapplication.R;

/**
 * 该Activity是A Application的Activity
 * Created by admin on 2016/11/14.
 */
public class TestAIDLActivity extends Activity {
    String[] datas = {"setN", "getSum"};
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
        onBind();
    }
    View.OnClickListener clicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case 1000:
                    setN();
                    break;
                case 1001:
                    getSum();
                    break;

            }
        }
    };
    //第四步：绑定AIDL Service
    public void onBind() {
        Intent intent = new Intent(this, GetSumService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
        //  Intent intent = new Intent("com.frain.GetSumService");
       // intent.setPackage("com.frain.myapplication");//Android6.0必须指定包名
       // bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }
    public void setN() {
        if (mService != null) {
            try {
                mService.setN(10000);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
    public void getSum() {
        if (mService != null) {
            try {
                long sum = mService.getSum();
                System.out.println("sum=" + sum);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
    //服务对象
    GetSumAIDL mService = null;
    //链接对象
    ServiceConnection connection = new ServiceConnection() {
        @Override//出现异常的时候才会断开，那么正常解除绑定不会触发这个方法
        public void onServiceDisconnected(ComponentName name) {
        }
        @Override//跟服务连接上
        public void onServiceConnected(ComponentName name, IBinder binder) {
            System.out.println("name==============>" + name.toShortString());
            mService = GetSumAIDL.Stub.asInterface(binder);//通过stub的方法获得本次服务的对象
        }
    };


}
