package com.example.cafeapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ListView;

import java.io.ByteArrayOutputStream;

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

        int cmenuquantity = sharepreference.getInt("cq", 0);
        int jmenuquantity = sharepreference.getInt("jq", 0);
        int fmenuquantity = sharepreference.getInt("fq", 0);
        int totalquantity = 0;

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

        final Shop j1 = new Shop(R.drawable.jchocolatecreamfurapuccino, "초콜렛 크림 프라푸치노", 0);
        final Shop j2 = new Shop(R.drawable.jwhitechocolatefurapuccino, "화이트 초코 프라푸치노", 0);
        final Shop j3 = new Shop(R.drawable.jgreentea, "아이스 그린 티", 0);
        final Shop j4 = new Shop(R.drawable.jlemonminttea, "레몬 민트 차", 0);
        final Shop j5 = new Shop(R.drawable.jmangoblended, "망고 블랜디드", 0);
        final Shop j6 = new Shop(R.drawable.jpinkjamongpizio, "핑크 자몽 피지오", 0);
        final Shop j7 = new Shop(R.drawable.jstrawberryyogurtblended, "딸기 요거트 블랜디드", 0);
        final Shop j8 = new Shop(R.drawable.jabocadoblended, "아보카도 블랜디드", 0);
        final Shop[] juice = {j1,j2,j3,j4,j5,j6,j7,j8};

        final Shop f1 = new Shop(R.drawable.fbagle, "허니 버터 베이글", 0);
        final Shop f2 = new Shop(R.drawable.fcake, "치즈 조각 케이크", 0);
        final Shop f3 = new Shop(R.drawable.fchocolatemurffin, "아몬드 초코 머핀", 0);
        final Shop f4 = new Shop(R.drawable.fcroissant, "녹차 딸기 크로와상", 0);
        final Shop f5 = new Shop(R.drawable.fpuding, "바닐라 푸딩", 0);
        final Shop f6 = new Shop(R.drawable.frole, "소시지 롤", 0);
        final Shop f7 = new Shop(R.drawable.fscone, "클레식 스콘", 0);
        final Shop f8 = new Shop(R.drawable.fstick, "클레식 스틱", 0);
        final Shop[] food ={f1,f2,f3,f4,f5,f6,f7,f8};



        //장바구니에 넣은 물건들 보여주기
        // 관련 c : 커피, j : 음료, f : 음식 으로 묶고
        // 순서는 이미지, 메뉴, 수량, 해당

        //if 문으로 제어하기 if (0 < cm && jm == 0 && fm == 0) 처럼
        //함수하나 만들어서 변수만 집어넣으면 알아서 출력되게 만들기



        for(int i = 0; i < cmenuquantity; i++)
        {
            DefineTotal definecoffee = new DefineTotal();
            definecoffee.defineTotal(total, coffee, i, sharepreference.getInt("coffee" + i + "", 0), sharepreference.getInt("coffeequantity" + i + "", 0));

            /*total[i] = coffee[sharepreference.getInt("coffee" + i + "", 0)];
            total[i].quantity = sharepreference.getInt("coffeequantity" + i + "", 0);*/
        }

        for(int i = cmenuquantity; i < cmenuquantity + jmenuquantity; i++)
        {
            DefineTotal definejuice = new DefineTotal();
            definejuice.defineTotal(total, juice, i, sharepreference.getInt("juice" + i + "", 0), sharepreference.getInt("juicequantity" + i + "", 0));


            /*total[i] = juice[sharepreference.getInt("juice" + i + "", 0)];
            total[i].quantity = sharepreference.getInt("juicequantity" + i + "", 0);*/
        }

        for(int i = cmenuquantity + jmenuquantity; i < cmenuquantity + jmenuquantity + fmenuquantity; i++)
        {
            DefineTotal definefood = new DefineTotal();
            definefood.defineTotal(total, food, i, sharepreference.getInt("coffee" + i + "", 0), sharepreference.getInt("coffeequantity" + i + "", 0));


            /*total[i] = food[sharepreference.getInt("food" + i + "", 0)];
            total[i].quantity = sharepreference.getInt("foodquantity" + i + "", 0);*/
        }

        /*for(int i = sharepreference.getInt("total",0); i < sharepreference.getInt("total",0) + cmenuquantity; i++)
        {
            total[i] = coffee[sharepreference.getInt("coffee" + i + "", 0)];
            total[i].quantity = sharepreference.getInt("coffeequantity" + i + "", 0);
        }

        for(int i = sharepreference.getInt("total",0); i < sharepreference.getInt("total",0) + jmenuquantity; i++)
        {
            total[i] = juice[sharepreference.getInt("juice" + i + "", 0)];
            total[i].quantity = sharepreference.getInt("juicequantity" + i + "", 0);
        }

        for(int i = sharepreference.getInt("total",0); i < sharepreference.getInt("total",0) + fmenuquantity; i++)
        {
            total[i] = food[sharepreference.getInt("food" + i + "", 0)];
            total[i].quantity = sharepreference.getInt("foodquantity" + i + "", 0);
        }

        if(50 < totalquantity)
        {
            totalquantity = 0;
        }
        totalquantity = cmenuquantity + jmenuquantity + fmenuquantity;
        editor.putInt("total", totalquantity);
        editor.commit();*/

        //리스트 불러오기
        ListView listView = findViewById(R.id.list_view);
        FinalOrder order = new FinalOrder(this, R.layout.shoppingcart, total);
        listView.setAdapter(order);

    }
}