package com.example.cafeapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.valueOf;

public class ShopBasket extends AppCompatActivity
{
    final Order c1 = new Order(R.drawable.camericano, "아메리카노", "Americano", 0, 4100);
    final Order c2 = new Order(R.drawable.ccafemoca, "카페모카", "CafeMoka", 0, 5100);
    final Order c3 = new Order(R.drawable.ccapuchino, "카푸치노", "Capuchino", 0, 4600);
    final Order c4 = new Order(R.drawable.ccaramelmacchiato, "캐러멜 마키아또", "CaramelMaci", 0, 5600);
    final Order c5 = new Order(R.drawable.ccoldbrew, "콜드 브루", "ColdBrew", 0, 4500);
    final Order c6 = new Order(R.drawable.cespresso, "에스프레소", "Espresso", 0, 3600);
    final Order c7 = new Order(R.drawable.cnightrochocolat, "나이트로 쇼콜라", "NightChocola", 0, 6100);
    final Order c8 = new Order(R.drawable.cnightrochocolatcloud, "나이트로 쇼콜라 클라우드", "NightroCC", 0, 6100);
    final Order[] coffee ={c1,c2,c3,c4,c5,c6,c7,c8};

    final Order j1 = new Order(R.drawable.jchocolatecreamfurapuccino, "초콜렛 크림 프라푸치노", "ChocoCreamF", 0, 5100);
    final Order j2 = new Order(R.drawable.jwhitechocolatefurapuccino, "화이트 초코 프라푸치노", "WhiteChocoF", 0, 5700);
    final Order j3 = new Order(R.drawable.jgreentea, "아이스 그린 티", "GreenTea", 0, 5900);
    final Order j4 = new Order(R.drawable.jlemonminttea, "레몬 민트 티", "LemonMintTea", 0, 5600);
    final Order j5 = new Order(R.drawable.jmangoblended, "망고 블랜디드", "MangoBlended", 0, 5000);
    final Order j6 = new Order(R.drawable.jpinkjamongpizio, "핑크 자몽 피지오", "PJamongPizio", 0, 6300);
    final Order j7 = new Order(R.drawable.jstrawberryyogurtblended, "딸기 요거트 블랜디드", "SBYogurBlend", 0, 6100);
    final Order j8 = new Order(R.drawable.jabocadoblended, "아보카도 블랜디드", "AbocadoBlend", 0, 6900);
    final Order[] juice = {j1,j2,j3,j4,j5,j6,j7,j8};

    final Order f1 = new Order(R.drawable.fbagle, "허니 버터 베이글", "HoneyBagle", 0, 2800);
    final Order f2 = new Order(R.drawable.fcake, "치즈 조각 케이크", "CheeseCake", 0, 5500);
    final Order f3 = new Order(R.drawable.fchocolatemurffin, "아몬드 초코 머핀", "ChocoMarfin", 0, 3300);
    final Order f4 = new Order(R.drawable.fcroissant, "녹차 딸기 크로와상", "SBCroissant", 0, 4900);
    final Order f5 = new Order(R.drawable.fpuding, "바닐라 푸딩", "BanilaPuding", 0, 3300);
    final Order f6 = new Order(R.drawable.frole, "소시지 롤", "SausageRole", 0, 3300);
    final Order f7 = new Order(R.drawable.fscone, "클레식 스콘", "ClassicScone", 0, 3300);
    final Order f8 = new Order(R.drawable.fstick, "클레식 스틱", "ClassicStick", 0, 4900);
    final Order[] food ={f1,f2,f3,f4,f5,f6,f7,f8};

    ListView listView;
    Sales_ListViewAdapter adapter;
    DBHelper dbHelper;
    List<Map<String, Object>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        dbHelper = new DBHelper(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_basket);
        final SharedPreferences sharepreference = getSharedPreferences("cart", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharepreference.edit();

        int cmenuquantity = sharepreference.getInt("cq", 0);
        int jmenuquantity = sharepreference.getInt("jq", 0);
        int fmenuquantity = sharepreference.getInt("fq", 0);

        final Order[] total = new Order[cmenuquantity + jmenuquantity + fmenuquantity];            //배열 초기화

        //장바구니에 넣은 물건들 보여주기
        // 관련 c : 커피, j : 음료, f : 음식 으로 묶고
        // 순서는 이미지, 메뉴, 수량, 해당

        //if 문으로 제어하기 if (0 < cm && jm == 0 && fm == 0) 처럼
        //함수하나 만들어서 변수만 집어넣으면 알아서 출력되게 만들기


        for(int i = 0; i < cmenuquantity; i++)                                                 // 선택한 커피 메뉴만큼 반복하기
        {
            DefineTotal definecoffee = new DefineTotal();
            definecoffee.defineTotal(total, coffee, i,
                    sharepreference.getInt("coffee" + i + "", 0),               // 선택한 커피 메뉴를 total 에 넣기
                    sharepreference.getInt("coffeequantity" + i + "", 0));      // 선택한 커피 메뉴의 수량을 total.quantity 에 넣기
        }

        for(int i = 0; i < jmenuquantity; i++)
        {
            if(cmenuquantity != 0)
            {
                DefineTotal definejuice = new DefineTotal();
                definejuice.defineTotal(total, juice, i + cmenuquantity,
                        sharepreference.getInt("juice" + i + "", 0),
                        sharepreference.getInt("juicequantity" + i + "", 0));
            }

            else
            {
                DefineTotal definejuice = new DefineTotal();
                definejuice.defineTotal(total, juice, i,
                        sharepreference.getInt("juice" + i + "", 0),
                        sharepreference.getInt("juicequantity" + i + "", 0));
            }
        }

        for(int i = 0; i < fmenuquantity; i++)
        {
            if(cmenuquantity == 0 && jmenuquantity == 0)
            {
                DefineTotal definefood = new DefineTotal();
                definefood.defineTotal(total, food, i,
                        sharepreference.getInt("food" + i + "", 0),
                        sharepreference.getInt("foodquantity" + i + "", 0));
            }

            else if(cmenuquantity != 0 && jmenuquantity == 0)
            {
                DefineTotal definefood = new DefineTotal();
                definefood.defineTotal(total, food, i + cmenuquantity,
                        sharepreference.getInt("food" + i + "", 0),
                        sharepreference.getInt("foodquantity" + i + "", 0));
            }

            else if(cmenuquantity == 0 && jmenuquantity != 0)
            {
                DefineTotal definefood = new DefineTotal();
                definefood.defineTotal(total, food, i + jmenuquantity,
                        sharepreference.getInt("food" + i + "", 0),
                        sharepreference.getInt("foodquantity" + i + "", 0));
            }

            else
            {
                DefineTotal definefood = new DefineTotal();
                definefood.defineTotal(total, food, i + cmenuquantity + jmenuquantity,
                        sharepreference.getInt("food" + i + "", 0),
                        sharepreference.getInt("foodquantity" + i + "", 0));
            }
        }

        int totalcost = 0;


        //리스트 불러오기
        final ListView listView = findViewById(R.id.list_view);

        Intent intent = new Intent(ShopBasket.this, DB_sales.class);
        final FinalOrder order = new FinalOrder(this, R.layout.shoppingcart, total);

        Button btn_order = findViewById(R.id.goOrder);

        //DB에 데이터 저장
        for(int i=0; i<total.length; i++)
        dbHelper.insert(order.data[i].name, String.valueOf(order.data[i].cost));

        btn_order.setOnClickListener(new View.OnClickListener() {
            //db에 저장
            @Override
            public void onClick(View v) {
            for(int i=0; i< total.length; i++ )
            {
                //String으로 묶어서 send
                String str1 = Integer.toString(i);
                String str2 = ",";
                String str3 = Integer.toString(order.data[i].quantity);
                String str4 = ",";
                String str5 = order.data[i].ename;
                String res = str1.concat(str2).concat(str3).concat(str4).concat(str5);
                BluetoothActivity.bt.send(res, true);       //static으로 메모리에 할당된 BluetoothActivtity의 객체를 주소로 직접 접근하여 send 메소드 실행
            }
                Toast.makeText(getApplicationContext(), "결제가 완료 됐습니다", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ShopBasket.this, DB_sales.class);
                startActivity(intent);
            }
        });

        for(int i = 0; i < cmenuquantity + jmenuquantity + fmenuquantity; i++)
        {
            totalcost += total[i].cost;
        }
        TextView cost = findViewById(R.id.cost);
        cost.setText(String.valueOf(totalcost));
        listView.setAdapter(order);

        //매출 넘기기
        editor.putInt("cost", totalcost);
        editor.commit();

        Button goCoffee = findViewById(R.id.gocoffee);
        goCoffee.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ShopBasket.this, CoffeeMenu.class);
                startActivity(intent);
            }
        });

        Button goJuice = findViewById(R.id.gojuice);
        goJuice.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ShopBasket.this, JuiceMenu.class);
                startActivity(intent);
            }
        });

        Button goFood = findViewById(R.id.gofood);
        goFood.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ShopBasket.this, FoodMenu.class);
                startActivity(intent);
            }
        });

        Button clearCart = findViewById(R.id.clearcart);
        clearCart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editor.clear();
                editor.commit();
                Intent intent = new Intent(ShopBasket.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}