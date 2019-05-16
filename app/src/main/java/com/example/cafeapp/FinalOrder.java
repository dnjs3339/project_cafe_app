package com.example.cafeapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

public class FinalOrder extends BaseAdapter     //장바구니를 지정된 layout list로 설정
{
    public ShopBasket sb;
    public int layout;
    public Order[] data;

    public
    FinalOrder(ShopBasket sb, int layout, Order[] data)         //해당 data는 total이 들어갈 예정
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
        ImageView img = view.findViewById(R.id.cimg);           //total image setting
        img.setImageResource(data[position].img);
        //img.setImageBitmap(data[position].getImg());

        //리스트별 이름 바꿔주기
        TextView textView = view.findViewById(R.id.cname);      //total menu-name setting
        textView.setText(data[position].name);

        TextView textView1 = view.findViewById(R.id.cquan);     //total quantity setting
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
