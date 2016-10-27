package com.frain.myapplication.ReviewFourthProject.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import com.frain.myapplication.R;

/**
 * Created by admin on 2016/10/26.
 */
public class NewsDetailsActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        Intent intent=getIntent();
        if(intent.getExtras()!=null){
            if(intent.getExtras().getString("key")!=null){
                String str=intent.getExtras().getString("key");
                Log.i("str",""+str);
            }
        }
    }
}
