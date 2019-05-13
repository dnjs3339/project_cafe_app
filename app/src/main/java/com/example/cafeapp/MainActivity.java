package com.example.cafeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);
        //커피
        Button btn_coffee = findViewById(R.id.coffee);
        btn_coffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CoffeeMenu.class);        //커피 선택시 커피 메뉴 이동

                startActivity(intent);
            }
        });

        //음료
        Button btn_juice = findViewById(R.id.juice);
        btn_juice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, JuiceMenu.class);         //쥬스 선택시 쥬스 메뉴 이동

                startActivity(intent);
            }
        });

        //디저트
        Button btn_food = findViewById(R.id.food);
        btn_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FoodMenu.class);          //디저트 선택시 디저트 메뉴 이동

                startActivity(intent);
            }
        });
    }
}
