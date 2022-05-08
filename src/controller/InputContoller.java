package controller;

import db.Database;
import utils.helper.FileHelper;
import db.entities.Items;
import db.entities.Order;
import db.entities.OrderItem;
import db.entities.OrderOutput;
import utils.validation.ItemCategoryLimitValidation;
import utils.validation.ItemPresenceValidation;
import utils.validation.ItemStockLimitValidation;
import utils.validation.ValidationHandler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

public class InputContoller {
    private Database database = Database.getInstance();
    private Order currentOrder = new Order();
    private FileHelper fileHelper;
    private ArrayList<String> output = new ArrayList<>();
    private ArrayList<OrderItem> items = new ArrayList<>();
    private HashSet<String> creditCards = database.getCardsSet();
    private double total = 0;

    public InputContoller() {}

    public InputContoller(String filePath){
        fileHelper = new FileHelper(filePath);
    }

    public void addToOutput(String str){
        output.add(str);
    }

    public boolean startOrder() {
        try{
            fileHelper.fileReader(true);
        }catch (Exception e){
            return false;
        }
        getItems(fileHelper.getContentFile());
        return true;
    }
    public boolean checkForOrder() throws IOException {
        checkItemInStock();
        return output.size()==0;
    }

    public void calculateOrderTotal() {
        for(OrderItem item: items){
            total += item.getQuantity()*database.getItemsMap().get(item.getName()).getPrice();
        }
        currentOrder.setTotalPrice(total);
    }

    public double getTotal() {
    return currentOrder.getTotalPrice();
    }

    public void checkoutOrder() {
        for(OrderItem orderItem: items){
            Items item = database.getItemsMap().get(orderItem.getName());
            item.setQuantity(item.getQuantity()-orderItem.getQuantity());
        }
        for(String credit:creditCards){
            if(!database.getCardsSet().contains(credit)){
                database.getCardsSet().add(credit);
            }
        }
        generateOutputFile();
    }

    public void printMessage() {
        for(String line: output){
            System.out.println(line);
        }
    }

    public void getItems(ArrayList<String> fileContent){
        for(String line: fileContent){
            String[] item = line.split(",");
            if(database.getItemsMap().containsKey(item[0])){
                items.add(new OrderItem(item[0],Integer.parseInt(item[1]),item[2].replaceAll("\\s+","")));
            }else{
                output.add("Item "+item[0]+" not found");
            }
        }
        if(!output.isEmpty()){
            items.clear();
        }
    }

    public boolean checkItemInStock() throws IOException {
        StringBuilder sb = new StringBuilder();
        database.getOrdersList().add(currentOrder);
        ValidationHandler itemPresence = new ItemPresenceValidation();
        ValidationHandler itemStock = new ItemStockLimitValidation();
        ValidationHandler itemCategory = new ItemCategoryLimitValidation();
        itemPresence.nextHandler(itemStock);
        itemStock.nextHandler(itemCategory);
        if(!itemPresence.validate(items)){
            output.add("One of the Item doesn't exist in the stock");
        }else if(!itemStock.validate(items)){
            output.add("Please correct quantities of one of the items");
        }else if(!itemCategory.validate(items)){
            output.add("Limit on one of the Categories has exceeded");
        }
        for(OrderItem orderItem: items){
            Items item = database.getItemsMap().get(orderItem.getName());
            if(item.getQuantity()<orderItem.getQuantity()){
                if(sb.length()>0)
                    sb.append(",");
                sb.append(orderItem.getName()+"("+item.getQuantity()+")");
            }else{
                if(!creditCards.contains(orderItem.getCardDetails())){
                    creditCards.add(orderItem.getCardDetails());
                String cardSet = "/Users/jayanthreddysheri/Documents/202/project/team-project-assassins/Booking/files/Cards - Sheet1.csv";
                FileWriter fileWriter = new FileWriter(cardSet, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                PrintWriter printWriter = new PrintWriter(bufferedWriter);
                printWriter.println();
                printWriter.println(orderItem.getCardDetails());
                printWriter.flush();
                printWriter.close();}
            }
        }
        if(sb.length()>0){
            output.add("Please correct quantities");
            output.add(sb.toString());
        }
        return sb.length()==0;
    }

    public void generateOutputFile(){
        if(output.size()==0){
            output.add("Item,Quantity,price");
            for (OrderItem item: items){
                OrderOutput orderOutput = new OrderOutput(item.getName(), item.getQuantity(), item.getQuantity()*database.getItemsMap().get(item.getName()).getPrice());
                output.add(orderOutput.toString());
            }
            output.add("Total Price = "+Double.toString((currentOrder.getTotalPrice())));
            try{
                fileHelper.writeOuput(output,false);
            }catch (IOException e){
                e.printStackTrace();
            }
        }else{
            try{
                fileHelper.writeOuput(output,true);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
