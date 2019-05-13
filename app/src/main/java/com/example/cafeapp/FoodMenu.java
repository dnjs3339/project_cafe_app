package com.example.cafeapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class FoodMenu extends AppCompatActivity
{
    int fmenuquantity = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        final ListView listView = findViewById(R.id.list_view);
        final SharedPreferences sharepreference = getSharedPreferences("cart", MODE_PRIVATE);       // 밑의 것을 쓰기 위해 사용
        final SharedPreferences.Editor editor = sharepreference.edit();                                     //해당 값을 앱에 저장

        final Shop f1 = new Shop(R.drawable.fbagle, "허니 버터 베이글", 0);
        final Shop f2 = new Shop(R.drawable.fcake, "치즈 조각 케이크", 0);
        final Shop f3 = new Shop(R.drawable.fchocolatemurffin, "아몬드 초코 머핀", 0);
        final Shop f4 = new Shop(R.drawable.fcroissant, "녹차 딸기 크로와상", 0);
        final Shop f5 = new Shop(R.drawable.fpuding, "바닐라 푸딩", 0);
        final Shop f6 = new Shop(R.drawable.frole, "소시지 롤", 0);
        final Shop f7 = new Shop(R.drawable.fscone, "클레식 스콘", 0);
        final Shop f8 = new Shop(R.drawable.fstick, "클레식 스틱", 0);
        final Shop[] food ={f1,f2,f3,f4,f5,f6,f7,f8};

        final Intent basket = new Intent(FoodMenu.this, ShopBasket.class);

        FoodCustom adapter = new FoodCustom(this, R.layout.basket, food);
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

                if(fmenuquantity == 0)          //추후에 장바구니 비우기로 옮김
                {
                    editor.clear();
                    editor.commit();
                }
                startActivity(basket);
            }
        });
    }
}
