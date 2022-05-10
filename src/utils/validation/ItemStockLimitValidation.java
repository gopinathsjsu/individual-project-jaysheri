package utils.validation;

import db.Database;
import db.entities.OrderItem;

import java.util.ArrayList;

public class ItemStockLimitValidation implements ValidationHandler{
    private ValidationHandler next= null;
    @Override
    public boolean validate(ArrayList<OrderItem> items) {
        Database database = Database.getInstance();
        for(OrderItem orderItem: items){
            if(database.getItemsMap().get(orderItem.getName().toLowerCase()).getQuantity()<orderItem.getQuantity()){
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
