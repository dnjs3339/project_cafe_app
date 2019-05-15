package com.example.cafeapp;

public class Order
{
    int img;
    String name;
    String ename;
    int quantity;
    int cost;

    Order(int img, String name, String ename, int quantity, int cost)
    {
        this.img = img;
        this.name = name;
        this.ename = ename;
        this.quantity = quantity;
        this.cost = cost;
    }
}