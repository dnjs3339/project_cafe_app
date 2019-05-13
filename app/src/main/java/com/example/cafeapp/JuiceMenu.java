package com.example.cafeapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class JuiceMenu extends AppCompatActivity
{
    int jmenuquantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        final ListView listView = findViewById(R.id.list_view);
        final SharedPreferences sharepreference = getSharedPreferences("cart", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharepreference.edit();

        final Shop j1 = new Shop(R.drawable.jchocolatecreamfurapuccino, "초콜렛 크림 프라푸치노", 0);
        final Shop j2 = new Shop(R.drawable.jwhitechocolatefurapuccino, "화이트 초코 프라푸치노", 0);
        final Shop j3 = new Shop(R.drawable.jgreentea, "아이스 그린 티", 0);
        final Shop j4 = new Shop(R.drawable.jlemonminttea, "레몬 민트 차", 0);
        final Shop j5 = new Shop(R.drawable.jmangoblended, "망고 블랜디드", 0);
        final Shop j6 = new Shop(R.drawable.jpinkjamongpizio, "핑크 자몽 피지오", 0);
        final Shop j7 = new Shop(R.drawable.jstrawberryyogurtblended, "딸기 요거트 블랜디드", 0);
        final Shop j8 = new Shop(R.drawable.jabocadoblended, "아보카도 블랜디드", 0);
        final Shop[] juice = {j1,j2,j3,j4,j5,j6,j7,j8};

        final Intent basket = new Intent(JuiceMenu.this, ShopBasket.class);

        JuiceCustom adapter = new JuiceCustom(this, R.layout.basket, juice);
        listView.setAdapter(adapter);

        Button goCart = findViewById(R.id.goCart);
        goCart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                jmenuquantity = 0;
                for(int i = 0; i < juice.length; i++)
                {
                    if(juice[i].quantity != 0)
                    {
                        editor.putInt("juice" + jmenuquantity + "", i);
                        editor.putInt("juicequantity" + jmenuquantity + "", juice[i].quantity);
                        jmenuquantity++;
                    }
                }

                editor.putInt("jq", jmenuquantity);
                editor.commit();

                if(jmenuquantity == 0)
                {
                    editor.clear();
                    editor.commit();
                }
                startActivity(basket);
            }
        });

    }
}
