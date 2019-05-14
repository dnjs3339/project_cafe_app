package com.example.cafeapp;

import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class DefineTotal
{
    public void defineTotal(Order[] total, Order[] cjf, int start_i, int num, int qua)
    {
       total[start_i] = cjf[num];                      // 선택한 coffee,juice,food 를 total 에 넣기
       total[start_i].quantity = qua;                  // 선택한 coffee,juice,food 의 수량을 total 의 수량에 넣기
       total[start_i].cost = qua * total[start_i].cost;
    }
}
