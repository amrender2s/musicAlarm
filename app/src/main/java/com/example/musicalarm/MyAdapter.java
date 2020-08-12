package com.example.musicalarm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    Context context;
    ArrayList<Alarm> arrayList;

    public MyAdapter(Context context, ArrayList<Alarm> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    MyAdapter(){}
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.custom_list,null);

            TextView path=(TextView)convertView.findViewById(R.id.pathh);
            TextView time=(TextView)convertView.findViewById(R.id.timee);
            path.setText(arrayList.get(position).getPath());
            time.setText(arrayList.get(position).getTime());
        }
        return convertView;
    }
}
