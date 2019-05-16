package com.example.cafeapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
/*import android.widget.Button;
import android.widget.EditText;*/
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

public class DB_sales extends AppCompatActivity
{
    ListView listView;
    Sales_ListViewAdapter adapter;
    DBHelper dbHelper;
    List<Map<String, Object>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sales_real);

        //지출 내역 리스트뷰로 보여주기
        dbHelper = new DBHelper(getApplicationContext());
        listView = findViewById(R.id.sales_listView);
        adapter = new Sales_ListViewAdapter(this, R.layout.sales_list_view);

        //총매출
        int t_cost = dbHelper.sum();

        TextView text_sales_tot = findViewById(R.id.sales_total);
        text_sales_tot.setText(String.valueOf(t_cost));

        listView.setAdapter(adapter);
        showData();

        // 총 매출 클리어
        Button btn_clr = findViewById(R.id.sales_clr);
        btn_clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text_sales_tot = findViewById(R.id.sales_total);
                text_sales_tot.setText("0");                                        //

                SQLiteDatabase db;
                String sql;
                db = dbHelper.getWritableDatabase();
                sql="DELETE FROM sales";
                db.execSQL(sql);
                adapter.notifyDataSetChanged();
                showData();
            }
        });

        //메인 화면 돌아가기
        Button btn_gomain = findViewById(R.id.gotomain);
        btn_gomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DB_sales.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    //리스트 보여주는 함수
    private void showData() {
        adapter.setList(dbHelper.select());
        adapter.notifyDataSetChanged();
    }
}
