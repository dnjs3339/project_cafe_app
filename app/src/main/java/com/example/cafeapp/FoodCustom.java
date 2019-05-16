package com.example.cafeapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodCustom extends BaseAdapter                 //디저트 기본 리스트
{
    FoodMenu  fm;
    int layout;
    Order[] data;
    TextView textCoffee;

    FoodCustom(FoodMenu fm, int layout, Order[] datas, TextView textCoffee)         //텍스트 뷰에는 총 금액이 나올 예정
    {
        this.fm = fm;
        this.layout = layout;
        this.data = datas;
        this.textCoffee = textCoffee;
    }

    @Override
    public int getCount()
    {
        return data.length;     //데이터의 길이만큼 반복
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = fm.getLayoutInflater();
        View view = inflater.inflate(layout, null);

        //버튼 누르면 박스 안 숫자 바꿔주기
        final Order currentShop = data[position];
        final TextView quantity = view.findViewById(R.id.num);

        Button btnUp = view.findViewById(R.id.up);                  //up버튼 누르면 수량 증가
        btnUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int total = Integer.parseInt(textCoffee.getText().toString());
                currentShop.quantity++;
                quantity.setText(String.valueOf(currentShop.quantity));
                total += currentShop.cost;
                textCoffee.setText(String.valueOf(total));
            }
        });

        final Button btnDown = view.findViewById(R.id.down);        //down버튼 누르면 수량 감소
        btnDown.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(0 < currentShop.quantity)
                {
                    int total = Integer.parseInt(textCoffee.getText().toString());
                    currentShop.quantity--;
                    quantity.setText(String.valueOf(currentShop.quantity));
                    total -= currentShop.cost;
                    textCoffee.setText(String.valueOf(total));
                }
            }
        });

        //리스트별 이미지 바꿔주기
        ImageView img = view.findViewById(R.id.coffee);
        img.setImageResource(data[position].img);

        //리스트별 이름 바꿔주기
        TextView textView = view.findViewById(R.id.coffeeName);
        textView.setText(data[position].name);

        //음식 부분에서 내야 할 총 금액
        TextView costView = view.findViewById(R.id.coffeecost);
        costView.setText(String.valueOf(data[position].cost));

        return view;
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
