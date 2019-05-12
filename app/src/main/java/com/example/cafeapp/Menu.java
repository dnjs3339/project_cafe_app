package com.example.cafeapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
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

import java.io.ByteArrayOutputStream;

public class Menu extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);


        //리스트 불러오기
        final ListView listView = findViewById(R.id.list_view);


        Button goCart = findViewById(R.id.goCart);
        goCart.setOnClickListener(new View.OnClickListener()
        {

            Intent basket = new Intent(Menu.this, ShopBasket.class);
            @Override
            public void onClick(View v)
            {
               // Intent basketimg = new Intent(Menu.this, FinalOrder.class);

                // 주문할 물건 장바구니로 넣어주기


                int jmenuquantity = 0;
                int fmenuquantity = 0;



                /*for(int i = 0; i < food.length; i++)
                {
                    if(food[i].quantity != 0)
                    {


                        basket.putExtra("food" + fmenuquantity + "", i);
                        basket.putExtra("foodquantity" + fmenuquantity + "", food[i].quantity);

                        fmenuquantity++;
                    }
                }*/

                //총 메뉴 숫자

                basket.putExtra("jmenuquan", jmenuquantity);
                basket.putExtra("fmenuquan", fmenuquantity);

               startActivity(basket);
            }
        });
    }
}