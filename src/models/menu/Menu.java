package models.menu;

import java.util.List;

public class Menu {
    private int id;
    private String name;
    private List<MenuItem> itemList;

    public List<MenuItem> getItemList() {
        return itemList;
    }

    public Menu(int id, String name, List<MenuItem> itemList){
        this.id = id;
        this.name = name;
        this.itemList = itemList;
    }
}
