package com.frain.myapplication.MySqlite.AccountsPractice;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.frain.myapplication.R;

import java.util.Date;

/**
 * Created by admin on 2016/11/9.
 */
public class InComeActivity extends Activity {
    Button typeButton;
    Button dateButton;
    Button saveButton;
    EditText addressEdit;
    EditText beizhuEdit;

    Cursor cursorList;
    AlertDialog alertDialog;
    TimePickerDialog timePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        typeButton = (Button) findViewById(R.id.textview_type);
        dateButton = (Button) findViewById(R.id.textview_date);
        saveButton = (Button) findViewById(R.id.textview_save);
        addressEdit = (EditText) findViewById(R.id.edit_address);
        beizhuEdit = (EditText) findViewById(R.id.edit_beizhu);

        typeButton.setOnClickListener(clickListener);
        dateButton.setOnClickListener(clickListener);
        saveButton.setOnClickListener(clickListener);
        initSQliteDataBase();
        getTypeList();

    }

    SQLiteDatabase sqLiteDatabase;

    public void initSQliteDataBase() {
        AccountsSQliteOpenHelper accountsSQliteOpenHelper = new AccountsSQliteOpenHelper(this, "Accounts.db", null, 1);
        sqLiteDatabase = accountsSQliteOpenHelper.getWritableDatabase();
    }


    public void getTypeList() {
        cursorList = sqLiteDatabase.rawQuery("select id as _id,name from income_type", null);
        if (cursorList != null) {
            while (cursorList.moveToNext()) {

                int numb = cursorList.getColumnCount();
                for (int i = 0; i < numb; i++) {
                    String key = cursorList.getColumnName(i);
                    String value = cursorList.getString(i);
                    Log.i("key", "" + key);
                    Log.i("value", "" + value);
                    if (cursorList.getPosition() == 0 && i == 0) {
                        selectId = Integer.parseInt(key);
                        selectTypeName = value;
                    }
                }
            }
            createAlertDialog();
        }

    }

    int selectId;
    String selectTypeName;
    Date date;
    String address;
    String beizhu;
    public void createAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setSingleChoiceItems(cursorList, 0, "name", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                cursorList.moveToPosition(i);
                String value = cursorList.getString(1);
                typeButton.setText(value);
                dialogInterface.dismiss();
                selectTypeName = value;

            }
        });
        alertDialog = builder.create();
        timePickerDialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                Log.i("onTimeSet","hour"+hour+"minute"+minute);
            }
        },new Date().getHours(),new Date().getMinutes(),true);
        timePickerDialog.setButton(0, "完成", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

    }


    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.textview_type:
                    alertDialog.show();
                    break;
                case R.id.textview_date:
                    break;
                case R.id.textview_save:
                    break;

            }
        }
    };


}
