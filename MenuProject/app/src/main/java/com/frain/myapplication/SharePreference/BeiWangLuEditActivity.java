package com.frain.myapplication.SharePreference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.frain.myapplication.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by admin on 2016/11/8.
 */
public class BeiWangLuEditActivity extends Activity{
    EditText editText;
    TextView saveTextView;
    TextView cancelTextView;
    TextView  timeTextview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beiwanglu_edit);
        editText=(EditText)findViewById(R.id.edit_text);
        saveTextView=(TextView)findViewById(R.id.save_textview);
        cancelTextView=(TextView)findViewById(R.id.cancel_textview);
        timeTextview=(TextView)findViewById(R.id.time_textview);
        saveTextView.setOnClickListener(clickListener);
        cancelTextView.setOnClickListener(clickListener);
        showNowTime();
    }
    public void showNowTime(){
        Date date=new Date();//获得一个Date对象，并有当前的时间戳
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        String showTime=simpleDateFormat.format(date);
        timeTextview.setText(showTime);
    }
    View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.save_textview:
                    save(editText.getText().toString());
                    setResult(1002,getIntent());//返回码1002，通知首页列表进行刷新
                    finish();
                    break;
                case R.id.cancel_textview:
                    setResult(1000,getIntent());
                    finish();
                    break;
            }
        }
    };
    HashSet<String> timeListSet=new HashSet<String>();
    //保存新编辑的备忘录内容
    public void save(String content){
        SharedPreferences sp=getSharedPreferences("BeiWangLu", Context.MODE_PRIVATE);
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String time=simpleDateFormat.format(date);
        HashSet<String> defaultSet=new HashSet<String>();
        //如果没取到key对应的value值则返回默认对象
        timeListSet= (HashSet<String>) sp.getStringSet("time_list",defaultSet);
        SharedPreferences.Editor editor=sp.edit();//编辑操作类
        timeListSet.add(time);//往取出来的集合中添加时间数据
        //首先要将数据的时间保存下来，
        editor.putStringSet("time_list",timeListSet);//覆盖之前时间列表的数据内容
        //然后再将时间对应的内容保存下来
        editor.putString(time,content);//将时间和内容做为key-value的形式存储在缓存中
        boolean isSuccess=editor.commit();//提交编辑操作
        if(isSuccess){
            Toast.makeText(this,"保存成功",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"保存失败",Toast.LENGTH_LONG).show();
        }
    }
//    <map>
    //    <set name="time_list">
            //    <string>2016/11/08 02:32:50</string>
            //    <string>2016/11/08 02:32:31</string>
            //    <string>2016/11/08 02:31:18</string>
    //    </set>
//    <string name="2016/11/08 02:32:31">213113</string>
//    <string name="2016/11/08 02:32:50">213113</string>
//    <string name="2016/11/08 02:31:18">213113</string>
//    </map>

    //修改已经有的备忘录的内容
    //1.删除time_list中对应的该条时间数据
    //2.删除对应时间对应的这条数据
    //3.保存现有的时间以及对应的数据
}
