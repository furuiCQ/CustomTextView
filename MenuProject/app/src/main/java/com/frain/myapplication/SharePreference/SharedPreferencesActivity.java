package com.frain.myapplication.SharePreference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.frain.myapplication.R;

/**
 * Created by admin on 2016/11/7.
 */
public class SharedPreferencesActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharedpreferrences);
        demoSharedPerferences();
    }
//    1.SharedPrefrences轻量级的存储 缓存
//    在我们的app的安装目录结构下创建一个文件夹，并存储在data/data/包名/shared_prefs的xxx.xml文件中
//    结构为key-value的形式。

    public void demoSharedPerferences(){
       // SharedPreferences sharedPreferences=getSharedPreferences(String name, int mode);
        //int mode Context
        //Context.MODE_PRIVATE  私有模式 除本应用外其他应用都无法使用改缓存文件
       // Context.MODE_WORLD_READABLE  读取权限  其他应用只能读取的模式
       // Context.MODE_WORLD_WRITEABLE  写入/读取权限
        //1.getSharedPerference对象                               //存储数据文件名
        SharedPreferences sharedPreferences=getSharedPreferences("wanglaowu",Context.MODE_PRIVATE);
        //2.使用sp存储数据
        //SharedPreferences.Editor 编辑的类  sp专门用来对于数据进行编辑操作的类
        SharedPreferences.Editor  editor=sharedPreferences.edit();//获得editor的对象
        editor.putString("user_name","furui");
        editor.putLong("user_password",123456789L);
    //    editor.remove(String key)//指定删除某个key值对应的value
        editor.clear();//清楚存储文件中所有的数据
        editor.commit();//提交修改并返回结果是否成功
        editor.apply();//直接提交修改无返回值
        //3.使用sp对象进行缓存的获取
        //sharedPreferences.getString(String key,String defvalue)
                                    //key的值  //默认值 作用：防止万一key对应的value为空或者找不到的话，返回的值
        String user_name=sharedPreferences.getString("user_name","default_name");
        Log.i("user_name",""+user_name);
        //clear清楚所有数据  remove删除指定key值对应数据项
    }
    //1.备忘录【新建项目做】
    //要求：制作一个手机备忘录的app
    //A.可以存储并记录备忘内容+时间点
    //B.可以对备忘录列表的内容进行增加。删除。修改 查询[制作一个输入框。使得列表具备筛选的功能]的操作
    //C.数据要sp存储本地，程序每次进来的时候读取sp里面存储的数据并展示给用户看
}
