package utils.validation;

import db.entities.OrderItem;

import java.util.ArrayList;

public interface ValidationHandler {
    boolean validate(ArrayList<OrderItem> items);
    void nextHandler(ValidationHandler next);
}
