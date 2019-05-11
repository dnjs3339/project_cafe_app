package com.example.cafeapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import static java.lang.Integer.valueOf;

public class ShopBasket extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_basket);

        SharedPreferences sharepreference = getSharedPreferences("cart", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharepreference.edit();

        Intent intent = getIntent();

        int cmenuquantity = intent.getExtras().getInt("cmenuquan");
        int jmenuquantity = intent.getExtras().getInt("jmenuquan");
        int fmenuquantity = intent.getExtras().getInt("fmenuquan");

        Shop[] total = new Shop[cmenuquantity + jmenuquantity + fmenuquantity];            //배열 초기화

        //장바구니에 넣은 물건들 보여주기
        // 관련 c : 커피, j : 음료, f : 음식 으로 묶고
        // 순서는 이미지, 메뉴, 수량, 해당

        for(int i = 0; i < cmenuquantity; i++)
        {
            byte[] arr = getIntent().getByteArrayExtra("coffeeimg");
            Bitmap coffeeimg = BitmapFactory.decodeByteArray(arr, 0, arr.length);
            ImageView BigImage = findViewById(R.id.cimg);
           // BigImage.setImageBitmap(coffeeimg);
            total[i].name = intent.getExtras().getString("coffeename" + String.valueOf(i));
            total[i].quantity = intent.getExtras().getInt("coffeequantity" + String.valueOf(i));
        }

        for(int i = cmenuquantity; i < cmenuquantity + jmenuquantity; i++)
        {
            total[i].img = intent.getExtras().getInt("juiceimg");
            total[i].name = intent.getExtras().getString("juicename");
            total[i].quantity = intent.getExtras().getInt("juicequantity");
        }

        for(int i = cmenuquantity + jmenuquantity; i < cmenuquantity + jmenuquantity + fmenuquantity; i++)
        {
            total[i].img = intent.getExtras().getInt("foodimg" + String.valueOf(i));
            total[i].name = intent.getExtras().getString("foodname" + String.valueOf(i));
            total[i].quantity = intent.getExtras().getInt("foodquantity" + String.valueOf(i));
        }

      /*  int jimg = intent.getExtras().getInt("juiceimg");
        String jname = intent.getExtras().getString("juicename");
        int jquantity = intent.getExtras().getInt("juicequantity");


        int fimg = intent.getExtras().getInt("foodimg");
        String fname = intent.getExtras().getString("foodname");
        int fquantity = intent.getExtras().getInt("foodquantity");*/


        // 커피 장바구니에 집어 넣기
        /*for(int i = 0; i < cmenuquantity; i++)
        {
            total[i] = new Shop(cimg, cname, cquantity);
            editor.putInt("cimage", total[i].img);
            editor.putString("cname", total[i].name);
            editor.putInt("cquantity", total[i].quantity);
            editor.commit();
        }

        // 음료 장바구니에 집어 넣기
        for(int i = cmenuquantity; i < cmenuquantity +jmenuquantity; i++)
        {
            total[i] = new Shop(jimg, jname, jquantity);
            editor.putInt("jimage", total[i].img);
            editor.putString("jname", total[i].name);
            editor.putInt("jquantity", total[i].quantity);
            editor.commit();
        }

        // 음식 장바구니에 집어 넣기 및
        for(int i = cmenuquantity + jmenuquantity; i < cmenuquantity + jmenuquantity + fmenuquantity; i++)
        {
            total[i] = new Shop(fimg, fname, fquantity);
            editor.putInt("fimage", total[i].img);
            editor.putString("fname", total[i].name);
            editor.putInt("fquantity", total[i].quantity);
            editor.commit();
        }
*/
        //리스트 불러오기
        ListView listView = findViewById(R.id.list_view);
        FinalOrder order = new FinalOrder(ShopBasket.this, R.layout.shoppingcart, total);
        listView.setAdapter(order);

    }
}