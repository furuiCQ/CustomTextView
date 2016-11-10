package com.frain.myapplication.File;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.frain.myapplication.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.jar.Manifest;

/**
 * Created by admin on 2016/11/8.
 */
public class FileEditActivity extends Activity{
    TextView addView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beiwanglu);
        openOutPut();
        addView=(TextView)findViewById(R.id.add_textview);
        addView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openInPut();
            }
        });
    }

    public void openOutPut(){
        try {
            //打开输出流
            FileOutputStream fileOutputStream=this.openFileOutput("wanglaowu.txt",
                    Context.MODE_APPEND);//追加模式
           // Context.MODE_PRIVATE //私有模式
           // Context.MODE_WORLD_READABLE //读取权限
           // Context.MODE_WORLD_WRITEABLE //读写权限
            String str="123";//创建一个字符串
            fileOutputStream.write(str.getBytes());//获得字符串的字节数据组，并写入到上面指定的文件中
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void openInPut(){
        try {
            FileInputStream fileInputStream=this.openFileInput("wanglaowu.txt");//打开输入流
            byte[] bytes=new byte[1024];
            fileInputStream.read(bytes);
            String str=new String(bytes);
            Log.i("bytes",""+str);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    Context还提供了如下几个重要的方法：
//                         getDir(String name , int mode):
//    在应用程序的数据文件夹下获取或者创建name对应的子目录
//                 File getFilesDir():获取该应用程序的数据文件夹的绝对路径
//                 String[]  fileList():返回该应用数据文件夹的全部文件
}
