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
        final Flag_num flag = new Flag_num();
        //커피
        Button btn_coffee = findViewById(R.id.coffee);
        btn_coffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Menu.class);

                flag.Set_flag('c');

                intent.putExtra("flag", flag);  //추가
                startActivity(intent);
            }
        });

        //디저트
        Button btn_food = findViewById(R.id.food);
        btn_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Menu.class);

                flag.Set_flag('f');

                intent.putExtra("flag", flag);  //추가
                startActivity(intent);
            }
        });

        //음료
        Button btn_juice = findViewById(R.id.juice);
        btn_juice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, Menu.class);

                flag.Set_flag('j');

                intent.putExtra("flag", flag);  //추가
                startActivity(intent);
            }
        });
    }
}
