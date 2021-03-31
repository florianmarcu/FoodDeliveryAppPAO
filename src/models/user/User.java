package models.user;

import models.order.Order;

import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;

public class User {
    private int id;
    private String name;
    private LocalDateTime dateRegistered;
    private List<Order> orderHistory;
    public User(int id, String name){
        this.id = id;
        this.name = name;
        this.dateRegistered = LocalDateTime.now();
        this.orderHistory = Arrays.asList();
    }

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
}
