package com.example.cafeapp;

import java.io.Serializable;

//  커피 선택시 c, 음료 선택시 j, 음식 선택시 f
public class Flag_num implements Serializable
{
    public char flag;

    public
    void Set_flag(char ch)
    {
        flag = ch;
    }

    char Get_flag()
    {
        return flag;
    }
}