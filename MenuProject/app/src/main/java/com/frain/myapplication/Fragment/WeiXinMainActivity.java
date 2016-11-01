package com.frain.myapplication.Fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.frain.myapplication.R;

/**
 * Created by admin on 2016/10/27.
 */
public class WeiXinMainActivity extends Activity{
    WeChatListFragment weChatListFragment;
    ContactFragment contactFragment;
    FoundFragment foundFragment;
    MineFragment mineFragment;
    FragmentManager fragmentManager;
    boolean radioBoolean1=true;//作为选中还是未选中状态标示
    boolean radioBoolean2=false;
    boolean radioBoolean3=false;
    boolean radioBoolean4=false;
    RelativeLayout relativeLayout1;
    RelativeLayout relativeLayout2;
    RelativeLayout relativeLayout3;
    RelativeLayout relativeLayout4;

    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weixin_main);
        creatFragment();
        relativeLayout1=(RelativeLayout)findViewById(R.id.realtivelayout1);
        relativeLayout2=(RelativeLayout)findViewById(R.id.realtivelayout2);
        relativeLayout3=(RelativeLayout)findViewById(R.id.realtivelayout3);
        relativeLayout4=(RelativeLayout)findViewById(R.id.realtivelayout4);

        relativeLayout1.setOnClickListener(clickListener);
        relativeLayout2.setOnClickListener(clickListener);
        relativeLayout3.setOnClickListener(clickListener);
        relativeLayout4.setOnClickListener(clickListener);
    }
    View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            switch (view.getId()){
                case R.id.realtivelayout1:
                    fragmentTransaction.hide(contactFragment);
                    fragmentTransaction.hide(foundFragment);
                    fragmentTransaction.hide(mineFragment);
                    fragmentTransaction.show(weChatListFragment);
                    fragmentTransaction.commit();
                    break;
                case R.id.realtivelayout2:
                    fragmentTransaction.hide(weChatListFragment);
                    fragmentTransaction.hide(foundFragment);
                    fragmentTransaction.hide(mineFragment);
                    fragmentTransaction.show(contactFragment);
                    fragmentTransaction.commit();
                    break;
                case R.id.realtivelayout3:
                    fragmentTransaction.hide(weChatListFragment);
                    fragmentTransaction.hide(contactFragment);
                    fragmentTransaction.hide(mineFragment);
                    fragmentTransaction.show(foundFragment);
                    fragmentTransaction.commit();
                    break;
                case R.id.realtivelayout4:
                    fragmentTransaction.hide(weChatListFragment);
                    fragmentTransaction.hide(foundFragment);
                    fragmentTransaction.hide(contactFragment);
                    fragmentTransaction.show(mineFragment);
                    fragmentTransaction.commit();
                    break;
            }
        }
    };
    public void creatFragment(){
        weChatListFragment=new WeChatListFragment();
        contactFragment=new ContactFragment();
        foundFragment=new FoundFragment();
        mineFragment=new MineFragment();

        fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.root_linearlayout,weChatListFragment,"WeChatListFragment");
        fragmentTransaction.add(R.id.root_linearlayout,contactFragment,"ContactFragment");
        fragmentTransaction.add(R.id.root_linearlayout,foundFragment,"FoundFragment");
        fragmentTransaction.add(R.id.root_linearlayout,mineFragment,"MineFragment");

        fragmentTransaction.commit();
    }
}
