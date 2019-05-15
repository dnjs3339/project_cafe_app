package com.example.cafeapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class Sales_ListViewAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<Map<String, Object>> list;
    LayoutInflater inflater;

    public Sales_ListViewAdapter(Context context, int layout) {
        this.context = context;
        this.layout = layout;
        this.list = new ArrayList<Map<String, Object>>();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = inflater.inflate(layout, null);
        }

        TextView id = (TextView) convertView.findViewById(R.id.sales_id);
        TextView title = (TextView) convertView.findViewById(R.id.sales_title);
        TextView cost = (TextView) convertView.findViewById(R.id.sales_cost);
        TextView wDate = (TextView) convertView.findViewById(R.id.sales_w_data);
        id.setText((Integer)list.get(position).get("id") + "");
        cost.setText((String) list.get(position).get("cost"));
        title.setText((String)list.get(position).get("title"));
        wDate.setText((String)list.get(position).get("w_date"));
        return convertView;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
