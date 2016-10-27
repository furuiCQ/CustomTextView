package com.frain.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.frain.myapplication.Intent.model.Person;

public class MainActivity extends Activity {
    Button goBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {//不可见。不可交互的
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MainActivity","onCreate");
        goBtn = (Button) findViewById(R.id.go_btn);

        Intent intent=getIntent();
        if(intent.getExtras()!=null){
            Bundle bundle=intent.getExtras();
            Person person=(Person)bundle.getSerializable("PersonData");
            if (person!=null){
                Log.i("MainActivity","person.getName===="+person.getName());
            }
        }


        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //创建一个intent对象，里面包含当前页面，目的页面的信息
                Intent intent = new Intent(MainActivity.this, TwoActivity.class);
                startActivity(intent);
            }
        });
    }


}
