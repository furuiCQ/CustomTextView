package com.frain.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by admin on 2016/10/24.
 */
public class TwoActivity extends Activity{
    Button goBackBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        goBackBtn=(Button)findViewById(R.id.go_back_btn);
        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //创建一个intent对象，里面包含当前页面，目的页面的信息
                Intent intent=new Intent(TwoActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });
    }


}
