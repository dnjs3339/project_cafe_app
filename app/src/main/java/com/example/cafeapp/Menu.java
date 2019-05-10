package com.example.cafeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Menu extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        //여러 메뉴 세팅
        Shop[] datas = {new Shop(R.drawable.americano, "아메리카노", 0),
                new Shop(R.drawable.cafuchino, "카푸치노", 0),
                new Shop(R.drawable.moca, "모카라떼", 0)};

        //리스트 불러오기
        ListView listView = findViewById(R.id.list_view);
        CustomAdapter adapter = new CustomAdapter(this, R.layout.menu, datas);
        listView.setAdapter(adapter);

        Button goCart = findViewById(R.id.goCart);
        goCart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent basket = new Intent(Menu.this, ShopBasket.class);
                startActivity(basket);
            }
        });
    }
}