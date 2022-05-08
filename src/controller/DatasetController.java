package controller;
import Database.Database;
import utils.helper.FileHelper;
import Database.entities.Items;

import java.io.IOException;
import java.util.ArrayList;

public class DatasetController {
    private Database database = Database.getInstance();
    private FileHelper fileHelper;
    public DatasetController(String path) throws IOException {
        fileHelper = new FileHelper(path);
    }

    public void createDatasetItems() {
        try{
            fileHelper.fileReader(true);
        }catch (Exception e){
            System.out.println("The Dataset file path was not found. Please enter valid file path");
            System.exit(0);
        }
        readItems(fileHelper.getContentFile());

    }

    private void readItems(ArrayList<String> contentOfFile){
        for(int i=0;i<contentOfFile.size();i++){
            String[] splitItem = contentOfFile.get(i).split(",");
            database.getItemsMap().put(splitItem[0], new Items(splitItem[0],splitItem[1],Integer.parseInt(splitItem[2]),Double.parseDouble(splitItem[3])));
        }
    }
}
