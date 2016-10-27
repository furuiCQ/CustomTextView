package com.frain.myapplication.Intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.frain.myapplication.Intent.model.Person;
import com.frain.myapplication.MainActivity;
import com.frain.myapplication.R;

import java.io.Serializable;

/**
 * Created by admin on 2016/10/25.
 */
public class IntentActivity extends Activity{
    ListView listView;
    IntentType intentType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
        listView=(ListView)findViewById(R.id.listview);
        intentType=new IntentType();

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
                new String[]{"Action","Categroy","Type","Component","Flags","Extras"});
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        intentType.intentWithAction(IntentActivity.this);
                        break;
                    case 1:
                        intentType.intentWithCategory(IntentActivity.this);
                        break;
                    case 2:
                        intentType.intentWithType(IntentActivity.this);
                        break;
                    case 3:
                        intentType.intentWithComponent(IntentActivity.this);
                        break;
                    case 4:
                        intentType.intentWithFlag(IntentActivity.this);
                        break;
                    case 5:
                        Intent intent=new Intent(IntentActivity.this, MainActivity.class);
                        Bundle bundle=new Bundle();
                        Person person=new Person();
                        person.setName("王宝强");
                        person.setAge(42);
                        bundle.putSerializable("PersonData",person);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                }
            }
        });
    }


}
