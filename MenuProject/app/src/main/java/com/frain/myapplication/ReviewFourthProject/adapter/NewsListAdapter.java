package com.frain.myapplication.ReviewFourthProject.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.frain.myapplication.R;
import com.frain.myapplication.ReviewFourthProject.model.NewsList;

import java.util.List;

/**
 * Created by admin on 2016/10/26.
 */
public class NewsListAdapter extends BaseAdapter {
    List<NewsList> lists;
    LayoutInflater layoutInflater;
    Context context;
    View view1;
    View view2;
    public NewsListAdapter(Context context, List<NewsList> lists) {
        this.lists = lists;
        this.context=context;
        layoutInflater = LayoutInflater.from(context);
        view1=layoutInflater.inflate(R.layout.listview_item_news_list_type_0,null);
        view2=layoutInflater.inflate(R.layout.listview_item_news_list_type_1,null);
    }
    public void setData(List<NewsList> lists){
        this.lists = lists;
        this.notifyDataSetChanged();//刷新ListView
    }
    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int i) {
        return lists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        NewsList newsList=lists.get(i);

        ImageView imageView;
        switch (newsList.getType()){
            case 0:
                view=view1;
                imageView=(ImageView)view.findViewById(R.id.title_imageview);
                imageView.setVisibility(View.GONE);
                break;
            case 1:
                view=view1;
                imageView=(ImageView)view.findViewById(R.id.title_imageview);
                imageView.setVisibility(View.VISIBLE);
                if(newsList.getImages()!=null && newsList.getImages().length>0){
                    int[] images=newsList.getImages();
                    imageView.setImageResource(images[0]);
                }
                break;
            case 2:
                view=view2;
                //加载三张图片
                break;
        }
        return view;
    }
}
