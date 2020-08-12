package com.example.musicalarm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context,"student.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table alarmList(ID INTEGER PRIMARY KEY AUTOINCREMENT,TIME TEXT,PATH TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists alarmList");
        onCreate(db);
    }
    public boolean insertData(String time , String path){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues  contentValues=new ContentValues();
        contentValues.put("TIME",time);
        contentValues.put("PATH",path);

        long result=db.insert("alarmlist",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

//    public Cursor getAllData(){
//        SQLiteDatabase db=this.getReadableDatabase();
//        Cursor cursor=db.rawQuery("select * from alarmList",null);
//        return cursor;
//    }
    public ArrayList<Alarm> getAllData(){
        ArrayList<Alarm> alarms=new ArrayList<>();
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery("select * from alarmList",null);
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String path=cursor.getString(2);
            String time=cursor.getString(1);
            Alarm alarm=new Alarm(id,time,path);
            alarms.add(alarm);
        }
        return alarms;
    }
}
