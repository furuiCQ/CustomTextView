package com.frain.myapplication.ReviewFourthProject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.frain.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/10/26.
 */
public class HorizontalListViewAdapter extends BaseAdapter {
    Context context;
    String []lists;
    List<Boolean> booleanList=new ArrayList<Boolean>();
    LayoutInflater layoutInflater;
    public HorizontalListViewAdapter(Context context, String []lists){
        this.context=context;
        this.lists=lists;
        layoutInflater=LayoutInflater.from(context);
        for(int i=0;i<lists.length;i++){
            if(i==0){
                booleanList.add(true);
            }
            booleanList.add(false);
        }
    }
    public void setData( List<Boolean> booleanList){
        this.booleanList=booleanList;
        this.notifyDataSetChanged();//数据源更改以后刷新ListView
    }
    @Override
    public int getCount() {
        return lists.length;
    }

    @Override
    public Object getItem(int i) {
        return lists[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view=layoutInflater.inflate(R.layout.title_bar_item,null);
        }
        TextView textView=(TextView)view.findViewById(R.id.textview);
        textView.setText(lists[i]);
        if(booleanList.get(i)==false){
            textView.setTextColor(context.getResources().getColor(android.R.color.black));
        }else{
            textView.setTextColor(context.getResources().getColor(android.R.color.holo_red_light));
        }


        return view;
    }
}
