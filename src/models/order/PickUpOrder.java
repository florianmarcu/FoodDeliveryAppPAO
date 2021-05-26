package models.order;

import models.menu.MenuItem;

import java.time.LocalDateTime;
import java.util.List;

public class PickUpOrder extends Order{
    private int id;
    private int userId;
    private int placeId;
    private LocalDateTime orderDate;
    private LocalDateTime pickUpDate;
    private List<MenuItem> itemList;

    public PickUpOrder(int id, int userId, int placeId, LocalDateTime orderDate, List<MenuItem> itemList) {
        this.id = id;
        this.userId = userId;
        this.placeId = placeId;
        this.orderDate = orderDate;
        this.itemList = itemList;
    }
}
