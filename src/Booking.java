import controller.CardController;
import controller.DatasetController;
import controller.InputContoller;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Booking {
    public static void main(String[] args) throws IOException {
//        if(args.length==0){
//            System.out.println("File path to Stock Inventory/Dataset not provided. Please enter one.");
//            System.exit(0);
//        }
        startBookingProcess(args);
    }

    private static void startBookingProcess(String[] args) throws IOException {
        Path currentPath = Paths.get("").toAbsolutePath();
        String inputPath, datasetPath, cardSetPath;
        if(args.length!=0){
            inputPath = args[0];
            datasetPath = args[1];
            cardSetPath = args[2];
        } else{
            inputPath = currentPath.toString() + "/OrderInput/Input 5.csv";
            cardSetPath = currentPath.toString() + "/InventoryData/Cards - Sheet1.csv";
            datasetPath = currentPath.toString() +"/InventoryData/Dataset - Sheet1.csv";
        }
        DatasetController datasetController = new DatasetController(datasetPath);
        datasetController.createDatasetItems();
        CardController cardController = new CardController(cardSetPath);
        cardController.createCards();
        startBookingOrder(inputPath);
    }

    private static void startBookingOrder(String path) throws IOException {
        InputContoller inputContoller = new InputContoller(path);
        if(inputContoller.startOrder()){
            if(inputContoller.checkForOrder()){
                inputContoller.calculateOrderTotal();
                if(inputContoller.getTotal()>0){
                    inputContoller.checkoutOrder();
                    System.out.println("The total amount for the order is $" + inputContoller.getTotal());
                }
            }else {
                System.out.println("Error log created. Please look into it");
                inputContoller.generateOutputFile();
            }
        }else {
            System.out.println("Order file not found");
        }
    }
}
