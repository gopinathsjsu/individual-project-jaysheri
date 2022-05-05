package controller;

import Database.Database;
import helper.FileHelper;

import java.io.IOException;
import java.util.ArrayList;

public class CardController {
    private Database database = Database.getInstance();
    private FileHelper fileHelper;
    public CardController(String path) throws IOException {
        fileHelper = new FileHelper(path);
    }

    public void cardCreation() {
        try{
            fileHelper.fileReader(true);
        }catch (Exception e){
            System.out.println("The cards dataset is not found. Please enter valid path");
            System.exit(0);
        }
        readCards(fileHelper.getContentFile());
    }

    public void readCards(ArrayList<String> contentOfFile){
        for(int i=0;i<contentOfFile.size();i++){
            database.getCardsSet().add(contentOfFile.get(i));
        }
    }
}
