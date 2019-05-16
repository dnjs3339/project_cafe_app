package com.example.cafeapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class CoffeeMenu extends AppCompatActivity       //CoffeeCustom에서 만든 커피 메뉴 리스트 보여주기
{
    int cmenuquantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        //리스트로 커피 보여주기
        final ListView listView = findViewById(R.id.list_view);
        //앱에 수량 저장
        final SharedPreferences sharecoffee = getSharedPreferences("cart", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharecoffee.edit();

        final Order c1 = new Order(R.drawable.camericano, "아메리카노", "Americano", 0, 4100);
        final Order c2 = new Order(R.drawable.ccafemoca, "카페모카", "CafeMoka", 0, 5100);
        final Order c3 = new Order(R.drawable.ccapuchino, "카푸치노", "Capuchino", 0, 4600);
        final Order c4 = new Order(R.drawable.ccaramelmacchiato, "캐러멜 마키아또", "CaramelMaci", 0, 5600);
        final Order c5 = new Order(R.drawable.ccoldbrew, "콜드 브루", "ColdBrew", 0, 4500);
        final Order c6 = new Order(R.drawable.cespresso, "에스프레소", "Espresso", 0, 3600);
        final Order c7 = new Order(R.drawable.cnightrochocolat, "나이트로 쇼콜라", "NightChocola", 0, 6100);
        final Order c8 = new Order(R.drawable.cnightrochocolatcloud, "나이트로 쇼콜라 클라우드", "NightroCC", 0, 6100);
        final Order[] coffee ={c1,c2,c3,c4,c5,c6,c7,c8};

        final Intent basket = new Intent(CoffeeMenu.this, ShopBasket.class);        //장바구니로 넘어가기

        TextView textCoffeeTotal = findViewById(R.id.coffeetotalcost);                             // 커피 총 결제 금액 보여주기

        CoffeeCustom adapter = new CoffeeCustom(this, R.layout.basket, coffee, textCoffeeTotal);    //CoffeeCustom한거 adapter로 리스트 보여주기
        listView.setAdapter(adapter);

        Button goCart = findViewById(R.id.goCart);                                  // 장바구니 갈 때 주문을 한 애들만 장바구니로 보내주기
        goCart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                cmenuquantity = 0;
                for(int i = 0; i < coffee.length; i++)
                {
                    if(coffee[i].quantity != 0)
                    {
                        editor.putInt("coffee" + cmenuquantity + "", i);
                        editor.putInt("coffeequantity" + cmenuquantity + "", coffee[i].quantity);
                       // basket.putExtra("cofq", cmenuquantity);
                        cmenuquantity++;
                    }
                }

                editor.putInt("cq", cmenuquantity);
                editor.commit();

                startActivity(basket);
            }
        });


    }
}
