package com.frain.myapplication.Intent;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import com.frain.myapplication.MainActivity;
import com.frain.myapplication.TwoActivity;

/**
 * Created by admin on 2016/10/25.
 */
public class IntentType {
    String strID = "123";
//    public void showStart(){
//        //创建一个显式的 Intent 对象(方法一：在构造函数中指定)
//        Intent intent = new Intent(MainActivity.this, TwoActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putString("id", strID);
//        intent.putExtras(bundle);
//
//        intent.putExtra("name", "bbb");
//        intent.putExtra("userInfo", new UserInfo(1, "name"));
//        startActivity(intent);
//
//        //创建一个显式的 Intent 对象(方法二：用 setClass 方法)
//        Intent intent = new Intent();
//        Bundle bundle = new Bundle();
//        bundle.putString("id", strID);
//        intent.setClass(MainActivity.this, TwoActivity.class);
//        intent.putExtras(bundle);
//        startActivity(intent);
//
//        //创建一个显式的 Intent 对象(方法三：用 setClass 方法)
//        Intent intent = new Intent();
//        Bundle bundle = new Bundle();
//        bundle.putString("id", strID);
//        intent.setClassName(MainActivity.this, "com.frain.myapplication.TwoActivity");
//        intent.putExtras(bundle);
//        startActivity(intent);
//    }








    //Intent Action
    public void intentWithAction(Context context) {
        //创建一个隐式的 Intent 对象：Action 动作
        /**
         * 这里指定的是 AndroidManifest.xml 文件中配置的
         * <intent-filter>标签中的<action android:name="com.frain.myapplication.TwoActivity" />
         * 所在的 Activity，注意这里都要设置 <category android:name="android.intent.category.DEFAULT" />
         */
        Intent intent = new Intent();
        //设置 Intent 的动作
        intent.setAction("com.wanglaowu.haha");
//        Bundle bundle = new Bundle();
//        bundle.putString("id", strID);
//        intent.putExtras(bundle);

        context.startActivity(intent);
    }







      //Category 类别
    public void intentWithCategory(Context context) {
        //创建一个隐式的 Intent 对象：Category 类别
        Intent intent = new Intent();
        intent.setAction("com.wanglaowu.haha");
        /**
         * 不指定 Category 或 只指定 AndroidManifest.xml 文件中 <intent-filter> 标签中配置的任意一个 Category
         * <category android:name="android.intent.category.DEFAULT" /> 除外，就可以访问该 Activity，
         */
        intent.addCategory(Intent.CATEGORY_INFO);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        Bundle bundle = new Bundle();
        bundle.putString("id", strID);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }


    //Component 组件
    public void intentWithComponent(Context context){
        Intent intent = new Intent();
        //setComponent方法的参数：ComponentName
        ComponentName componentName=new ComponentName(context,com.frain.myapplication.TwoActivity.class);
        intent.setComponent(componentName);
        context.startActivity(intent);

//        Intent intent1=new Intent();
//        intent1.setClass(context,com.frain.myapplication.TwoActivity.class);
//        context.startActivity(intent1);
    }

    // Date 数据 跳转
    public void intentWithType(Context context) {
        //创建一个隐式的 Intent 对象，方法四：Date 数据
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);//设置动作，调用系统的view/控件
        //通过文件路径生成一个uri对象
        Uri uri = Uri.parse("file:///storage/emulated/0/kawayi.jpg");
        //注：setData、setDataAndType、setType 这三种方法只能单独使用，不可共用
        //要么单独以 setData 方法设置 URI
        //intent.setData(uri);
        //要么单独以 setDataAndType 方法设置 URI 及 mime type
        intent.setDataAndType(uri, "image/png");
        //要么单独以 setType 方法设置 Type
        //intent.setType("audio/x-mpeg");
//        Bundle bundle = new Bundle();
//        bundle.putString("id", strID);
//        intent.putExtras(bundle);
        context.startActivity(intent);
    }


















    public void intentWithFlag(Context context) {
        //当在启动其他界面的时候，尽量的加上Intent.FLAG_ACTIVITY_NEW_TASK 标志位
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_DATE_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
//    //调用系统组件
//    //web浏览器
//    Uri uri= Uri.parse("http://www.baidu.com");
//    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//    startActivity(intent);
//
//    //拨打电话-调用拨号程序
//    Uri uri = Uri.parse("tel:15980665805");
//    Intent intent = new Intent(Intent.ACTION_DIAL, uri);
//    startActivity(intent);
//
//    //拨打电话-直接拨打电话
//    //要使用这个必须在配置文件中加入
//    <uses-permission android:name="android.permission.CALL_PHONE"/>
//    Uri uri = Uri.parse("tel:15980665805");
//    Intent intent = new Intent(Intent.ACTION_CALL, uri);
//    startActivity(intent);
//
//    //调用发送短信程序(方法一)
//    Uri uri = Uri.parse("smsto:15980665805");
//    Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
//    intent.putExtra("sms_body", "The SMS text");
//    startActivity(intent);
//
//    //调用发送短信程序(方法二)
//    Intent intent = new Intent(Intent.ACTION_VIEW);
//    intent.putExtra("sms_body", "The SMS text");
//    intent.setType("vnd.android-dir/mms-sms");
//    startActivity(intent);
//
//    //发送彩信
//    Uri uri = Uri.parse("content://media/external/images/media/23");
//    Intent intent = new Intent(Intent.ACTION_SEND);
//    intent.putExtra("sms_body", "some text");
//    intent.putExtra(Intent.EXTRA_STREAM, uri);
//    intent.setType("image/png");
//    startActivity(intent);
//
//    //发送Email(方法一)(要在 Android 手机上才能测试)
//    Uri uri = Uri.parse("mailto:zhangsan@gmail.com");
//    Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
//    startActivity(intent);
//
//    //发送Email(方法二)(要在 Android 手机上才能测试)
//    Intent intent = new Intent(Intent.ACTION_SENDTO);
//    intent.setData(Uri.parse("mailto:zhangsan@gmail.com"));
//    intent.putExtra(Intent.EXTRA_SUBJECT, "这是标题");
//    intent.putExtra(Intent.EXTRA_TEXT, "这是内容");
//    startActivity(intent);
//
//    //发送Email(方法三)(要在 Android 手机上才能测试)
//    Intent intent = new Intent(Intent.ACTION_SEND);
//    intent.putExtra(Intent.EXTRA_EMAIL, "me@abc.com");
//    intent.putExtra(Intent.EXTRA_SUBJECT, "这是标题");
//    intent.putExtra(Intent.EXTRA_TEXT, "这是内容");
//    intent.setType("text/plain");
//    //选择一个邮件客户端
//    startActivity(Intent.createChooser(intent, "Choose Email Client"));
//
//    //发送Email(方法四)(要在 Android 手机上才能测试)
//    Intent intent = new Intent(Intent.ACTION_SEND);
//    //收件人
//    String[] tos = {"to1@abc.com", "to2@abc.com"};
//    //抄送人
//    String[] ccs = {"cc1@abc.com", "cc2@abc.com"};
//    //密送人
//    String[] bcc = {"bcc1@abc.com", "bcc2@abc.com"};
//    intent.putExtra(Intent.EXTRA_EMAIL, tos);
//    intent.putExtra(Intent.EXTRA_CC, ccs);
//    intent.putExtra(Intent.EXTRA_BCC, bcc);
//    intent.putExtra(Intent.EXTRA_SUBJECT, "这是标题");
//    intent.putExtra(Intent.EXTRA_TEXT, "这是内容");
//    intent.setType("message/rfc822");
//    startActivity(Intent.createChooser(intent, "Choose Email Client"));
//
//    //发送Email且发送附件(要在 Android 手机上才能测试)
//    Intent intent = new Intent(Intent.ACTION_SEND);
//    intent.putExtra(Intent.EXTRA_SUBJECT, "The email subject text");
//    intent.putExtra(Intent.EXTRA_STREAM, "file:///sdcard/mp3/醉红颜.mp3");
//    intent.setType("audio/mp3");
//    startActivity(Intent.createChooser(intent, "Choose Email Client"));
//
//    //播放媒体文件(android 对中文名的文件支持不好)
//    Intent intent = new Intent(Intent.ACTION_VIEW);
//    //Uri uri = Uri.parse("file:///sdcard/zhy.mp3");
//    Uri uri = Uri.parse("file:///sdcard/a.mp3");
//    intent.setDataAndType(uri, "audio/mp3");
//    startActivity(intent);
//
//    Uri uri = Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "1");
//    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//    startActivity(intent);
//
//    //音乐选择器
//    //它使用了Intent.ACTION_GET_CONTENT 和 MIME 类型来查找支持 audio/* 的所有 Data Picker，允许用户选择其中之一
//    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//    intent.setType("audio/*");
//    //Intent.createChooser：应用选择器，这个方法创建一个 ACTION_CHOOSER Intent
//    startActivity(Intent.createChooser(intent, "选择音乐"));
//
//    Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT);
//    intent1.setType("audio/*");
//
//    Intent intent2 = new Intent(Intent.ACTION_CHOOSER);
//    intent2.putExtra(Intent.EXTRA_INTENT, intent1);
//    intent2.putExtra(Intent.EXTRA_TITLE, "aaaa");
//    startActivity(intent2);
//
//
//// //设置壁纸
//// Intent intent = new Intent(Intent.ACTION_SET_WALLPAPER);
//// startActivity(Intent.createChooser(intent, "设置壁纸"));
//
//    //卸载APK
//    //fromParts方法
//    //参数1：URI 的 scheme
//    //参数2：包路径
//    //参数3：
//    Uri uri = Uri.fromParts("package", "com.great.activity_intent", null);
//    Intent intent = new Intent(Intent.ACTION_DELETE, uri);
//    startActivity(intent);
//
//    //安装APK(???)
//    Uri uri = Uri.fromParts("package", "com.great.activity_intent", null);
//    Intent intent = new Intent(Intent.ACTION_PACKAGE_ADDED, uri);
//    startActivity(intent);
//
//    //调用搜索
//    Intent intent = new Intent();
//    intent.setAction(Intent.ACTION_WEB_SEARCH);
//    intent.putExtra(SearchManager.QUERY, "android");
//    startActivity(intent);
}
