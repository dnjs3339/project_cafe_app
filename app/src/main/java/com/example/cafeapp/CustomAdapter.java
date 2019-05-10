package com.example.cafeapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

class CustomAdapter extends BaseAdapter
{
    Menu  ma;
    int layout;
    Shop[] data;
    CustomAdapter(Menu ma, int layout, Shop[] datas)
    {
        this.ma = ma;
        this.layout = layout;
        this.data = datas;
    }

    @Override
    public int getCount()
    {
        return data.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = ma.getLayoutInflater();
        View view = inflater.inflate(layout, null);

        //버튼 누르면 박스 안 숫자 바꿔주기
        final Shop currentShop = data[position];
        final EditText quantity = view.findViewById(R.id.num);

        Button btnUp = view.findViewById(R.id.up);
        btnUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                currentShop.quantity++;
                quantity.setText(String.valueOf(currentShop.quantity));
            }
        });

        Button btnDown = view.findViewById(R.id.down);
        btnDown.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(0 < currentShop.quantity)
                {
                    currentShop.quantity--;
                    quantity.setText(String.valueOf(currentShop.quantity));
                }
            }
        });

        //리스트별 이미지 바꿔주기
        ImageView img = view.findViewById(R.id.coffee);
        img.setImageResource(data[position].img);

        //리스트별 이름 바꿔주기
        TextView textView = view.findViewById(R.id.coffeeName);
        textView.setText(data[position].name);

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