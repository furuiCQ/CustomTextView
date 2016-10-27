package com.frain.myapplication.ReviewFourthProject.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.frain.myapplication.R;
import com.frain.myapplication.ReviewFourthProject.adapter.HorizontalListViewAdapter;
import com.frain.myapplication.ReviewFourthProject.adapter.NewsListAdapter;
import com.frain.myapplication.ReviewFourthProject.model.NewsList;
import com.frain.myapplication.ReviewFourthProject.view.HorizontalListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by admin on 2016/10/26.
 */
public class TodayNewsActivity extends Activity {
    ImageView msgImageView;
    ImageView addImageView;
    AlertDialog alertDialog;
    HorizontalListView horizontalListView;
    ListView listView;
    List<NewsList> lists;
    NewsListAdapter newsListAdapter;
    HorizontalListViewAdapter arrayAdapter;
    String[] str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_news_main);
        initView();


    }
    /**
     * 初始化控件
     */
    public void initView(){
        horizontalListView=(HorizontalListView)findViewById(R.id.title_bar_listview);
        listView=(ListView)findViewById(R.id.listview_news_list);
        msgImageView = (ImageView) findViewById(R.id.msg_imageview);
        addImageView=(ImageView)findViewById(R.id.add_imageview);
        creatAlertDialog();
        str=new String[]{"推荐","社会","娱乐","科技","汽车","体育", "财经","军事","国际","时尚","游戏","焦点"};
        arrayAdapter=new HorizontalListViewAdapter(this,
                str);
        horizontalListView.setAdapter(arrayAdapter);

        getNewsListData();
        newsListAdapter= new NewsListAdapter(this,lists);
        listView.setAdapter(newsListAdapter);

        horizontalListView.setOnItemClickListener(itemClickListener);
        listView.setOnItemClickListener(itemClickListener);
        msgImageView.setOnClickListener(clickListener);
        addImageView.setOnClickListener(clickListener);
    }

    AdapterView.OnItemClickListener itemClickListener=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                //被点击AdapterView的对象  //被点击的列表项的view对象
            switch (adapterView.getId()){
                case R.id.title_bar_listview:

                    //横向ListView，内容点击以后改变效果
                    List<Boolean> booleanList=new ArrayList<Boolean>();
                    for(int j=0;j<str.length;j++){
                        if(j==l){//如果选中的id等于集合里面对应的id，就把对应位置设置true
                            booleanList.add(true);
                        }
                        booleanList.add(false);
                    }
                    arrayAdapter.setData(booleanList);
                    //模拟网络请求真实数据
                    //推荐0 社会1 ......
                    getNewsListData();
                    newsListAdapter.setData(lists);
                    break;
                case R.id.listview_news_list:
                    Intent intent=new Intent(TodayNewsActivity.this,NewsDetailsActivity.class);
                    intent.putExtra("","");
                    startActivity(intent);
                    break;
            }
        }
    };

    View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.add_imageview:
//                    Intent intent=new Intent(TodayNewsActivity.this,ChannelManagerActivity.class);
//                    intent.putExtra("title_data",str);
//                    startActivityForResult(intent,1002);
                    break;
                case R.id.msg_imageview:
                    alertDialog.show();
                    break;
            }

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data!=null){
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    public void getNewsListData(){
        lists=new ArrayList<NewsList>();
        for (int i=0;i<15;i++){
            NewsList newsList=new NewsList();
            newsList.setType((int)(3*Math.random()));//0.0--1.0 double 0.0-3.0
            lists.add(newsList);
        }
    }
    /**
     * 创建AlertDialog
     */
    public void creatAlertDialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view=getLayoutInflater().inflate(R.layout.activity_popupwindow_main,null);
            ImageView closBtn=(ImageView)view.findViewById(R.id.close_btn);
            closBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                }
            });
            builder.setView(view);
            alertDialog = builder.create();

    }
}
