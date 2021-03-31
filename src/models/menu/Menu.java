package models.menu;

import java.util.List;

public class Menu {
    private int id;
    private List<MenuItem> itemList;

    public List<MenuItem> getItemList() {
        return itemList;
    }

    public Menu(int id, List<MenuItem> itemList){
        this.id = id;
        this.itemList = itemList;
    }
}
