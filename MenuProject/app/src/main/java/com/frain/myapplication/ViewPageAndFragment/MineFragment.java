package com.frain.myapplication.ViewPageAndFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.frain.myapplication.R;

/**
 * Created by admin on 2016/10/31.
 */
public class MineFragment extends Fragment{
    TextView textView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //保存数据状态的bundle
        View view=inflater.inflate(R.layout.fragment_wechat_list,null);
        textView=(TextView)view.findViewById(R.id.title_textview);
        textView.setText("MineFragment");

        return view;
    }
}
