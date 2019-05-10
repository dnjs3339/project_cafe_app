package com.example.cafeapp;

import java.io.Serializable;

public class Flag_num implements Serializable {
    public
    char flag;

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