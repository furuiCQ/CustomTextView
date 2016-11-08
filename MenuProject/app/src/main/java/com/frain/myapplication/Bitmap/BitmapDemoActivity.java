package com.frain.myapplication.Bitmap;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.ImageView;

import com.frain.myapplication.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by admin on 2016/11/7.
 */
public class BitmapDemoActivity  extends Activity{
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_demo);
        imageView=(ImageView)findViewById(R.id.imageview);
        checkUserPermisson();
    }
    //assets【静态目录】文件目录存在main目录下，
    // assets目录的作用用于存储与java.xml代码无关资源文件，
    //会被打包进apk下面
    // 例如：.html文件，.mp3文件等



    public void createBitmap(){
        //设置BitmapFactory参数
        //1.创建一个Options对象，用于设置相关参数
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=false;
        //*设置为true则Bitmap对象是空，获取宽高需要拿options对象取
        options.inSampleSize=16;//图片宽高的缩放倍数
        //如果设为4，则宽和高都为原来的1/4，则图是原来的1/16。
        options.inDensity=20;//像素压缩比
        options.inTargetDensity=1;//目标位图的像素压缩比
        options.inScaled=true;//压缩图片从inDensity到inTargetDensity的压缩比例
        options.inPreferredConfig=Bitmap.Config.RGB_565;//设置位图参数
        //从指定的文件路径名指向的文件解析成一个Bitmap对象
        Bitmap bitmap=BitmapFactory.decodeFile(
                Environment.getExternalStorageDirectory()+"/kawayi.jpg"
        ,options);//设置参数并获取Bitmap对象
        Log.i("bitmap.getHeight()",""+options.outWidth);//1920
        Log.i("bitmap.getWidth()",""+options.outHeight);//1200
        Log.i("bitmap.getHeight()",""+bitmap.getHeight());//1920
        Log.i("bitmap.getWidth()",""+bitmap.getWidth());//1200
        imageView.setImageBitmap(bitmap);
        BitmapDrawable bitmapDrawable;
    }
    // Bitmap.Config 位图参数
    //    ALPHA_8 代表8位Alpha位图
//    ARGB_4444 代表16位ARGB位图
//    ARGB_8888 代表32位ARGB位图
//    RGB_565 代表8位RGB位图
//    位图位数越高代表其可以存储的颜色信息越多，当然图像也就越逼真。

















    //动态权限的请求
    public void checkUserPermisson(){//必须要用户点击确认以后
        if (Build.VERSION.SDK_INT >= 23) {
            ActivityCompat.requestPermissions(//动态请求权限
                    this,//上下文
                    new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},//权限数组
                    1001);//请求码
        }
    }
    //动态权限的回调函数
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Log.i("requestCode",""+requestCode);
        createBitmap();
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
