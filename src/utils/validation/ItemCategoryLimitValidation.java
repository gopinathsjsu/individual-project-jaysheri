package utils.validation;

import db.Database;
import db.entities.OrderItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class ItemCategoryLimitValidation implements ValidationHandler{
    private ValidationHandler next= null;
    private final int luxCap = 6;
    private final int essCap = 6;
    private final int miscCap = 10;
    @Override
    public boolean validate(ArrayList<OrderItem> items) {
        HashMap<String,Integer> map = new HashMap<>();
        Database database = Database.getInstance();
        for(OrderItem orderItem: items){
            map.put(database.getItemsMap().get(orderItem.getName().toLowerCase()).getCategory(),map.getOrDefault(database.getItemsMap().get(orderItem.getName().toLowerCase()).getCategory(),0)+orderItem.getQuantity());
        }
        if(map.getOrDefault("Luxury",0)>luxCap){
            return false;
        }else if(map.getOrDefault("Essential",0)>essCap){
            return false;
        }else if(map.getOrDefault("Misc",0)>miscCap){
            return false;
        }

//        System.out.println(map.getOrDefault("Luxury",0));
//        System.out.println(map.getOrDefault("Essential",0));
//        System.out.println(map.getOrDefault("Misc",0));
        return true;
    }
    @Override
    public void nextHandler(ValidationHandler next) {
        this.next = next ;
    }
}
