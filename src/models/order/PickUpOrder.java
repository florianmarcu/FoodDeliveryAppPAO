package models.order;

import models.menu.MenuItem;

import java.time.LocalDateTime;
import java.util.List;

public class PickUpOrder implements Order{
    private int id;
    private int userId;
    private int placeId;
    private LocalDateTime orderDate;
    private LocalDateTime pickUpDate;
    private List<MenuItem> itemList;

    public PickUpOrder(int id){
        this.orderDate = LocalDateTime.now();
    }
}
