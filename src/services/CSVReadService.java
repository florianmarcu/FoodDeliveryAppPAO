package services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CSVReadService {
    private static CSVReadService instance = null;

    private CSVReadService(){

    }
    public static CSVReadService instance(){
        if(instance == null){
            instance = new CSVReadService();
        }
        return instance;
    }
    public ArrayList<ArrayList<String>> read(String path) {

        ArrayList<ArrayList<String>> objects = new ArrayList<>();

        try {
            File input = new File(path);
            Scanner s = new Scanner(input);

            String k;
            k = s.nextLine();

            while (k != null) {
                ArrayList<String> current_obj = new ArrayList<>();
                current_obj.addAll(Arrays.asList(k.split(",")));
                objects.add(current_obj);

                if (s.hasNextLine()) {
                    k = s.nextLine();
                } else {
                    k = null;
                }

            }
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return objects;
    }
}

