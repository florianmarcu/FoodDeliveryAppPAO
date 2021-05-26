package services;

import database.Database;
import models.user.User;

import java.util.ArrayList;

public class UserCSVService {
    public void read(Database db) {

        CSVReadService rs = CSVReadService.instance();

        ArrayList<ArrayList<String>> usersList = rs.read("src/project/csv/user.csv");
        for(ArrayList<String> u : usersList){
            String name = u.get(0);


            User user = new User(name);

            db.users.add(user);

        }

    }
}
