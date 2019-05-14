package com.example.cafeapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class CoffeeMenu extends AppCompatActivity
{
    int cmenuquantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        final ListView listView = findViewById(R.id.list_view);
        final SharedPreferences sharecoffee = getSharedPreferences("cart", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharecoffee.edit();

        final Order c1 = new Order(R.drawable.camericano, "아메리카노", 0, 4100);
        final Order c2 = new Order(R.drawable.ccafemoca, "카페모카", 0, 5100);
        final Order c3 = new Order(R.drawable.ccapuchino, "카푸치노", 0, 4600);
        final Order c4 = new Order(R.drawable.ccaramelmacchiato, "캐러멜 마키아또", 0, 5600);
        final Order c5 = new Order(R.drawable.ccoldbrew, "콜드 브루", 0, 4500);
        final Order c6 = new Order(R.drawable.cespresso, "에스프레소", 0, 3600);
        final Order c7 = new Order(R.drawable.cnightrochocolat, "나이트로 쇼콜라", 0, 6100);
        final Order c8 = new Order(R.drawable.cnightrochocolatcloud, "나이트로 쇼콜라 클라우드", 0, 6100);
        final Order[] coffee ={c1,c2,c3,c4,c5,c6,c7,c8};

        final Intent basket = new Intent(CoffeeMenu.this, ShopBasket.class);

        CoffeeCustom adapter = new CoffeeCustom(this, R.layout.basket, coffee);
        listView.setAdapter(adapter);

        Button goCart = findViewById(R.id.goCart);
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
