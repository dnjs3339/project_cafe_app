package com.example.cafeapp;

public class Order
{
    int img;
    String name;
    String ename;
    int quantity;
    int cost;

    Order(int img, String name, String ename, int quantity, int cost)           //ename 의 용도는 블루투스로 보내 lcd에 띄울 영어이름이다.
    {
        this.img = img;
        this.name = name;
        this.ename = ename;
        this.quantity = quantity;
        this.cost = cost;
    }
}