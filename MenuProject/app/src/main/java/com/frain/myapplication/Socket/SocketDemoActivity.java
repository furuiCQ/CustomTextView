package com.frain.myapplication.Socket;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.frain.myapplication.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by admin on 2016/11/16.
 */
public class SocketDemoActivity extends Activity{
    String datas[]={"START A SOCKET"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_demo);
        LinearLayout  linearLayout = (LinearLayout) findViewById(R.id.root_linearlayout);
        for(int i=0;i<datas.length;i++){
            Button button=new Button(this);
            button.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            button.setText(datas[i]);
            button.setId(1000+i);
            button.setOnClickListener(clicklistener);
            linearLayout.addView(button);
        }
    }
    View.OnClickListener clicklistener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case 1000:
                    new Thread(){
                        @Override
                        public void run() {
                            startSocket();
                        }
                    }.start();
                    break;

            }
        }
    };
    //客户端代码
    public void startSocket(){
        try {                       //访问主机的ip地址,
            Socket socket=new Socket("10.0.2.2",30000);//Android模拟器访问本地pc的ip
                                    //127.0.0.1
            socket.setSoTimeout(5000);//socket请求超时时间
            //将数据流的字节转换成指定编码格式的字符
            BufferedReader bf=new BufferedReader(new InputStreamReader(socket.getInputStream(),"gbk"));
            String data=bf.readLine();
            Log.i("data",""+data);
            bf.close();//关闭流
            socket.close();//关闭socket连接对象
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //客户端发送账号密码给服务端
    //服务端接收到账号密码以后进行验证，并返回是否登陆成功。如果失败，则提示为什么失败
    //例如：客户端发送：账号furui，密码123456
}
