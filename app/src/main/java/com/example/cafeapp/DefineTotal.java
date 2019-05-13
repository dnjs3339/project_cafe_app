package com.example.cafeapp;

import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class DefineTotal
{
    public void defineTotal(Shop[] total, Shop[] cjm, int start_i, int num, int qua)
    {
            total[start_i] = cjm[num];
            total[start_i].quantity = qua;
    }
}
