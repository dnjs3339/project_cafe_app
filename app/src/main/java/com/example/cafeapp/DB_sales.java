package com.example.cafeapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
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

        dbHelper = new DBHelper(getApplicationContext());
        listView = (ListView) findViewById(R.id.sales_listView);
        adapter = new Sales_ListViewAdapter(this, R.layout.sales_list_view);

        //누적하자
        SharedPreferences sharepreference = getSharedPreferences("cart", MODE_PRIVATE);
        SharedPreferences.Editor edit = sharepreference.edit();
        //총매출
        int sales_tot = sharepreference.getInt("cost", 0);
        int acc_sales = sharepreference.getInt("acc_sales", 0);
        acc_sales += sales_tot;
        edit.putInt("acc_sales", acc_sales);
        edit.commit();

        Log.d("cost", String.valueOf(acc_sales));
        TextView txtv_sales_tot = findViewById(R.id.sales_total);
        txtv_sales_tot.setText(String.valueOf(acc_sales));

        listView.setAdapter(adapter);

        Button btn_clr = findViewById(R.id.sales_clr);
        btn_clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db;
                String sql;
                db = dbHelper.getWritableDatabase();
                sql="DELETE FROM sales";
                db.execSQL(sql);
                Log.d("sql","delet all!");
                adapter.notifyDataSetChanged();
                Toast.makeText(getBaseContext(),"주문 완료",Toast.LENGTH_SHORT).show();
                showData();
            }
        });
        showData();

        Button btn_gomain = findViewById(R.id.gotomain);
        btn_gomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DB_sales.this, DB_sales.class);
                startActivity(intent);
            }
        });
        /*
        Log.d("test", order.data[0].name);
        Log.d("testname", String.valueOf(order.data[0].quantity));
        Log.d("testname", String.valueOf(order.data[0].cost));*/

        /*Button btn_ok = (Button) findViewById(R.id.ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               EditText title = (EditText) findViewById(R.id.title);
                EditText cost = (EditText) findViewById(R.id.cost);
                dbHelper.insert(title.getText().toString(), cost.getText().toString());
                showData();
            }*/
    }
    private void showData() {
        adapter.setList(dbHelper.select());
        adapter.notifyDataSetChanged();
    }
}
