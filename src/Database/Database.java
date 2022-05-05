package Database;

import model.Items;
import model.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Database {

    private static Database databaseInstance;

    private HashMap<String,Items> itemsMap = new HashMap<>();

    private HashSet<String> cardsSet = new HashSet<>();

    private ArrayList<Order> ordersList = new ArrayList<>();

    private Database() {}

    public static Database getInstance() {
        if( databaseInstance == null){
            databaseInstance = new Database();
        }
        return databaseInstance;
    }

    public HashMap<String,Items> getItemsMap(){
        return itemsMap;
    }

    public HashSet<String> getCardsSet() {
        return cardsSet;
    }

    public ArrayList<Order> getOrdersList() {
        return ordersList;
    }


}
