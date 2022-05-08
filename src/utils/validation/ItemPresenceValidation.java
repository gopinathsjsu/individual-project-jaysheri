package utils.validation;

import db.Database;
import db.entities.OrderItem;

import java.util.ArrayList;

public class ItemPresenceValidation implements ValidationHandler{
    private ValidationHandler next = null;
    @Override
    public boolean validate(ArrayList<OrderItem> items) {
        Database database = Database.getInstance();
        for(OrderItem orderItem: items){
            if(!database.getItemsMap().containsKey(orderItem.getName())){
                return false;
            }
        }
        return true;
    }

    @Override
    public void nextHandler(ValidationHandler next) {
        this.next = next ;
    }
}
