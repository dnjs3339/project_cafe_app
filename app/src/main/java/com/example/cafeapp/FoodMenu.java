package com.example.cafeapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class FoodMenu extends AppCompatActivity
{
    int fmenuquantity = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        final ListView listView = findViewById(R.id.list_view);
        final SharedPreferences sharefood = getSharedPreferences("cart", MODE_PRIVATE);       // 밑의 것을 쓰기 위해 사용
        final SharedPreferences.Editor editor = sharefood.edit();                                     //해당 값을 앱에 저장

        final Order f1 = new Order(R.drawable.fbagle, "허니 버터 베이글", "HoneyBagle", 0, 2800);
        final Order f2 = new Order(R.drawable.fcake, "치즈 조각 케이크", "CheeseCake", 0, 5500);
        final Order f3 = new Order(R.drawable.fchocolatemurffin, "아몬드 초코 머핀", "ChocoMarfin", 0, 3300);
        final Order f4 = new Order(R.drawable.fcroissant, "녹차 딸기 크로와상", "SBCroissant", 0, 4900);
        final Order f5 = new Order(R.drawable.fpuding, "바닐라 푸딩", "BanilaPuding", 0, 3300);
        final Order f6 = new Order(R.drawable.frole, "소시지 롤", "SausageRole", 0, 3300);
        final Order f7 = new Order(R.drawable.fscone, "클레식 스콘", "ClassicScone", 0, 3300);
        final Order f8 = new Order(R.drawable.fstick, "클레식 스틱", "ClassicStick", 0, 4900);
        final Order[] food ={f1,f2,f3,f4,f5,f6,f7,f8};

        final Intent basket = new Intent(FoodMenu.this, ShopBasket.class);

        TextView textCoffeeTotal = findViewById(R.id.coffeetotalcost);

        FoodCustom adapter = new FoodCustom(this, R.layout.basket, food, textCoffeeTotal);
        listView.setAdapter(adapter);

        Button goCart = findViewById(R.id.goCart);
        goCart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                fmenuquantity = 0;
                for(int i = 0; i < food.length; i++)
                {
                    if(food[i].quantity != 0)
                    {
                        editor.putInt("food" + fmenuquantity + "", i);                              // 선택한 디저트 종류 넘기기
                        editor.putInt("foodquantity" + fmenuquantity + "", food[i].quantity);       // 선택한 디저트 수량 넘기기
                        fmenuquantity++;
                    }
                }

                editor.putInt("fq", fmenuquantity);                                                 // 선택한 디저트 종류의 수 넘기기
                editor.commit();                // 값 저장하기(안쓰면 저장 안됨)

                startActivity(basket);
            }
        });
    }
}
