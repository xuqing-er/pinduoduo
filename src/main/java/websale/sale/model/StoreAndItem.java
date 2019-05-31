package websale.sale.model;

public class StoreAndItem {
    int storeId;
    int itemId;
    int number;

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getNumber() {
        return number;
    }

    public int getItemId() {
        return itemId;
    }
}
