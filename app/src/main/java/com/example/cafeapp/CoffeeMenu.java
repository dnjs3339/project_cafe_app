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
        final SharedPreferences sharepreference = getSharedPreferences("cart", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharepreference.edit();

        final Shop c1 = new Shop(R.drawable.camericano, "아메리카노", 0);
        final Shop c2 = new Shop(R.drawable.ccafemoca, "카페모카", 0);
        final Shop c3 = new Shop(R.drawable.ccapuchino, "카푸치노", 0);
        final Shop c4 = new Shop(R.drawable.ccaramelmacchiato, "캐러멜 마키아또", 0);
        final Shop c5 = new Shop(R.drawable.ccoldbrew, "콜드 브루", 0);
        final Shop c6 = new Shop(R.drawable.cespresso, "에스프레소", 0);
        final Shop c7 = new Shop(R.drawable.cnightrochocolat, "나이트로 쇼콜라", 0);
        final Shop c8 = new Shop(R.drawable.cnightrochocolatcloud, "나이트로 쇼콜라 클라우드", 0);
        final Shop[] coffee ={c1,c2,c3,c4,c5,c6,c7,c8};

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

                if(cmenuquantity == 0)
                {
                    editor.clear();
                    editor.commit();
                }
                startActivity(basket);
            }
        });


    }
}
