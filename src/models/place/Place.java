package models.place;

import models.menu.Menu;

public class Place {
    private String name;
    private int id;
    private double[] location;
    private Menu menu;
    private PlaceType type;
    private int rating;

    public Place(int id, String name){
        this.id = id;
        this.name = name;
    }
    public Place(int id, String name, double[] location, Menu menu, String type){
        this.id = id;
        this.name = name;
        this.location = location;
        this.menu = menu;
        PlaceType selectedType;
        switch(type){
            case "CAFE": this.type = PlaceType.CAFE; break;
            case "RESTAURANT": this.type = PlaceType.RESTAURANT; break;
            case "PUB": this.type = PlaceType.PUB; break;
            case "SUPERMARKET": this.type = PlaceType.SUPERMARKET; break;
        }
    }
    public int getId(){
        return id;
    }

    public Menu getMenu() {
        return menu;
    }
    public void printMenu(){
        System.out.println("Meniu:\n");
        for(int i = 0; i < menu.getItemList().size(); i++){
            System.out.println(String.format("%d "));
        }
    }


    public String getName(){
        return name;
    }
}
