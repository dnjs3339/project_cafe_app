package com.example.cafeapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FinalOrder extends BaseAdapter
{
    public ShopBasket sb;
    public int layout;
    public Shop[] data;

    public
    FinalOrder(ShopBasket sb, int layout, Shop[] data)
    {
        this.sb = sb;
        this.layout = layout;
        this.data = data;
    }

    @Override
    public int getCount()
    {
        return data.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = sb.getLayoutInflater();
        View view = inflater.inflate(layout, null);

        //리스트별 이미지 바꿔주기
        ImageView img = view.findViewById(R.id.cimg);
        img.setImageResource(data[position].img);
        //img.setImageBitmap(data[position].getImg());

        //리스트별 이름 바꿔주기
        TextView textView = view.findViewById(R.id.cname);
        textView.setText(data[position].name);

        TextView textView1 = view.findViewById(R.id.cquan);
        textView1.setText(Integer.toString(data[position].quantity));

        return view;
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

}
