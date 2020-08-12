package com.example.musicalarm;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class AlarmList extends AppCompatActivity {

    private ListView list;
    ArrayList<Alarm> alarmArrayList;
    MyAdapter myAdapter;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_list);

        list=(ListView)findViewById(R.id.musicList);
        dbHelper=new DBHelper(this);
        alarmArrayList=new ArrayList<>();
        loadDataIntoList();
    }

    private void loadDataIntoList() {
        alarmArrayList=dbHelper.getAllData();

        myAdapter=new MyAdapter(this,alarmArrayList);
        list.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }

    public void add(View view) {
        startActivity(new Intent(AlarmList.this,MainActivity.class));
    }

}
