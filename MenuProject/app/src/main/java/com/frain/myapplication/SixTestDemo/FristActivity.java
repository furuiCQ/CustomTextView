package com.frain.myapplication.SixTestDemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;

import com.frain.myapplication.MySqlite.MySQliteOpenHelper;
import com.frain.myapplication.R;

/**
 * Created by admin on 2016/11/11.
 */
public class FristActivity extends Activity{
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frist);
        progressBar=(ProgressBar)findViewById(R.id.progressbar);
        startProgress();
    }
    public void startProgress(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while(i<101){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.sendEmptyMessage(i);
                    i++;
                    createNewDatabase(i);
                    if(i==101){
                        i=0;
                    }
                }

            }
        }).start();
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progressBar.setProgress(msg.what);
        }
    };
    public void createNewDatabase(int version){
        MySqliteOpenHelper mySQliteOpenHelper=new MySqliteOpenHelper(
                this,
                "diaryOpenHelper.db",
                null,
                version
        );
        mySQliteOpenHelper.getWritableDatabase();
    }
}
