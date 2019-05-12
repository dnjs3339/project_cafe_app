package com.example.cafeapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        Bundle bundle = getIntent().getBundleExtra("bundle");


        int cmenuquantity = intent.getExtras().getInt("cmenuquan");
        int jmenuquantity = intent.getExtras().getInt("jmenuquan");
        int fmenuquantity = intent.getExtras().getInt("fmenuquan");

        Shop[] total = new Shop[cmenuquantity + jmenuquantity + fmenuquantity];            //배열 초기화

        final Shop c1 = new Shop(R.drawable.camericano, "아메리카노", 0);
        final Shop c2 = new Shop(R.drawable.ccafemoca, "카페모카", 0);
        final Shop c3 = new Shop(R.drawable.ccapuchino, "카푸치노", 0);
        final Shop c4 = new Shop(R.drawable.ccaramelmacchiato, "캐러멜 마키아또", 0);
        final Shop c5 = new Shop(R.drawable.ccoldbrew, "콜드 브루", 0);
        final Shop c6 = new Shop(R.drawable.cespresso, "에스프레소", 0);
        final Shop c7 = new Shop(R.drawable.cnightrochocolat, "나이트로 쇼콜라", 0);
        final Shop c8 = new Shop(R.drawable.cnightrochocolatcloud, "나이트로 쇼콜라 클라우드", 0);
        final Shop[] coffee ={c1,c2,c3,c4,c5,c6,c7,c8};



        //장바구니에 넣은 물건들 보여주기
        // 관련 c : 커피, j : 음료, f : 음식 으로 묶고
        // 순서는 이미지, 메뉴, 수량, 해당

        for(int i = 0; i < cmenuquantity; i++)
        {
            //total[i] = coffee[intent.getExtras().getInt("coffee")];
            total[i].img = coffee[intent.getExtras().getInt(String.valueOf(i))].img;
            total[i].name = coffee[intent.getExtras().getInt(String.valueOf(i))].name;
            total[i].quantity = coffee[intent.getExtras().getInt(String.valueOf(i + 100))].quantity;

            //total[i].img = intent.getExtras().getByteArray('c' + String.valueOf(i));
            /*Bundle bundle = getIntent().getExtras();
            Bitmap bitmap = bundle.getParcelable('c' + String.valueOf(cmenuquantity));
            total[i].setImg(bitmap);*/
            //shop.getExtras().getByteArray('c' + String.valueOf(cmenuquantity));
            //shop.putExtra('c', total[i].img);
            //total[i].name = intent.getExtras().getString('c' + String.valueOf(i));
           // total[i].quantity = intent.getExtras().getInt('c' + String.valueOf(i));
        }


        /*
         imageView.buildDrawingCache();
          Bitmap image= imageView.getDrawingCache();

           Bundle extras = new Bundle();
          extras.putParcelable("imagebitmap", image);
          intent.putExtras(extras);
          startActivity(intent);


          Bundle extras = getIntent().getExtras();
          Bitmap bmp = (Bitmap) extras.getParcelable("imagebitmap");

          image.setImageBitmap(bmp );
         */

     /*   for(int i = cmenuquantity; i < cmenuquantity + jmenuquantity; i++)
        {
            total[i].img = intent.getExtras().getInt("juiceimg");
            total[i].name = intent.getExtras().getString("juicename");
            total[i].quantity = intent.getExtras().getInt("juicequantity");
        }

        for(int i = cmenuquantity + jmenuquantity; i < cmenuquantity + jmenuquantity + fmenuquantity; i++)
        {
            total[i].img = intent.getExtras().getInt("foodimg");
            total[i].name = intent.getExtras().getString("foodname");
            total[i].quantity = intent.getExtras().getInt("foodquantity");
        }*/

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
        FinalOrder order = new FinalOrder(this, R.layout.shoppingcart, total);
        listView.setAdapter(order);

    }
}