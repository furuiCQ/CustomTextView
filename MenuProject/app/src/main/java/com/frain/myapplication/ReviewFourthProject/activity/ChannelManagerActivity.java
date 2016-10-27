package com.frain.myapplication.ReviewFourthProject.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.frain.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/10/27.
 */
public class ChannelManagerActivity extends Activity{
    GridView deleteGridView;
    GridView addGridView;
    List<String> deleteList;
    List<String> addList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_manager);
        initData();
        deleteGridView=(GridView)findViewById(R.id.delete_gridview);
        addGridView=(GridView)findViewById(R.id.add_gridview);


        deleteGridView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,deleteList));
        addGridView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,addList));
    }
    public void initData(){
        deleteList=new ArrayList<String>();
        addList=new ArrayList<String>();

        deleteList.add("推荐");
        deleteList.add("社会");
        deleteList.add("娱乐");
        deleteList.add("科技");
        deleteList.add("汽车");
        deleteList.add("体育");
        deleteList.add("财经");
        deleteList.add("军事");
        deleteList.add("国际");
        deleteList.add("时尚");
        deleteList.add("游戏");
        deleteList.add("焦点");
        addList.add("旅游");
        addList.add("历史");
        addList.add("探索");
        addList.add("美食");
        addList.add("育儿");
        addList.add("养生");
        addList.add("故事");
        addList.add("美文");
    }
}
