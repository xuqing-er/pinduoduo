package websale.sale.model;

public class CartItem {
    int clientId;
    int itemId;
    int number;

    public int getItemId() {
        return itemId;
    }

    public int getNumber() {
        return number;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getClientId() {
        return clientId;
    }
}
