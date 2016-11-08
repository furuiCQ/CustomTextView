package com.frain.myapplication.SharePreference;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.frain.myapplication.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by admin on 2016/11/8.
 */
public class BeiWangLuActivity extends Activity{
    TextView addTextView;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beiwanglu);
        addTextView=(TextView)findViewById(R.id.add_textview);
        listView=(ListView)findViewById(R.id.listview);
        addTextView.setOnClickListener(clickListener);
        getData();
    }
    View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.add_textview:
                    Intent intent=new Intent(
                            BeiWangLuActivity.this,BeiWangLuEditActivity.class);
                    startActivityForResult(intent,1001);//请求码为1001
                    break;
            }
        }
    };
    HashSet<String> timeListSet=new HashSet<String>();
    List<BeiWang> list=new ArrayList<BeiWang>();
    public void getData(){
        //获得sp对象
        SharedPreferences sp=getSharedPreferences("BeiWangLu", Context.MODE_PRIVATE);
        HashSet<String> defaultSet=new HashSet<String>();//创建默认set集合供取值使用
        //如果没取到key对应的value值则返回默认对象
        timeListSet= (HashSet<String>) sp.getStringSet("time_list",defaultSet);
        //Collections使用该类进行降序的形式整理数据
        if(timeListSet.size()>0){//如果缓存中有数据进行显示数据的操作
            String[] timeArray=new String[timeListSet.size()];
            timeArray=timeListSet.toArray(timeArray);//将set集合转换成String数组
            for (int i=0;i<timeArray.length;i++){//循环添加数据到list集合
                BeiWang beiWang=new BeiWang();
                beiWang.setTime(timeArray[i]);
                String content= sp.getString(timeArray[i],"");
                beiWang.setContent(content);
                list.add(beiWang);
            }
            //创建带有数据的适配器对象
            BeiWangLuListAdapter  beiWangLuListAdapter=new BeiWangLuListAdapter(this,list);
            listView.setAdapter(beiWangLuListAdapter);//listview设置适配器
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1001 && resultCode==1002){
            //刷新列表的操作
            getData();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //Set<String>
    //取出sp保存的信息吧。并以列表的形式展示出来
    //删除指定某条备忘录
    //根据搜索框输入的内容，进行数据筛选
  //  HashSet<String> sets=new HashSet<String>();
    //思考Map key-value     2016/11/8-----09:38 你好  08:36 你好
    // key - hashSet  time--内容
    // key - 时间
    //适配器要适用的是
    public class BeiWangLuListAdapter extends BaseAdapter{
        Context context;
        List<BeiWang> list;
        LayoutInflater layoutInflater;
        public BeiWangLuListAdapter(Context context, List<BeiWang> list){
            this.context=context;
            this.list=list;
            layoutInflater=LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if(view==null){
                viewHolder=new ViewHolder();
                view=layoutInflater.inflate(R.layout.listview_item_beiwanglu,null);
                viewHolder.timeTextView=(TextView)view.findViewById(R.id.time_textview);
                viewHolder.contentTextView=(TextView)view.findViewById(R.id.title_textview);
                view.setTag(viewHolder);
            }
            viewHolder=(ViewHolder) view.getTag();
            BeiWang beiWang=list.get(i);
            //增加一个时间的判断，如果时间为当天则显示时分。
            // 如果为当年显示月日
            //否则显示年月日
            viewHolder.timeTextView.setText(beiWang.getTime());
            String content=beiWang.getContent();
            if(content.length()>10){//内容简要
                content=content.substring(0,10);//如果内容过大截取前10个字符
            }
            viewHolder.contentTextView.setText(content);
            return view;
        }
        public class ViewHolder{
            TextView timeTextView;
            TextView contentTextView;
        }
    }


    public class BeiWang{
        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        String time;
        String  content;
    }
}
