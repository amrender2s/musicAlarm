package com.example.musicalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Calendar calendar;
    EditText editText;
    Intent i,getMusic;
    public String uri;
    String path;

    TimePicker timePicker;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=(EditText)findViewById(R.id.location);
        calendar = Calendar.getInstance();
        timePicker = (TimePicker) findViewById(R.id.timepicker);

        dbHelper=new DBHelper(this);

    }
    public void display(View v){
        startActivity(new Intent(MainActivity.this,AlarmList.class));
    }

    public void btn(View view) {
        int min = timePicker.getCurrentMinute();
        int hr = timePicker.getCurrentHour();

        calendar.set(Calendar.HOUR_OF_DAY,hr);
        calendar.set(Calendar.MINUTE,min);
        calendar.set(Calendar.SECOND,0);

        boolean result=dbHelper.insertData(calendar.getTime().toString(),editText.getText().toString());
        if(result)
            Toast.makeText(MainActivity.this,"Data is successfully added into database",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivity.this,"Data not successfully added into database",Toast.LENGTH_SHORT).show();

        setAlarm(calendar.getTimeInMillis());
    }

    private void setAlarm(long timeInMillis) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        i = new Intent(MainActivity.this, Receiver.class);
        i.putExtra("pos",uri);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, i, 0);
        alarmManager.set(AlarmManager.RTC, timeInMillis, pendingIntent);
    }

    public void musicList(View view) {
        getMusic=new Intent(Intent.ACTION_GET_CONTENT);
        getMusic.setType("audio/mpeg");
        startActivityForResult(getMusic,10);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 10) {
            if(resultCode == RESULT_OK){
                path=data.getData().getPath();
                editText.setText(path);
                uri=data.getData().toString();
            }
        }
    }


}


