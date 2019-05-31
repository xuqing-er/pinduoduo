package websale.sale.model;

public class OrderAndItem {
    int orderId;
    int itemId;
    int number;

    public int getItemId() {
        return itemId;
    }

    public int getNumber() {
        return number;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
