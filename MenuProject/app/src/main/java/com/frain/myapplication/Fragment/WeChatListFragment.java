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
public class WeChatListFragment extends Fragment{
    ListView listView;
    TextView textView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                                                                                //保存数据状态的bundle
        View view=inflater.inflate(R.layout.fragment_wechat_list,null);
        listView=(ListView)view.findViewById(R.id.listview);
        textView=(TextView)view.findViewById(R.id.title_textview);
        textView.setText("WeChatListFragment");
        listView.setAdapter(new ArrayAdapter<String>(getActivity(),//上下文【在Fragment自然就是getActivity】
                android.R.layout.simple_list_item_1,//
                new String[]{"王老五","王老五","王老五","王老五","王老五","王老五","王老五",
                "王老五","王老五","王老五","王老五","王老五","王老五","王老五","王老五","王老五"}));
        return view;
    }
}
