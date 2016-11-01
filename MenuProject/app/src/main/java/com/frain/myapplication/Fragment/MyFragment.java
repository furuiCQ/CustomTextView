package com.frain.myapplication.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.frain.myapplication.R;

/**
 * Created by admin on 2016/10/27.
 */
public class MyFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,//视图转换器的对象
                             ViewGroup container,//父控件的对象
                             Bundle savedInstanceState) {//bundle的对象
        Log.i("MyFragment","onCreateView");
        View view=inflater.inflate(R.layout.listview_item_news_list_type_1,null);


        Button nihaoButton=(Button)view.findViewById(R.id.nihao_btn);
        nihaoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getActivity方法在Fragment中才会有，为的就是拿到加载当前Fragment的Activity的对象
//                MainActivity mainActivity=(MainActivity)getActivity();
//                mainActivity.addBtn.setText("你好呀");
            }
        });
        return view;
    }























    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("MyFragment","onCreate");

    }
    @Override
    public void onStart() {
        super.onStart();
        Log.i("MyFragment","onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("MyFragment","onResume");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.i("MyFragment","onPause");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.i("MyFragment","onStop");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("MyFragment","onDestroy");
    }
}
