package com.frain.myapplication.Async.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.frain.myapplication.Async.Model.News;
import com.frain.myapplication.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by admin on 2016/11/17.
 */
public  class NewsAdapter extends BaseAdapter {
    int i=0;
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<News> list;
    public NewsAdapter(Context context,
                        ArrayList<News> list){
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
        ViewHodler viewHodler=null;
        if(view==null){
            view=layoutInflater.inflate(R.layout.listview_item_news_list_type_0,null);
            viewHodler=new ViewHodler();
            viewHodler.timeTextView=(TextView)view.findViewById(R.id.time_textview);
            viewHodler.titleTextView=(TextView)view.findViewById(R.id.title_textview);
            viewHodler.auterTextView=(TextView)view.findViewById(R.id.author_textview);
            viewHodler.imageView=(ImageView) view.findViewById(R.id.title_imageview);
            view.setTag(viewHodler);
        }
        viewHodler=(ViewHodler) view.getTag();
        final  News news=list.get(i);
        viewHodler.timeTextView.setText(news.getCtime());
        viewHodler.titleTextView.setText(news.getTitile());
        viewHodler.auterTextView.setText(news.getDescription());
        MyHandler myHandler=new MyHandler();
        myHandler.setViewHodler(viewHodler);
        MyThread thread=new MyThread();
        thread.setMyHandler(myHandler);
        thread.setIamgeURL(news.getPicUrl());
        thread.start();
        return view;
    }
    class MyThread extends Thread{
        String iamgeURL;
        public void setMyHandler(MyHandler myHandler) {
            this.myHandler = myHandler;
        }
        MyHandler myHandler;
        public void setIamgeURL(String iamgeURL) {
            this.iamgeURL = iamgeURL;
        }
        @Override
        public void run() {//耗时操作在线程中
            try {
                URL url=new URL(iamgeURL);
                i++;
                Log.i("MyThread===>","请求了第"+i);
                Bitmap bimap=BitmapFactory.decodeStream(url.openStream());
                if(bimap!=null){
                    myHandler.setBitmap(bimap);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            myHandler.sendEmptyMessage(0);

        }
    }
    class MyHandler extends Handler{
        ViewHodler viewHodler;
        public void setBitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
        }
        Bitmap bitmap;
        public void setViewHodler(ViewHodler viewHodler) {
            this.viewHodler = viewHodler;
        }
        @Override
        public void handleMessage(Message msg) {
            if(bitmap!=null) {
                viewHodler.imageView.setImageBitmap(bitmap);
            }

        }
    }
    class ViewHodler{
        TextView timeTextView;
        TextView titleTextView;
        TextView auterTextView;
        ImageView imageView;
    }


}
