package com.frain.myapplication.Async;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.frain.myapplication.Async.Adapter.NewsAdapter;
import com.frain.myapplication.Async.Model.News;
import com.frain.myapplication.R;
import com.frain.myapplication.SharePreference.BeiWangLuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/11/17.
 */
public class AsyncDemoActivity extends Activity {
    String datas[] = {"GET WEIXIN NEWSLIST","START A ASYNCTASK"};
    ListView listView;
    ArrayList<News> lists = new ArrayList<News>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_demo);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.root_linearlayout);
        for (int i = 0; i < datas.length; i++) {
            Button button = new Button(this);
            button.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            button.setText(datas[i]);
            button.setId(1000 + i);
            button.setOnClickListener(clicklistener);
            linearLayout.addView(button);
        }
        listView = new ListView(this);
        listView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        linearLayout.addView(listView);

    }

    View.OnClickListener clicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case 1000:
                    new Thread() {
                        @Override
                        public void run() {
                            getData();
                        }
                    }.start();
                    break;
                case 1001:
                    startAsyncTask();
                    break;
            }
        }
    };

    //android 线程通信
    //1.同步    顺序执行我们的某些任务，如果当前线程队列里面有一个任务正在执行，
    // 下一个任务必须等待上一个任务结束，才能够接着执行
    //2.异步   同事进行很多任务
    //从通信的角度来看
    //同步  某次网络通信等待服务器的反馈才能继续进行下去.
    //例子：客户端发送请求后，必须等服务端返回消息，客户端才能够继续执行剩下的网络请求
    //异步，每次网络通信都无需等待服务器的反馈，可以一直执行
    //例子：客户端可以同时发送多次网络请求，都处于等待服务器反馈的状态
    //方式:
    //Thread+Handler
    public void getData() {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            String httpUrl = "http://apis.baidu.com/txapi/weixin/wxhot?" +
                    "num=10";//Url地址
            URL url = new URL(httpUrl);
            //
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("apikey", "50657b78f0f72e081b5baf70a2dd3648");
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));
                String s;
                while ((s = bufferedReader.readLine()) != null) {
                    stringBuilder.append(s);
                }
                Log.i("data===>", stringBuilder.toString());
                JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                JSONArray jsonArray = jsonObject.getJSONArray("newslist");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    News news = new News();
                    news.setCtime(obj.getString("ctime"));
                    news.setTitile(obj.getString("title"));
                    news.setDescription(obj.getString("description"));
                    news.setPicUrl(obj.getString("picUrl"));
                    news.setUrl(obj.getString("url"));
                    lists.add(news);
                }
                handler.sendEmptyMessage(0);
            } else {
                Log.i("getResponseCode()", "" + httpURLConnection.getResponseCode());
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    Handler handler = new Handler() {//利用Handler更新UI组件
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            NewsAdapter newsAdapter = new NewsAdapter(AsyncDemoActivity.this, lists);
            listView.setAdapter(newsAdapter);
        }
    };
    //AsyncTask
                                            //启动任务的参数，进度参数，结果参数
    public class MyAsyncTask extends AsyncTask<String,Integer,String>{
        @Override
        protected void onPreExecute() {
            //在execute(Params... params)被调用后立即执行，一般用来在执行后台任务前对UI做一些标记。
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {//执行UI操作
            if(s!=null){
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("newslist");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        News news = new News();
                        news.setCtime(obj.getString("ctime"));
                        news.setTitile(obj.getString("title"));
                        news.setDescription(obj.getString("description"));
                        news.setPicUrl(obj.getString("picUrl"));
                        news.setUrl(obj.getString("url"));
                        lists.add(news);
                    }
                    NewsAdapter newsAdapter = new NewsAdapter(AsyncDemoActivity.this, lists);
                    listView.setAdapter(newsAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            super.onPostExecute(s);
        }
        @Override
        protected void onProgressUpdate(Integer... values) {//
            //更新类似于进度条的控件的进度效果
            Log.i("onProgressUpdate",""+values[0]);
            super.onProgressUpdate(values);
        }
        @Override//所有的耗时操作都在此时进行，不能进行UI操作
        protected String doInBackground(String... strings) {//在后台运行的回调方法
            try{
                StringBuilder stringBuilder=new StringBuilder();//可变字符序列
                URL url=new URL(strings[0]);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestProperty("apikey","50657b78f0f72e081b5baf70a2dd3648");
                httpURLConnection.connect();//建立本次网络请求的连接
                if(httpURLConnection.getResponseCode()==HttpURLConnection.HTTP_OK){
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                            httpURLConnection.getInputStream(), "utf-8"));
                    String s;
                    while ((s = bufferedReader.readLine()) != null) {
                        this.publishProgress(s.length());
                        stringBuilder.append(s);
                    }
                    Log.i("data===>", stringBuilder.toString());
                    return stringBuilder.toString();//返回数据给onPostExecute
                }
            }catch (MalformedURLException ex){
                ex.printStackTrace();
            }catch(IOException ex){
                ex.printStackTrace();
            }
            return null;//返回数据给onPostExecute
        }
    }
    public void startAsyncTask(){
        MyAsyncTask myAsyncTask=new MyAsyncTask();
        String httpUrl="http://apis.baidu.com/txapi/weixin/wxhot?num=10";
        myAsyncTask.execute(httpUrl);
    }


}
