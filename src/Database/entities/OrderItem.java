package Database.entities;

public class OrderItem {
    private String name;
    private int quantity;
    private String cardDetails;

    public OrderItem(String name, int quantity, String cardDetails){
        this.name = name;
        this.quantity = quantity;
        this.cardDetails = cardDetails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCardDetails() {
        return cardDetails;
    }

    public void setCardDetails(String cardDetails) {
        this.cardDetails = cardDetails;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", cardDetails='" + cardDetails + '\'' +
                '}';
    }
}
