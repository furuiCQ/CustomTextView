package com.frain.myapplication.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.frain.myapplication.R;

/**
 * Created by admin on 2016/10/27.
 */
public class MainActivity extends Activity{
    Button addBtn;
    Button replaceBtn;
    Button deleteBtn;
    Button hideBtn;
    Button showBtn;
    Button weixinBtn;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    int numb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("MainActivity","onCreate");
        setContentView(R.layout.activity_fragment_main);
        addBtn=(Button)findViewById(R.id.add_button);
        replaceBtn=(Button)findViewById(R.id.replace_button);
        deleteBtn=(Button)findViewById(R.id.delete_button);
        hideBtn=(Button)findViewById(R.id.hide_button);
        weixinBtn=(Button)findViewById(R.id.weixin_btn);
        showBtn=(Button)findViewById(R.id.show_button);
        weixinBtn.setOnClickListener(clickListener);
        showBtn.setOnClickListener(clickListener);
        hideBtn.setOnClickListener(clickListener);
        deleteBtn.setOnClickListener(clickListener);
        replaceBtn.setOnClickListener(clickListener);
        addBtn.setOnClickListener(clickListener);
        initFragment();
    }
    MyFragment fragment;
    public void initFragment(){
        //获得Fragment的管理器对象
        fragmentManager=getFragmentManager();
        //添加Fragment到Activity页面的某个viewGroup内部
    }
    View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.add_button:
                    fragment=new MyFragment();//创建一个Fragment对象
                    fragmentTransaction=fragmentManager.beginTransaction(); //获得新的Fragment的编辑操作的对象fragmentTransaction
                    fragmentTransaction.add(R.id.root_linearlayout,fragment);
                    //  fragmentTransaction.replace(int containerId,Fragment fragment,"wanglaowu")//替换
                    fragmentTransaction.commit();                    //将事务进行提交，产生结果
                    break;
                /**
                 * 注意：fragmentTransaction每次都需要重新去获得，否则将报错commit already called
                 */
                case R.id.replace_button://替换视图中已存在的Fragment：
                    //注意：如果要准确替换，在add时候，设置tag这条属性
                    fragmentTransaction=fragmentManager.beginTransaction();//获得新的Fragment的编辑操作的对象fragmentTransaction
                    PopupWindowFragment popupWindowFragment=new PopupWindowFragment();
                    fragmentTransaction.replace(R.id.root_linearlayout,popupWindowFragment);
                  //fragmentTransaction.replace(R.id.root_linearlayout,popupWindowFragment,"wanglaowu");
                    fragmentTransaction.commit();
                    break;
                case R.id.delete_button://删除视图中已存在的Fragment对象
                    fragmentTransaction=fragmentManager.beginTransaction();
                    fragmentTransaction.remove(fragment);
                    fragmentTransaction.commit();
                    break;
                case R.id.hide_button://隐藏视图中已存在的Fragment的对象
                    fragmentTransaction=fragmentManager.beginTransaction();
                    fragmentTransaction.hide(fragment);
                    fragmentTransaction.commit();
                    break;
                case R.id.show_button://显示出已存在并隐藏的Fragment的对象
                    fragmentTransaction=fragmentManager.beginTransaction();
                    fragmentTransaction.show(fragment);
                    fragmentTransaction.commit();
                    break;
                case R.id.weixin_btn:
                    Intent intent=new Intent(MainActivity.this,WeiXinMainActivity.class);
                    startActivity(intent);
                    break;
            }}};



















    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity","onStart");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("MainActivity","onRestart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity","onResume");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity","onPause");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity","onStop");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MainActivity","onDestroy");
    }
}
