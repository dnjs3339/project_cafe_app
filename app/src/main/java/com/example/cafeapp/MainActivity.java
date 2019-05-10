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
        Button btn_cof = findViewById(R.id.cof);
        btn_cof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        MainActivity.this, CoffeeActivity.class);
                startActivity(intent);
            }
        });

        //디저트
        Button btn_des = findViewById(R.id.des);
        btn_des.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        MainActivity.this, DesertActivity.class);
                startActivity(intent);
            }
        });

        //음료
        Button btn_bev = findViewById(R.id.bev);
        btn_bev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        MainActivity.this, BeverActivity.class);
                startActivity(intent);
            }
        });



    }
}
