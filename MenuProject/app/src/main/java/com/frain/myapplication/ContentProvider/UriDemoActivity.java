package com.frain.myapplication.ContentProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.frain.myapplication.R;

/**
 * Created by admin on 2016/11/10.
 */
public class UriDemoActivity extends Activity {
    String[] button_texts = {"openWeb", "getContactsData","insert Data","update Data","delete Data"};
    LinearLayout rootLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uri_demo);
        rootLinearLayout = (LinearLayout) findViewById(R.id.root_linearlayout);
        for (int i = 0; i < button_texts.length; i++) {
            Button button = new Button(this);
            button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            button.setId(100 + i);
            button.setText(button_texts[i]);
            button.setOnClickListener(clickListener);
            rootLinearLayout.addView(button);
        }
    }
    long rawContactId;
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case 100:
                    Uri uri = Uri.parse("http://www.baidu.com");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                    break;
                case 101:
                    requestMission();
                    break;
                case 102:
                    rawContactId=ContactUtils.addContact(UriDemoActivity.this,"Lao Wang","18712396312");
                    break;
                case 103:
                    ContactUtils.updataCotact(UriDemoActivity.this,rawContactId);
                    break;
                case 104:
                    ContactUtils.deleteContact(UriDemoActivity.this,rawContactId);
                    break;
            }
        }
    };

    //    //调用系统组件
//    //web浏览器
//    Uri uri= Uri.parse("http://www.baidu.com");
//    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//    startActivity(intent);
//
//    //拨打电话-调用拨号程序
//    Uri uri = Uri.parse("tel:15980665805");
//    Intent intent = new Intent(Intent.ACTION_DIAL, uri);
//    startActivity(intent);
//
//    //拨打电话-直接拨打电话
//    //要使用这个必须在配置文件中加入
//    <uses-permission android:name="android.permission.CALL_PHONE"/>
//    Uri uri = Uri.parse("tel:15980665805");
//    Intent intent = new Intent(Intent.ACTION_CALL, uri);
//    startActivity(intent);
//
//    //调用发送短信程序(方法一)
//    Uri uri = Uri.parse("smsto:15980665805");
//    Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
//    intent.putExtra("sms_body", "The SMS text");
//    startActivity(intent);
    //ContentProvider 内容提供者
    //提供一种应用程序之间共享数据的功能



    public void requestMission() {
        if (Build.VERSION.SDK_INT >= 23) {
            this.requestPermissions(new String[]{
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.WRITE_CONTACTS}, 1001);
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
                                           String[] permissions,
            int[] grantResults) {
        if (requestCode == 1001) {
            ContactUtils.getContactsList(this);
        }
        super.onRequestPermissionsResult(requestCode,
                permissions, grantResults);
    }
}
