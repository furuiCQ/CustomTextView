package com.frain.myapplication.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.frain.myapplication.R;

/**
 * Created by admin on 2016/10/31.
 */
public class FoundFragment extends Fragment{
    TextView textView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //保存数据状态的bundle
        View view=inflater.inflate(R.layout.fragment_wechat_list,null);
        textView=(TextView)view.findViewById(R.id.title_textview);
        textView.setText("FoundFragment");

        return view;
    }
}
