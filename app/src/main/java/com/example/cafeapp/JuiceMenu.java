package com.example.cafeapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class JuiceMenu extends AppCompatActivity
{
    int jmenuquantity = 0;          //쥬스 메뉴 선택 수

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        final ListView listView = findViewById(R.id.list_view);
        final SharedPreferences sharejuice = getSharedPreferences("cart", MODE_WORLD_READABLE|MODE_WORLD_WRITEABLE);
        final SharedPreferences.Editor editor = sharejuice.edit();

        final Order j1 = new Order(R.drawable.jchocolatecreamfurapuccino, "초콜렛 크림 프라푸치노", "ChocoCreamF", 0, 5100);
        final Order j2 = new Order(R.drawable.jwhitechocolatefurapuccino, "화이트 초코 프라푸치노", "WhiteChocoF", 0, 5700);
        final Order j3 = new Order(R.drawable.jgreentea, "아이스 그린 티", "GreenTea", 0, 5900);
        final Order j4 = new Order(R.drawable.jlemonminttea, "레몬 민트 티", "LemonMintTea", 0, 5600);
        final Order j5 = new Order(R.drawable.jmangoblended, "망고 블랜디드", "MangoBlended", 0, 5000);
        final Order j6 = new Order(R.drawable.jpinkjamongpizio, "핑크 자몽 피지오", "PJamongPizio", 0, 6300);
        final Order j7 = new Order(R.drawable.jstrawberryyogurtblended, "딸기 요거트 블랜디드", "SBYogurBlend", 0, 6100);
        final Order j8 = new Order(R.drawable.jabocadoblended, "아보카도 블랜디드", "AbocadoBlend", 0, 6900);
        final Order[] juice = {j1,j2,j3,j4,j5,j6,j7,j8};


        TextView textCoffeeTotal = findViewById(R.id.coffeetotalcost);                                  //선택한 쥬스 메뉴 총 금액
        final Intent basket = new Intent(JuiceMenu.this, ShopBasket.class);

        JuiceCustom adapter = new JuiceCustom(this, R.layout.basket, juice, textCoffeeTotal);       //쥬스 메뉴 표시하기
        listView.setAdapter(adapter);

        Button goCart = findViewById(R.id.goCart);              //장바구니 담기
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

                startActivity(basket);
            }
        });
    }
}
