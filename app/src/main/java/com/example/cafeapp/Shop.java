package com.example.cafeapp;

public class Shop           //한 칸당 요소(이미지, 이름, 수량조절)
{
    int img;
    String name;
    int quantity;

    Shop(int img, String name, int quantity) {
        this.img = img;
        this.name = name;
        this.quantity = quantity;
    }
}