package database;

import models.courier.Courier;
import models.menu.Menu;
import models.order.Order;
import models.place.Place;
import models.user.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database {
    private static Database instance = null;

    private Database(){
        menus = new ArrayList<>();
        users = new ArrayList<>();
        places = new ArrayList<>();
        orders = new ArrayList<>();
        couriers = new ArrayList<>();
    }

    public static Database instance(){
        if(instance == null)
            instance = new Database();
        return instance;
    }

    public ArrayList<Menu> menus;
    public ArrayList<User> users;
    public ArrayList<Place> places;
    public ArrayList<Order> orders;
    public ArrayList<Courier> couriers;


    public void getAllUsers(){
        for(int i = 0; i < users.size(); i++)
            System.out.println(
                    String.format(
                            "id: %d\n nume: %s",users.get(i).getId(),  users.get(i).getName()
                    )
            );
    }
    public void getAllPlaces(){
        for(int i = 0; i < places.size(); i++)
            System.out.println(
                    String.format(
                            "id: %d\n nume: %s",places.get(i).getId(),  places.get(i).getName()
                    )
            );
    }
    public void getAllCouriers(){
        for(int i = 0; i < couriers.size(); i++)
            System.out.println(
                    String.format(
                            "id: %d\n nume: %s",couriers.get(i).getId(),  couriers.get(i).getName()
                    )
            );
    }

    private void getUserById(int id){
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getId() == id){
                System.out.println(
                        String.format("Utilizatorul cu id-ul %d si numele %s\n",id,users.get(i).getName())
                );
            }
        }
    }
}
