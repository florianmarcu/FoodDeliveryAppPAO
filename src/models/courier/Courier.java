package models.courier;

import models.order.Order;

import java.util.Arrays;
import java.util.List;

public class Courier {
    private int id;
    private String name;
    private String phoneNumber;
    private List<Order> orderList;

    public Courier(int id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        orderList = Arrays.asList();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
