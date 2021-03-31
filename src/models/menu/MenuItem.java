package models.menu;

import java.awt.*;

public class MenuItem {
    private int id;
    private String title;
    private String content;
    private double price;
    private int discount;
    //private Image image;
    public MenuItem(int id, String title, String content, double price, int discount){
        this.id = id;
        this.title = title;
        this.content = content;
        //this.image = image;
        this.price = price;
        this.discount = discount;
    }
}
