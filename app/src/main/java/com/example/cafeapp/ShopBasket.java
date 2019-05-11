package com.example.cafeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class ShopBasket extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        //장바구니에 넣은 물건들 보여주기

        Intent intent = getIntent();

        // 관련 c : 커피, j : 음료, f : 음식 으로 묶고
        // 순서는 이미지, 메뉴, 수량, 해당
        int cimg = intent.getExtras().getInt("coffeeimg");              //이미지 bitmap ㅈㄹ 해야함
        String cname = intent.getExtras().getString("coffeename");
        int cquantity = intent.getExtras().getInt("coffeequantity");
        int cmenuquantity = intent.getExtras().getInt("cmenuquan");

        int jimg = intent.getExtras().getInt("juiceimg");
        String jname = intent.getExtras().getString("juicename");
        int jquantity = intent.getExtras().getInt("juicequantity");
        int jmenuquantity = intent.getExtras().getInt("jmenuquan");

        int fimg = intent.getExtras().getInt("foodimg");
        String fname = intent.getExtras().getString("foodname");
        int fquantity = intent.getExtras().getInt("foodquantity");
        int fmenuquantity = intent.getExtras().getInt("fmenuquan");

        Shop[] total = new Shop[30];

        // 커피 장바구니에 집어 넣기
        for(int i = 0; i < cmenuquantity; i++)
        {
            total[i] = new Shop(cimg, cname, cquantity);
        }

        // 음료 장바구니에 집어 넣기
        for(int i = cmenuquantity; i < cmenuquantity +jmenuquantity; i++)
        {
            total[i] = new Shop(jimg, jname, jquantity);
        }

        // 음식 장바구니에 집어 넣기
        for(int i = cmenuquantity + jmenuquantity; i < cmenuquantity + jmenuquantity + fmenuquantity; i++)
        {
            total[i] = new Shop(fimg, fname, fquantity);
        }

        //리스트 불러오기
        ListView listView = findViewById(R.id.list_view);
        FinalOrder order = new FinalOrder(ShopBasket.this, R.layout.shoppingcart, total);
        listView.setAdapter(order);

    }
}