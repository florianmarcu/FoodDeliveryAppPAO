package services;

import database.Database;
import models.menu.Menu;
import models.menu.MenuItem;
import models.place.Place;

import java.util.ArrayList;

public class PlaceCSVService {
    public void read(Database db) {

        CSVReadService rs = CSVReadService.instance();

        ArrayList<ArrayList<String>> placesList = rs.read("src/project/csv/user.csv");
        for(ArrayList<String> p : placesList){
            String name = p.get(0);
            String location = p.get(1);
            double[] locations =  new double[]{
                    Double.parseDouble(location.split("")[0]),
                    Double.parseDouble(location.split("")[1])
            };
            ArrayList<MenuItem> itemList = new ArrayList<>();
            Menu menu = new Menu(
                    1,
                    "",
                    new ArrayList<MenuItem>()
            );
            String type = p.get(3);

            Place place = new Place(db.places.size(), name, locations, menu, type);

            db.places.add(place);

        }

    }
}
