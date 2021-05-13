package services;

import database.Database;
import models.courier.Courier;
import models.menu.Menu;
import models.menu.MenuItem;
import models.order.DeliveryOrder;
import models.order.Order;
import models.order.PickUpOrder;
import models.place.Place;
import models.user.User;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// Serviciul prin care se interactioneaza cu sistemul
// Clasa de tip Singleton
public class MenuService {
    private static MenuService instance = null;

    private MenuService(){

    }

    public static MenuService instance(){
        if(instance == null){
            instance = new MenuService();
        }
        return instance;
    }

    private static void printMenu(){
        System.out.println(
                "------------   Food Delivery App   -------------\n" +
                        "Pentru a efectua o instructiune, tasteaza inputul corespunzator:\n" +
                        "ATENTIE! Daca inchizi programul, datele introduse in aceasta sesiune se vor sterge!\n" +

                        "q) Renunta\n" +
                        //"clear) Curata terminalul\n" +
                        "1) Adauga un utilizator\n" +
                        "2) Adauga un local\n" +
                        "3) Adauga un curier\n" + //TODO
                        "4) Creeaza o comanda\n" + //TODO
                        "5) Vezi statusul unei comenzi\n" + //TODO
                        "6) Vizualizeaza meniul localului x\n" + //TODO
                        "7) Afiseaza toti utilizatorii\n" +
                        "8) Afiseaza toate localurile\n" +
                        "9) Afiseaza toti curierii\n" +
                        "10) Afiseaza utilizatorul cu id-ul...\n" +
                        "11) Afiseaza localul cu id-ul...\n" +
                        "12) Afiseaza curierul cu id-ul...\n"
        );
    }

        public static void start(){
        printMenu();
        while(true){
            System.out.println("OK");
            Scanner input = new Scanner(System.in);
            try{
                String value = input.nextLine();
                //System.out.println(value);
                switch (value){
                    case "q": return;
                    case "i": printMenu(); break;
                    case "1": {
                        System.out.println("Numele: ");
                        String name = input.nextLine();

                        User user = new User(
                                Database.instance().users.size(),
                                name
                        );
                        //System.out.println(user.getName());
                        try{
                            Database.instance().users.add(user);
                            System.out.println(Database.instance().users.size());
                        }
                        catch(Exception e){
                            System.out.println(String.format("eroare %s",e.getMessage()));
                        }
                        break;
                    }
                    case "2": {
                        System.out.println("Numele: ");
                        String name = input.nextLine();
                        //System.out.println(name);
                        System.out.println("Tipul (CAFE, RESTAURANT, PUB, SUPERMARKET): ");
                        String type = input.nextLine();
                        System.out.println("Cream meniul:\n");
                        System.out.println("Introduceti numarul de produse: \n");
                        int n = input.nextInt();
                        ArrayList<MenuItem> itemList = new ArrayList<>();
                        for(int i = 0; i<n;i++){
                            System.out.println(String.format("Pentru produsul %d:\n",i+1));
                            System.out.println("Titlul :\n");
                            String productName = input.nextLine();
                            System.out.println("Descrierea :\n");
                            String productDescription = input.nextLine();
                            System.out.println("Pret :\n");
                            double price = input.nextDouble();
                            System.out.println("Discount :\n");
                            int discount = input.nextInt();
                            itemList.add(
                                    new MenuItem(
                                            i+1,
                                            productName,
                                            productDescription,
                                            price,
                                            discount
                                    )
                            );
                        }
                        Menu menu = new Menu(
                                Database.instance().menus.size(),
                                itemList
                        );
                        Place place = new Place(
                                Database.instance().places.size(),
                                name,
                                new double[]{44.430045832998246, 26.101229225429865},
                                menu,
                                type
                        );
                        Database.instance().places.add(place);
                        break;
                    }
                    case "3":{
                        System.out.println("Numele: ");
                        String name = input.nextLine();
                        System.out.println("Numar de telefon: ");
                        String phoneNumber = input.nextLine();
                        Courier courier = new Courier(
                                Database.instance().couriers.size(),
                                name,
                                phoneNumber
                        );
                        Database.instance().couriers.add(courier);
                        break;
                    }
                    case "4":{
                        if(Database.instance().couriers.size() == 0 || Database.instance().users.size() == 0 || Database.instance().places.size() == 0){
                            System.out.println("Nu exista suficiente date pentru a crea o comanda\n" +
                                    "Trebuie sa existe cel putin o valoare pentru fiecare (utilizator, curier, local)");
                            break;
                        }
                        Order order;
                        System.out.println("Cream o comanda:");
                        System.out.println("Alege tipul comenzii: \n 1) LIVRARE\n 2) PICKUP\n");
                        String orderType = input.nextLine();
                        switch (orderType){
                            case "LIVRARE": {
                                System.out.println("Useri disponibili");
                                Database.instance().getAllUsers();
                                System.out.println("Alege id-ul utilizatorului");
                                int idUser = input.nextInt();
                                System.out.println("Curieri disponibili");
                                Database.instance().getAllCouriers();
                                System.out.println("Alege id-ul curierului");
                                int idCourier = input.nextInt();
                                System.out.println("Localuri disponibili");
                                Database.instance().getAllCouriers();
                                System.out.println("Alege id-ul localului");
                                int idPlace = input.nextInt();
                                System.out.println("Produse disponibile");
                                Database.instance().places.get(idPlace).printMenu();
                                System.out.println("Alege id-urile produselor (dupa formatul 1 2 3 1):");
                                String[] menuIds = input.nextLine().split(" ");
                                ArrayList<MenuItem> itemList = new ArrayList<>();
                                for(int i = 0; i< menuIds.length; i++)
                                    itemList.add(
                                            Database.instance()
                                                    .places.get(idPlace)
                                                    .getMenu().getItemList()
                                                    .get((int)menuIds[i].charAt(0))
                                    );
                                System.out.println("Introdu adresa de livrare");
                                String address = input.nextLine();
                                order = new DeliveryOrder(
                                        Database.instance().orders.size(),
                                        idUser,
                                        idPlace,
                                        idCourier,
                                        LocalDateTime.now(),
                                        itemList,
                                        address
                                );
                                break;
                            }
                            case "PICKUP": {
                                System.out.println("Useri disponibili");
                                Database.instance().getAllUsers();
                                System.out.println("Alege id-ul utilizatorului");
                                int idUser = input.nextInt();
                                System.out.println("Localuri disponibili");
                                Database.instance().getAllCouriers();
                                System.out.println("Alege id-ul localului");
                                int idPlace = input.nextInt();
                                System.out.println("Produse disponibile");
                                Database.instance().places.get(idPlace).printMenu();
                                System.out.println("Alege id-urile produselor (dupa formatul 1 2 3 1):");
                                String[] menuIds = input.nextLine().split(" ");
                                ArrayList<MenuItem> itemList = new ArrayList<>();
                                for(int i = 0; i< menuIds.length; i++)
                                    itemList.add(
                                            Database.instance()
                                                    .places.get(idPlace)
                                                    .getMenu().getItemList()
                                                    .get((int)menuIds[i].charAt(0))
                                    );
                                order = new PickUpOrder(
                                        Database.instance().orders.size(),
                                        idUser,
                                        idPlace,
                                        LocalDateTime.now(),
                                        itemList
                                );
                                break;
                            }
                            default: System.out.println("Ceva este gresit\n");return;
                        }

                        Database.instance().orders.add(order);
                        break;
                    }
                    case "5":{

                    }
                    case "6":{

                    }
                    case "7":{
                        ArrayList<User> users = Database.instance().users;
                        System.out.println(users.size());
                        for(int i = 0; i < users.size(); i++){
                            System.out.println(String.format("Utilizatorul %d:",i+1));
                            System.out.println(String.format("Nume: %s", users.get(i).getName()));
                            System.out.println(String.format("Istoric comenzi %d:\n\n"));
                            for(int j = 0; j < users.get(i).getOrderHistory().size(); j++){
                                System.out.println(String.format("Utilizatorul %d:",i+1));
                                System.out.println(String.format("Nume: %s", users.get(i).getName()));
                                System.out.println(String.format("Istoric comenzi %d:\n\n"));
                            }
                        }
                        break;
                    }
                    case "8":{
                        ArrayList<Place> places = Database.instance().places;
                        for(int i = 0; i < places.size(); i++){
                            System.out.println(String.format("Localul %d",i+1));
                            System.out.println(String.format("Nume: %s", places.get(i).getName()));
                            System.out.println(String.format("Istoric comenzi %d:\n\n"));

                        }
                        break;
                    }
                    case "9":{
                        ArrayList<Courier> couriers = Database.instance().couriers;
                        for(int i = 0; i < couriers.size(); i++){
                            System.out.println(String.format("Curierul %d:\n",i+1));
                            System.out.println(String.format("Nume: %s", couriers.get(i).getName()));
                            System.out.println(String.format("Istoric comenzi %d:\n\n"));

                        }
                        break;
                    }
                    //case "clear": Runtime.getRuntime().exec("cls"); break;
                    default : System.out.println("Inputul este gresit, incearca din nou");
                }
            }
            catch(Exception exp){

            }
        }
    }
}
