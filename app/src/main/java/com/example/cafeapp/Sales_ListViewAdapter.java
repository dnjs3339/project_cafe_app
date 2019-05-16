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

        TextView id = convertView.findViewById(R.id.sales_id);
        TextView title = convertView.findViewById(R.id.sales_title);
        TextView cost = convertView.findViewById(R.id.sales_cost);
        TextView wDate = convertView.findViewById(R.id.sales_w_data);

        id.setText((Integer)list.get(position).get("id") + "");             //구매 순번 표시
        cost.setText((String) list.get(position).get("cost"));              //구매 금액 표시
        title.setText((String)list.get(position).get("title"));             //구매 이름 표시
        wDate.setText((String)list.get(position).get("w_date"));            //구매 날짜 표시
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
