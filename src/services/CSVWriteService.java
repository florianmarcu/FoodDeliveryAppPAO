package services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriteService {
    private static CSVWriteService instance = null;

    private CSVWriteService(){

    }
    public static CSVWriteService instance(){
        if(instance == null){
            instance = new CSVWriteService();
        }
        return instance;
    }
    public void write(String path, List<String> list){

        File file = new File(path);
        try{
            if(!file.exists()){
                file.createNewFile();
            }

            FileWriter w = new FileWriter(file,true);
            StringBuilder mesaj = new StringBuilder();
            for (String l : list) {
                mesaj.append(l);
                mesaj.append(",");
            }
            mesaj.deleteCharAt(mesaj.length()-1);
            mesaj.append("\n");
            w.write(mesaj.toString());
            w.flush();
            w.close();

        }  catch (IOException e) {
            System.out.println("Eroare de IO");
            e.printStackTrace();
        }
    }
}
