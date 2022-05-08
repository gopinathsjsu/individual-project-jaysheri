package db.entities;

public class Items {
    private String category;
    private String itemName;
    private int quantity;
    private double price;

    public Items() {}

    public Items(String itemName, String category, int quantity, double price){
        this.category = category;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

    public Items(String itemName, int quantity){
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Items{" +
                "category='" + category + '\'' +
                ", itemName='" + itemName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
