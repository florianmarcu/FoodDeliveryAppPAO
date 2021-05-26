package models.order;

import models.menu.MenuItem;

import java.time.LocalDateTime;
import java.util.List;

public class DeliveryOrder extends Order{
    private int id;
    private int userId;
    private int courierId;
    private int placeId;
    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;
    private List<MenuItem> itemList;
    private String deliveryAddress;

    public DeliveryOrder(int id, int userId, int courierId, int placeId, LocalDateTime orderDate, List<MenuItem> itemList, String deliveryAddress) {
        this.id = id;
        this.userId = userId;
        this.courierId = courierId;
        this.placeId = placeId;
        this.orderDate = orderDate;
        this.itemList = itemList;
        this.deliveryAddress = deliveryAddress;
    }
}
