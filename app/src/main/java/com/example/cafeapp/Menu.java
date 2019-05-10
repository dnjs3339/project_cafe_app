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

        Intent intent = getIntent();    //추가
        Flag_num flag = (Flag_num) intent.getSerializableExtra("flag");

        //여러 메뉴 세팅
        Shop[] coffee =
                {new Shop(R.drawable.camericano, "아메리카노", 0),
                    new Shop(R.drawable.ccafemoca, "카페모카", 0),
                    new Shop(R.drawable.ccapuchino, "카푸치노", 0),
                    new Shop(R.drawable.ccaramelmacchiato, "캐러멜 마키아또", 0),
                    new Shop(R.drawable.ccoldbrew, "콜드 브루", 0),
                    new Shop(R.drawable.cespresso, "에스프레소", 0),
                    new Shop(R.drawable.cnightrochocolat, "나이트로 쇼콜라", 0),
                    new Shop(R.drawable.cnightrochocolatcloud, "나이트로 쇼콜라 클라우드", 0)};

        Shop[] juice =
                {new Shop(R.drawable.jchocolatecreamfurapuccino, "초콜렛 크림 프라푸치노", 0),
                new Shop(R.drawable.jwhitechocolatefurapuccino, "화이트 초코 프라푸치노", 0),
                new Shop(R.drawable.jgreentea, "아이스 그린 티", 0),
                new Shop(R.drawable.jlemonminttea, "레몬 민트 차", 0),
                new Shop(R.drawable.jmangoblended, "망고 블랜디드", 0),
                new Shop(R.drawable.jpinkjamongpizio, "핑크 자몽 피지오", 0),
                new Shop(R.drawable.jstrawberryyogurtblended, "딸기 요거트 블랜디드", 0),
                new Shop(R.drawable.jabocadoblended, "아보카도 블랜디드", 0)};

        Shop[] food =
                {new Shop(R.drawable.fbagle, "허니 버터 베이글", 0),
                 new Shop(R.drawable.fcake, "치즈 조각 케이크", 0),
                 new Shop(R.drawable.fchocolatemurffin, "아몬드 초코 머핀", 0),
                 new Shop(R.drawable.fcroissant, "녹차 딸기 크로와상", 0),
                 new Shop(R.drawable.fpuding, "바닐라 푸딩", 0),
                 new Shop(R.drawable.frole, "소시지 롤", 0),
                 new Shop(R.drawable.fscone, "클레식 스콘", 0),
                 new Shop(R.drawable.fstick, "클레식 스틱", 0)};

        //리스트 불러오기
        ListView listView = findViewById(R.id.list_view);
        switch(flag.Get_flag())
        {
            case 'c':
            CustomAdapter adapter = new CustomAdapter(this, R.layout.basket, coffee);
            listView.setAdapter(adapter);
            break;

            case 'j':
                adapter = new CustomAdapter(this, R.layout.basket, juice);
                listView.setAdapter(adapter);
                break;

            case 'f':
                adapter = new CustomAdapter(this, R.layout.basket, food);
                listView.setAdapter(adapter);
                break;
        }

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